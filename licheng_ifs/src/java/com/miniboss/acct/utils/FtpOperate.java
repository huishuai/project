package com.miniboss.acct.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.*;

import java.io.*;
import java.net.SocketException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-3-9
 * Time: 3:35:32
 * To change this template use File | Settings | File Templates.
 */
public class FtpOperate {
    public static Log logger = LogFactory.getLog(FtpOperate.class);

    private String userName;         //FTP ��¼�û���
    private String password;         //FTP ��¼����
    private String ip;                     //FTP ��������ַIP��ַ
    private int port;                        //FTP �˿�
    private FTPClient ftpClient = null; //FTP �ͻ��˴���
    private String rootPath = "/";
    static String fileSeparator = "/";
    private boolean processStreamClose = false;
    private boolean login = false;
    private boolean printLog = false;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isPrintLog() {
        return printLog;
    }

    public void setPrintLog(boolean printLog) {
        this.printLog = printLog;
    }

    public FtpOperate(String ip, int port, String userName, String password) {
        this.ip = ip;
        this.port = port;
        this.userName = userName;
        this.password = password;
    }

    public void validateCommandAndClose() {
        if (processStreamClose) {
            try {
                ftpClient.completePendingCommand();
            } catch (IOException e) {
                e.printStackTrace();
            }
            processStreamClose = false;
        }
    }

    /**
     * �Ѳ���
     *
     * @param pathName
     * @return
     * @throws IOException
     */
    public String[] getFileList(String pathName) throws IOException {
        if (!login) return null;
        validateCommandAndClose();
        FTPFile[] ftpFiles = ftpClient.listFiles(pathName);
        String[] list = new String[ftpFiles.length];
        for (int i = 0; i < ftpFiles.length; i++) {
            FTPFile ftpFile = ftpFiles[i];
            list[i] = ftpFile.getName();
        }
        return list;
    }

    public long getFileSize(String path, String fileName) throws IOException {
        validateCommandAndClose();
        if (!path.startsWith(fileSeparator))
            path = fileSeparator + path;
        if (!path.endsWith(fileSeparator))
            path = path + fileSeparator;
        FTPFile[] ftpFiles = ftpClient.listFiles(path + fileName);
        if (ftpFiles != null && ftpFiles.length > 0) {
            FTPFile ftpFile = ftpFiles[0];
            return ftpFile.getSize();
        }
        return 0L;
    }

    /**
     * �Ѳ���
     *
     * @return
     */
    public boolean checkConnected() {
        return ftpClient.isConnected();
    }

    /**
     * �Ѳ���
     *
     * @param pathName
     * @param fileName
     * @return
     * @throws IOException
     */
    public boolean checkFile(String pathName, String fileName) throws IOException {
        if (!login) return false;
        validateCommandAndClose();
        boolean result = checkFileByFtpClient(pathName, fileName);
        return result;
    }

    /**
     * �Ѳ���
     *
     * @param path
     * @param fileName
     * @return
     */
    public boolean checkFileByFtpClient(String path, String fileName) {
        if (!login) return false;
        validateCommandAndClose();
        if (path == null || fileName == null)
            return false;
        try {
            if (!path.startsWith(fileSeparator))
                path = fileSeparator + path;
            if (!path.endsWith(fileSeparator))
                path = path + fileSeparator;
            boolean flag = ftpClient.changeWorkingDirectory(path);
            if (!flag) {
                path = path.substring(1, path.length());
                flag = ftpClient.changeWorkingDirectory(path);
            }
            if (flag) {
                String[] fileNames = ftpClient.listNames();
                ftpClient.changeWorkingDirectory(rootPath);
                if (fileNames == null)
                    return false;
                for (String afileName : fileNames) {
                    if (fileName.equals(afileName)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * �ϴ������ļ�����������
     * �Ѳ���
     *
     * @param localFile--�����ļ�·��
     * @param distFolder--�µ��ļ���,��������Ϊ��""
     * @return true �ϴ��ɹ���false �ϴ�ʧ��
     */
    public boolean uploadFile(File localFile, final String distFolder) {
        if (!login) return false;
        validateCommandAndClose();
        boolean flag = true;
        InputStream input = null;
        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
            input = new FileInputStream(localFile);

            ftpClient.changeWorkingDirectory(rootPath);
            this.checkAndMakeFtpDir(distFolder);
            ftpClient.changeWorkingDirectory(distFolder);

            System.out.println("b>>>>>>> : " + localFile.getPath() + " " + ftpClient.printWorkingDirectory());
            flag = ftpClient.storeFile(localFile.getName(), input);
            if (flag) {
                System.out.println("�ϴ��ļ��ɹ���");
            } else {
                System.out.println("�ϴ��ļ�ʧ�ܣ�");
            }

        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("�����ļ��ϴ�ʧ�ܣ�", e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        processStreamClose = true;
        return flag;
    }

    /**
     * �Ѳ���
     *
     * @param toFtpPath
     * @param realFileName
     * @return
     * @throws IOException
     */
    public boolean uploadFile(String toFtpPath, String realFileName) throws IOException {
        if (!login) return false;
        validateCommandAndClose();
        checkAndMakeFtpDir(toFtpPath);
        return uploadFileByFtpClient(toFtpPath, realFileName);
    }

    /**
     * �Ѳ���
     *
     * @param toFtpPath
     * @param fileName
     * @param inputStream
     * @return
     * @throws IOException
     */
    public boolean uploadFile(String toFtpPath, String fileName, InputStream inputStream) throws IOException {
        if (!login) return false;
        validateCommandAndClose();
        boolean result = false;
        checkAndMakeFtpDir(toFtpPath);
        if (ftpClient.changeWorkingDirectory(toFtpPath)) {
            result = ftpClient.storeFile(fileName, inputStream);
        }
        ftpClient.changeWorkingDirectory(rootPath);
        return result;
    }

    /**
     * �ϴ�����ļ�
     *
     * @param localFile--�����ļ���·��
     * @return true �ϴ��ɹ���false �ϴ�ʧ��
     */
    public String uploadManyFile(String localFile) {
        if (!login) return null;
        validateCommandAndClose();
        boolean flag = true;
        StringBuffer strBuf = new StringBuffer();
        try {
            File file = new File(localFile);        // �ڴ�Ŀ¼�����ļ�
            File fileList[] = file.listFiles();
            for (File f : fileList) {
                if (f.isDirectory()) {            // �ļ����л����ļ���
                    uploadManyFile(f.getAbsolutePath());
                } else {
                }
                if (!flag) {
                    strBuf.append(f.getName() + "\r\n");
                }
            }
            System.out.println(strBuf.toString());
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("�����ļ��ϴ�ʧ�ܣ�", e);
        }
        return strBuf.toString();
    }

    /**
     * �����ļ�
     *
     * @param remoteFileName       --�������ϵ��ļ���
     * @param localFileName--�����ļ���
     * @return true ���سɹ���false ����ʧ��
     */
    public boolean downloadFile(String remoteFileName, String localFileName) {
        if (!login) return false;
        validateCommandAndClose();
        boolean flag = true;
        // �����ļ�
        BufferedOutputStream buffOut = null;
        try {
            buffOut = new BufferedOutputStream(new FileOutputStream(localFileName));
            flag = ftpClient.retrieveFile(remoteFileName, buffOut);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("�����ļ�����ʧ�ܣ�", e);
        } finally {
            try {
                if (buffOut != null)
                    buffOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean downloadFile(String localPath, String ftpPath, String fileName) {
        if (!login) return false;
        validateCommandAndClose();
        boolean result = false;
        if (!ftpPath.startsWith(fileSeparator)) {
            ftpPath = fileSeparator + ftpPath;
        }
        if (!ftpPath.endsWith(fileSeparator)) {
            ftpPath = ftpPath + fileSeparator;
        }
        File f = new File(localPath);
        //���������ʱĿ¼������,������
        if (!f.exists()) {
            boolean mkResult = f.mkdirs();
            //����ʧ��,�������
            if (!mkResult) {
                return mkResult;
            }
        }
        if (!localPath.endsWith(fileSeparator)) {
            localPath = localPath + fileSeparator;
        }
        if (f.isDirectory()) {
            result = downloadFile(ftpPath + fileName, localPath + fileName);
        }
        return result;
    }

    /**
     * ɾ��һ���ļ�
     */
    public boolean delFile(String filename) {
        if (!login) return false;
        validateCommandAndClose();
        boolean flag = true;
        try {
            flag = ftpClient.deleteFile(filename);
            if (flag) {
                System.out.println("ɾ���ļ��ɹ���");
            } else {
                System.out.println("ɾ���ļ�ʧ�ܣ�");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }

    public boolean delFile(String path, String fileName) throws IOException {
        boolean result = checkFile(path, fileName);
        validateCommandAndClose();
        if (!result) {
            return false;
        }
        if (!path.endsWith(fileSeparator)) {
            path = path + fileSeparator;
        }
        try {
            result = ftpClient.deleteFile(path + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * ɾ��Ŀ¼
     */
    public void deleteDirectory(String pathname) {
        if (!login) return;
        validateCommandAndClose();
        if (!pathname.endsWith(fileSeparator)) {
            pathname = pathname + fileSeparator;
        }
        try {
            FTPFile[] ftpFiles = ftpClient.listFiles(pathname);
            if (ftpFiles != null) {
                for (FTPFile ftpFile : ftpFiles) {
                    ftpClient.deleteFile(pathname + ftpFile.getName());
                }
            }
            ftpClient.removeDirectory(pathname);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    //��ȡFTP���ļ�������ʽ����

    public InputStream readFtpFile(String ftpPath, String fileName) throws IOException {
        if (!login) return null;
        validateCommandAndClose();
        InputStream inputStream = null;
        if (this.checkFile(ftpPath, fileName)) {
            if (!ftpPath.endsWith(fileSeparator)) {
                ftpPath = ftpPath + fileSeparator;
            }
            if (ftpClient.changeWorkingDirectory(ftpPath)) {
                inputStream = ftpClient.retrieveFileStream(fileName);
            }
            ftpClient.changeWorkingDirectory(fileSeparator);
        }
        processStreamClose = true;
        return inputStream;
    }

    //��ȡFTP���ļ�������ʽ����

    public InputStream retrieveFileStream(String ftpPath, String ftpFileName) throws IOException {
        if (!login) return null;
        validateCommandAndClose();
        if (checkFile(ftpPath, ftpFileName)) {
            //������ͬ�ļ����ļ�����������
            return null;
        } else {
            if (!ftpPath.endsWith(fileSeparator)) {
                ftpPath = ftpPath + fileSeparator;
            }
            if (ftpClient.changeWorkingDirectory(ftpPath)) {
                return ftpClient.retrieveFileStream(ftpFileName);
            }
            ftpClient.changeWorkingDirectory(fileSeparator);
        }
        processStreamClose = true;
        return null;
    }


    //ɾ�������е������ļ�,���ÿ���ļ�����ɾ������������ɾ��״̬

    public boolean delFtpFiles(String ftpPath, List<String> ftpFileNameList) throws IOException {
        if (!login) return false;
        validateCommandAndClose();
        if (ftpFileNameList == null) {
            return false;
        }
        boolean allFileDelResult = true;
        for (int i = 0; i < ftpFileNameList.size(); i++) {
            String ftpfileName = ftpFileNameList.get(i);
            boolean delResult = this.delFile(ftpPath, ftpfileName);
            if (!delResult) {
                allFileDelResult = false;
            }
        }
        return allFileDelResult;
    }

    /**
     * ɾ����Ŀ¼
     */
    public void deleteEmptyDirectory(String pathname) {
        if (!login) return;
        validateCommandAndClose();
        try {
            ftpClient.removeDirectory(pathname);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * �г����������ļ���Ŀ¼
     *
     * @param regStr --ƥ���������ʽ
     */
    public void listRemoteFiles(String regStr) {
        if (!login) return;
        validateCommandAndClose();
        try {
            String files[] = ftpClient.listNames(regStr);
            if (files == null || files.length == 0)
                System.out.println("û���κ��ļ�!");
            else {
                for (int i = 0; i < files.length; i++) {
                    System.out.println(files[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * �г�Ftp�������ϵ������ļ���Ŀ¼
     */
    public void listRemoteAllFiles() {
        if (!login) return;
        validateCommandAndClose();
        try {
            String[] names = ftpClient.listNames();
            for (int i = 0; i < names.length; i++) {
                System.out.println(names[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * �ر�����
     */
    public void close() {
        validateCommandAndClose();
        try {
            if (ftpClient != null) {
                ftpClient.disconnect();
                ftpClient = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * ���ô����ļ�������[�ı��ļ����߶������ļ�]
     *
     * @param fileType--BINARY_FILE_TYPE��ASCII_FILE_TYPE
     *
     */
    public void setFileType(int fileType) {
        try {
            ftpClient.setFileType(fileType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ��չʹ��
     *
     * @return ftpClient
     */
    protected FTPClient getFtpClient() {
        if (!this.checkConnected())
            createFtpClient();
        return ftpClient;
    }

    /**
     * ���ӵ�������
     *
     * @return true ���ӷ������ɹ���false ���ӷ�����ʧ��
     */
    public FTPClient createFtpClient() {
        boolean flag = true;
        int reply;
        try {
            ftpClient = new FTPClient();
            ftpClient.setControlEncoding("GB2312");
            ftpClient.setDefaultPort(this.port);
            ftpClient.configure(getFtpConfig());
            if (printLog) {
                PrintCommandListener listener = new PrintCommandListener(new PrintWriter(System.out));
                ftpClient.addProtocolCommandListener(listener);
            }
            ftpClient.connect(this.ip);
            ftpClient.login(this.userName, this.password);
            ftpClient.setDefaultPort(this.port);
            reply = ftpClient.getReplyCode();
            ftpClient.setDataTimeout(12000000);
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                System.err.println("FTP server refused connection.");
                flag = false;
            }
        } catch (SocketException e) {
            flag = false;
            e.printStackTrace();
            System.err.println("��¼ftp������ " + ip + " ʧ��,���ӳ�ʱ��");
        } catch (IOException e) {
            flag = false;
            e.printStackTrace();
            System.err.println("��¼ftp������ " + ip + " ʧ�ܣ�FTP�������޷��򿪣�");
        }
        login = flag;
        return ftpClient;
    }

    /**
     * ���뵽��������ĳ��Ŀ¼��
     *
     * @param directory
     */
    public boolean changeWorkingDirectory(String directory) {
        if (!login) return false;
        validateCommandAndClose();
        boolean result = false;
        try {
            result = ftpClient.changeWorkingDirectory(directory);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    /**
     * ���ص���һ��Ŀ¼
     */
    public void changeToParentDirectory() {
        if (!login) return;
        validateCommandAndClose();
        try {
            ftpClient.changeToParentDirectory();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * �������ļ�
     *
     * @param oldFileName --ԭ�ļ���
     * @param newFileName --���ļ���
     */
    public boolean moveFile(String oldPathName, String oldFileName, String newPathName, String newFileName) {
        if (!login) return false;
        validateCommandAndClose();
        boolean result = false;
        try {
            if (checkFile(oldPathName, oldFileName)) {
                checkAndMakeFtpDir(newPathName);
                if (!oldPathName.endsWith(fileSeparator)) {
                    oldPathName = oldPathName + fileSeparator;
                }
                if (!newPathName.endsWith(fileSeparator)) {
                    newPathName = newPathName + fileSeparator;
                }
            }
            result = ftpClient.rename(oldPathName + oldFileName, newPathName + newFileName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    /**
     * ��鲢�Ҵ���Ŀ¼������еݹ鴦��
     *
     * @param pathName
     * @throws IOException
     */
    private void checkAndMakeFtpDir(String pathName) throws IOException {
        if (!login) return;
        validateCommandAndClose();
        String path = "";
        String[] paths = StringUtils.split(pathName, fileSeparator);
        for (int i = 0; i < paths.length; i++) {
            path += fileSeparator + paths[i];
            if (!changeWorkingDirectory(path)) {
//                System.out.println("into Dir Error:"+path);
                boolean flag = ftpClient.makeDirectory(paths[i]);
                if (!flag) {
                    System.err.println("Make Directory Error! Directory:" + path);
                } else {
                    System.out.println("Make Directory Success! Directory:" + path);
                    checkAndMakeFtpDir(pathName);
                    break;
                }
            } else {
                System.out.println("into Dir Success:" + path);
            }
        }
    }

    /**
     * ����FTP�ͷ��˵�����--һ����Բ�����
     *
     * @return ftpConfig
     */
    private FTPClientConfig getFtpConfig() {
        FTPClientConfig ftpConfig = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        ftpConfig.setServerLanguageCode(FTP.DEFAULT_CONTROL_ENCODING);
        return ftpConfig;
    }

    /**
     * ת��[ISO-8859-1 -> GBK] ��ͬ��ƽ̨��Ҫ��ͬ��ת��
     *
     * @param obj
     * @return ""
     */
    private String iso8859togbk(Object obj) {
        try {
            if (obj == null)
                return "";
            else
                return new String(obj.toString().getBytes("iso-8859-1"), "GBK");
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * �ڷ������ϴ���һ���ļ���
     *
     * @param dir �ļ������ƣ����ܺ��������ַ����� \ ��/ ��: ��* ��?�� "�� <��>...
     */
    public boolean makeDirectory(String dir) {
        if (!login) return false;
        validateCommandAndClose();
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                System.out.println("make Directory " + dir + " succeed");

            } else {

                System.out.println("make Directory " + dir + " false");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean uploadFileByFtpClient(String toFtpPath, String fileName) {
        if (!login) return false;
        validateCommandAndClose();
        boolean result = false;
        FileInputStream in = null;
        try {
            checkAndMakeFtpDir(toFtpPath);
            if (ftpClient.changeWorkingDirectory(toFtpPath)) {
                File file = new File(fileName);
                in = new FileInputStream(file);
                result = ftpClient.storeFile(file.getName(), in);
                ftpClient.changeWorkingDirectory(rootPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
//                if (ftpClient != null)
//                    ftpClient.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FtpOperate ftpOperate = new FtpOperate("172.16.1.129", 21, "anonymous", "pwd");
        ftpOperate.createFtpClient();
        ftpOperate.setFileType(FTP.BINARY_FILE_TYPE);// ���ô���������ļ�
//        try {
//            String[] ftpFiles = ftpOperate.getFileList("/interftp/bsmp/content/receive/20100707/");
//            System.out.println("size:"+ftpFiles.length);
        boolean flag = false;
        try {
            flag = ftpOperate.uploadFile("/dass/sms/", "D:\\home\\iboss\\appdatas\\receive\\20101222\\C03002201012220001.DAT");
//            long size = ftpOperate.getFileSize("/IFS_Dir/receive/20100707/", "C03002201007070001.DAT");
//            System.out.println("size:"+size);

//            ftpOperate.downloadFile("/interftp/bsmp/content/receive/20100707/A0200420100707200001.DAT", "D:\\IFS_Dir\\receive\\20100707\\a.dat");

//        } catch (IOException e) {
//            e.printStackTrace();
//        }
            System.out.println("flag:" + flag);
//  System.err.println(ftpOperate.checkFile("/test/va/", "�½� �ı��ĵ�.txt"));
//            ftpOperate.moveFile("/test/", "�½� �ı��ĵ�.txt", "/test/va/", "�½� �ı��ĵ�.txt");
//            ftpOperate.checkAndMakeFtpDir("/testMakeDir/interftp/receive/20100701");

//            String incrementStr = "11111111111111111111111111111111111111\n";
//            byte[] bytes = incrementStr.getBytes();
//            InputStream inputStream = new ByteArrayInputStream(bytes);
//            ftpOperate.uploadFile("/testMakeDir/", "test.dat", inputStream);
//            inputStream.close();
//
//            long size = ftpOperate.getFileSize("/testMakeDir/", "test.dat");
//            String validateValue = "test.dat" + size + Md5Util.SPARATOR_N;
//            String md5Str = Md5Util.byte2hex(Md5Util.encryptMD5(validateValue.getBytes()));
//            InputStream inputStreamMD5 = new ByteArrayInputStream(md5Str.getBytes());
//            ftpOperate.uploadFile("/testMakeDir/", "test_MD5.dat", inputStreamMD5);
//            ftpOperate.downloadFile("c://receive/20100707/", "/interftp/bsmp/content/receive/20100707/", "A0200420100707200001.MD5");
//            ftpOperate.downloadFile("c://receive/20100707/", "/interftp/bsmp/content/receive/20100707/", "A0200420100707200001.DAT");

//c://receive/20100707/A0200420100707200001.DAT
//true
///interftp/bsmp/content/receive/20100707/A0200420100707200001.DAT

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ftpOperate.close();// �ر�����
        }
    }

    //��ȡFTP���ļ�������ʽ���� add by niuph

    public InputStream readFtpFile2(String ftpPath, String fileName) throws IOException {
        if (!login) return null;
        validateCommandAndClose();
        InputStream inputStream = null;
        //if (this.checkFile(ftpPath, fileName)) {
            if (!ftpPath.endsWith(fileSeparator)) {
                ftpPath = ftpPath + fileSeparator;
            }
            if (ftpClient.changeWorkingDirectory(ftpPath)) {
                inputStream = ftpClient.retrieveFileStream(fileName);
            }
            ftpClient.changeWorkingDirectory(fileSeparator);
       // }
        processStreamClose = true;
        return inputStream;
    }

}