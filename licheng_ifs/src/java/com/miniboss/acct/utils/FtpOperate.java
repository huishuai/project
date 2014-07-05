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

    private String userName;         //FTP 登录用户名
    private String password;         //FTP 登录密码
    private String ip;                     //FTP 服务器地址IP地址
    private int port;                        //FTP 端口
    private FTPClient ftpClient = null; //FTP 客户端代理
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
     * 已测试
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
     * 已测试
     *
     * @return
     */
    public boolean checkConnected() {
        return ftpClient.isConnected();
    }

    /**
     * 已测试
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
     * 已测试
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
     * 上传单个文件，并重命名
     * 已测试
     *
     * @param localFile--本地文件路径
     * @param distFolder--新的文件名,可以命名为空""
     * @return true 上传成功，false 上传失败
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
                System.out.println("上传文件成功！");
            } else {
                System.out.println("上传文件失败！");
            }

        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("本地文件上传失败！", e);
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
     * 已测试
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
     * 已测试
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
     * 上传多个文件
     *
     * @param localFile--本地文件夹路径
     * @return true 上传成功，false 上传失败
     */
    public String uploadManyFile(String localFile) {
        if (!login) return null;
        validateCommandAndClose();
        boolean flag = true;
        StringBuffer strBuf = new StringBuffer();
        try {
            File file = new File(localFile);        // 在此目录中找文件
            File fileList[] = file.listFiles();
            for (File f : fileList) {
                if (f.isDirectory()) {            // 文件夹中还有文件夹
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
            logger.debug("本地文件上传失败！", e);
        }
        return strBuf.toString();
    }

    /**
     * 下载文件
     *
     * @param remoteFileName       --服务器上的文件名
     * @param localFileName--本地文件名
     * @return true 下载成功，false 下载失败
     */
    public boolean downloadFile(String remoteFileName, String localFileName) {
        if (!login) return false;
        validateCommandAndClose();
        boolean flag = true;
        // 下载文件
        BufferedOutputStream buffOut = null;
        try {
            buffOut = new BufferedOutputStream(new FileOutputStream(localFileName));
            flag = ftpClient.retrieveFile(remoteFileName, buffOut);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("本地文件下载失败！", e);
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
        //如果本地临时目录不存在,创建它
        if (!f.exists()) {
            boolean mkResult = f.mkdirs();
            //创建失败,任务结束
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
     * 删除一个文件
     */
    public boolean delFile(String filename) {
        if (!login) return false;
        validateCommandAndClose();
        boolean flag = true;
        try {
            flag = ftpClient.deleteFile(filename);
            if (flag) {
                System.out.println("删除文件成功！");
            } else {
                System.out.println("删除文件失败！");
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
     * 删除目录
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

    //读取FTP上文件流的形式返回

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

    //读取FTP上文件流的形式返回

    public InputStream retrieveFileStream(String ftpPath, String ftpFileName) throws IOException {
        if (!login) return null;
        validateCommandAndClose();
        if (checkFile(ftpPath, ftpFileName)) {
            //存在相同文件名文件，不做处理
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


    //删除集合中的所有文件,针对每个文件进行删除。返回总体删除状态

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
     * 删除空目录
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
     * 列出服务器上文件和目录
     *
     * @param regStr --匹配的正则表达式
     */
    public void listRemoteFiles(String regStr) {
        if (!login) return;
        validateCommandAndClose();
        try {
            String files[] = ftpClient.listNames(regStr);
            if (files == null || files.length == 0)
                System.out.println("没有任何文件!");
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
     * 列出Ftp服务器上的所有文件和目录
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
     * 关闭连接
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
     * 设置传输文件的类型[文本文件或者二进制文件]
     *
     * @param fileType--BINARY_FILE_TYPE、ASCII_FILE_TYPE
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
     * 扩展使用
     *
     * @return ftpClient
     */
    protected FTPClient getFtpClient() {
        if (!this.checkConnected())
            createFtpClient();
        return ftpClient;
    }

    /**
     * 连接到服务器
     *
     * @return true 连接服务器成功，false 连接服务器失败
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
            System.err.println("登录ftp服务器 " + ip + " 失败,连接超时！");
        } catch (IOException e) {
            flag = false;
            e.printStackTrace();
            System.err.println("登录ftp服务器 " + ip + " 失败，FTP服务器无法打开！");
        }
        login = flag;
        return ftpClient;
    }

    /**
     * 进入到服务器的某个目录下
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
     * 返回到上一层目录
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
     * 重命名文件
     *
     * @param oldFileName --原文件名
     * @param newFileName --新文件名
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
     * 检查并且创建目录，会进行递归处理
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
     * 设置FTP客服端的配置--一般可以不设置
     *
     * @return ftpConfig
     */
    private FTPClientConfig getFtpConfig() {
        FTPClientConfig ftpConfig = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        ftpConfig.setServerLanguageCode(FTP.DEFAULT_CONTROL_ENCODING);
        return ftpConfig;
    }

    /**
     * 转码[ISO-8859-1 -> GBK] 不同的平台需要不同的转码
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
     * 在服务器上创建一个文件夹
     *
     * @param dir 文件夹名称，不能含有特殊字符，如 \ 、/ 、: 、* 、?、 "、 <、>...
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
        ftpOperate.setFileType(FTP.BINARY_FILE_TYPE);// 设置传输二进制文件
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
//  System.err.println(ftpOperate.checkFile("/test/va/", "新建 文本文档.txt"));
//            ftpOperate.moveFile("/test/", "新建 文本文档.txt", "/test/va/", "新建 文本文档.txt");
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
            ftpOperate.close();// 关闭连接
        }
    }

    //读取FTP上文件流的形式返回 add by niuph

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