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
     * 创建文件夹----已测试
     * 存在同名文件夹--Return:true;
     * 存在同名文件----Retrun:false;
     * 创建文件夹失败--Return:false;
     * @param dirStr 要创建的文件夹名称
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
     * 删除文件夹
     * todo:当前方法未测试
     * @param dirStr 要删除的文件夹名称
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
     * 获得本地某个路径下所有文件----已测试
     * (不包括文件夹，不递归获取下级文件夹文件)
     * @param dirPath 目标路径
     * @return 文件集合
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
            /* 日志文件大小最大为100KB */
            File log = new File(logFileName);
            if (log.length() > 102400) {
                if (log.delete())
                    System.out.println("日志文件: " + logFileName + " 已满, 已经自动清空.");
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
                System.out.println("文件已存在，开始写文件 File:" + localPath + File.separator + fileName);
                brafReadFile = new BufferedRandomAccessFile(file, "rw");
                writeFile(is, brafReadFile, file);
            } else {
                System.out.println("文件创建失败 File:" + localPath + File.separator + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("写文件失败 File:" + localPath + File.separator + fileName);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (brafReadFile != null) {
                    brafReadFile.close();
                }
            } catch (IOException e) {
                System.out.println("关闭文件流失败！");
            }
        }
    }

    /**
     * 创建某个文件----已经初步测试
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
     * 将某个流写到某个文件中，从文件末尾开始写
     * @param is 将要写到文件中的流
     * @param brafReadFile 随机写流工具
     * @param _file 被写的文件
     * @throws IOException 异常
     */
    private void writeFile(BufferedInputStream is, BufferedRandomAccessFile brafReadFile, File _file) throws IOException {
        int readLength = 1024;
        byte b[] = new byte[readLength];
        int fileLength = 0;
        int off = (int) _file.length();
//        is.skip(off); //todo:验证它存在的理由
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


//        String str = "发送发送发送的发送发送发送方\n";
//        FileUtil fileUtil = new FileUtil();
//        for (int i = 0; i < 1000000; i++) {
//            fileUtil.writeFileByJava("D:\\home", "a.x", str);
//        }


    }

}
