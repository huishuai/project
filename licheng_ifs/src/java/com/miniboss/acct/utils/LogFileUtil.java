package com.miniboss.acct.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

/**
 * 暂时用于处理灾难恢复日志文件的相关逻辑
 * Todo:需要针对并发，日志文件解析等进行开发
 */
public class LogFileUtil {

    /**
     * 查找文本文件最后一行文本，忽略空格，换行情况
     *
     * @param path 文件目录
     * @return String   最后一行文本内容，需要根据不同的情况进行转码操作(iso-8859-1 To GB2312)
     */
    public static String getFileLastRecord(String path){
        RandomAccessFile rf = null;
        try {
            rf = new RandomAccessFile(path, "r");
            long len = rf.length();
            if (len == 0)
                return null;
            long start = rf.getFilePointer();
            long nextend = start + len - 1;
            String line;
            rf.seek(nextend);
            int c = -1;
            while (nextend > start) {
                c = rf.read();
                if (c == '\n' || c == '\r') {
                    line = rf.readLine();
                    if (line != null && line.trim().length() != 0) {
                        return line;
                    }
                    nextend--;
                }
                nextend--;
                rf.seek(nextend);
                if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
                    String firstLineStr = rf.readLine();
                    if (firstLineStr != null && firstLineStr.trim().length() != 0)
                        return firstLineStr;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rf != null)
                    rf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据最新同步日志的最后一条记录获得最大主键ID
     * @param path 同步日志最后一条记录
     * @return  long 记录中主键ID
     */
    public static long getLastKeyFromLog(String path){

        String lastLogRecord = getFileLastRecord(path);
        //Todo:Get lastLogRecord by parser
        return 1000000;
    }





    public static void main(String[] args) throws UnsupportedEncodingException {
        String fileLastRecord = LogFileUtil.getFileLastRecord("D:\\workspace\\miniboss\\src\\MiniBoss\\stacktrace.log");
        if (fileLastRecord == null) {
            System.out.println("Result:" + fileLastRecord);
        } else
            System.out.println("Result:" + new String(fileLastRecord.getBytes("iso-8859-1"), "GB2312"));

    }


}
