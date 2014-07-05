package com.miniboss.acct.utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-7-2
 * Time: 18:20:41
 * To change this template use File | Settings | File Templates.
 */
public class FileUtil {

    /**
     * �����ļ���----�Ѳ���
     * ����ͬ���ļ���--Return:true;
     * ����ͬ���ļ�----Retrun:false;
     * �����ļ���ʧ��--Return:false;
     * @param dirStr Ҫ�������ļ�������
     * @return boolean
     */
    public static boolean mkDir(String dirStr) {
        File fileDir = new File(dirStr);
        boolean result = false;
        if (!fileDir.exists()) {
            if (fileDir.mkdirs())
                return true;
        } else {
            if (fileDir.isDirectory())
                return true;
        }
        return result;
    }

    /**
     * ɾ���ļ���
     * todo:��ǰ����δ����
     * @param dirStr Ҫɾ�����ļ�������
     * @return boolean
     */
    public static boolean delDir(String dirStr) {
        File rmDir = new File(dirStr);
        if (rmDir.isDirectory() && rmDir.exists()) {
            String[] fileList = rmDir.list();
            for (String aFileList : fileList) {
                String subFile = dirStr + File.separator + aFileList;
                File tmp = new File(subFile);
                if (tmp.isFile()) {
                    tmp.delete();
                } else if (tmp.isDirectory()) {
                    delDir(subFile);
                } else {
                    System.out.println("Delete Something Err!");
                    return false;
                }
            }
            rmDir.delete();
        } else {
            return false;
        }
        return true;
    }

    /**
     * ��ñ���ĳ��·���������ļ�----�Ѳ���
     * (�������ļ��У����ݹ��ȡ�¼��ļ����ļ�)
     * @param dirPath Ŀ��·��
     * @return �ļ�����
     *
     */
    public static List<File> getFileListFromDir(String dirPath) {
        List<File> fileList = new ArrayList<File>();
        File filePath = new File(dirPath);
        if (filePath.exists() && filePath.isDirectory()) {
            File[] files = filePath.listFiles();
            for (File someFile : files) {
                if (someFile.isFile())
                    fileList.add(someFile);
            }
        }
        return fileList;
    }

    public void writeLog(String logFileName, String content) {
        try {
            /* ��־�ļ���С���Ϊ100KB */
            File log = new File(logFileName);
            if (log.length() > 102400) {
                if (log.delete())
                    System.out.println("��־�ļ�: " + logFileName + " ����, �Ѿ��Զ����.");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ");
            RandomAccessFile raf = new RandomAccessFile(logFileName, "rw");
            String contents = sdf.format(new Date()) + content + "\r\n";
            raf.seek(raf.length());
            raf.write(contents.getBytes("GBK"));
            raf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeSyncDataToFileFoot(String localPath, String fileName, String writeLog) {

        BufferedInputStream is = null;
        BufferedRandomAccessFile brafReadFile = null;
        String writeFileCharset = "GB2312";
        try {
            byte[] bytes = writeLog.getBytes(writeFileCharset);
            InputStream inputStream = new ByteArrayInputStream(bytes);
            is = new BufferedInputStream(inputStream);
            File file = createFile(localPath, fileName);
            if (file != null) {
                System.out.println("�ļ��Ѵ��ڣ���ʼд�ļ� File:" + localPath + File.separator + fileName);
                brafReadFile = new BufferedRandomAccessFile(file, "rw");
                writeFile(is, brafReadFile, file);
            } else {
                System.out.println("�ļ�����ʧ�� File:" + localPath + File.separator + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("д�ļ�ʧ�� File:" + localPath + File.separator + fileName);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (brafReadFile != null) {
                    brafReadFile.close();
                }
            } catch (IOException e) {
                System.out.println("�ر��ļ���ʧ�ܣ�");
            }
        }
    }

    /**
     * ����ĳ���ļ�----�Ѿ���������
     * @param localPath
     * @param fileName
     * @return
     * @throws java.io.IOException
     */
    public File createFile(String localPath, String fileName) throws IOException {
        if (!localPath.trim().endsWith(File.separator))
            localPath += File.separator;
        File file = new File(localPath + fileName);
        if (!file.exists()) {
            boolean mkDirFlag = mkDir(localPath);
            if (mkDirFlag) {
                boolean createFileFlag = file.createNewFile();
                if (!createFileFlag) {
                    System.out.println("Create File Error!FilePath:" + localPath + fileName);
                    return null;
                }
            } else {
                System.out.println("Create Dir Error!Dir:" + localPath);
                return null;
            }
        }
        return file;
    }

    /**
     * ��ĳ����д��ĳ���ļ��У����ļ�ĩβ��ʼд
     * @param is ��Ҫд���ļ��е���
     * @param brafReadFile ���д������
     * @param _file ��д���ļ�
     * @throws IOException �쳣
     */
    private void writeFile(BufferedInputStream is, BufferedRandomAccessFile brafReadFile, File _file) throws IOException {
        int readLength = 1024;
        byte b[] = new byte[readLength];
        int fileLength = 0;
        int off = (int) _file.length();
//        is.skip(off); //todo:��֤�����ڵ�����
        brafReadFile.seek(off);
        while ((fileLength = is.read(b)) > 0) {
            if (fileLength < readLength) {
                byte s[] = new byte[fileLength];
                for (int i = 0; i < fileLength; i++) {
                    s[i] = b[i];
                }
                brafReadFile.write(s);
            } else {
                brafReadFile.write(b);
            }
        }
    }

    public static void main(String[] args) {


//        String str = "���ͷ��ͷ��͵ķ��ͷ��ͷ��ͷ�\n";
//        FileUtil fileUtil = new FileUtil();
//        for (int i = 0; i < 1000000; i++) {
//            fileUtil.writeFileByJava("D:\\home", "a.x", str);
//        }


    }

}
