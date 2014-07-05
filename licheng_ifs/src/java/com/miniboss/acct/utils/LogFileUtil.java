package com.miniboss.acct.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

/**
 * ��ʱ���ڴ������ѻָ���־�ļ�������߼�
 * Todo:��Ҫ��Բ�������־�ļ������Ƚ��п���
 */
public class LogFileUtil {

    /**
     * �����ı��ļ����һ���ı������Կո񣬻������
     *
     * @param path �ļ�Ŀ¼
     * @return String   ���һ���ı����ݣ���Ҫ���ݲ�ͬ���������ת�����(iso-8859-1 To GB2312)
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
                if (nextend == 0) {// ���ļ�ָ�������ļ���ʼ���������һ��
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
     * ��������ͬ����־�����һ����¼����������ID
     * @param path ͬ����־���һ����¼
     * @return  long ��¼������ID
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
