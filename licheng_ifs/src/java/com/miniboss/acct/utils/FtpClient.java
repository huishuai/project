package com.miniboss.acct.utils;


import org.apache.commons.net.ftp.FTP; 
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-18
 * Time: 15:43:30
 */
public class FtpClient {
    private String host;
    private int port;
    private String user;
    private String remoteCdrFileRoot;
    private String localFilePath;
    private String remoteFilePath;

    public String getLocalFilePath() {
        return localFilePath;
    }

    public void setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
    }

    public String getRemoteFilePath() {
        return remoteFilePath;
    }

    public void setRemoteFilePath(String remoteFilePath) {
        this.remoteFilePath = remoteFilePath;
    }

    public String getRemoteCdrFileRoot() {
        return remoteCdrFileRoot;
    }

    public void setRemoteCdrFileRoot(String remoteCdrFileRoot) {
        this.remoteCdrFileRoot = remoteCdrFileRoot;
    }

    public FtpClient() {
        port = 21;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public String getYearMonthDay() {
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMdd");
        return getTime(dateFm);
    }

    public String getYearMonthYesToDay() {
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMdd");
        return getYesToDay(dateFm);
    }

    public String getTime() {
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return getYesToDay(dateFm);
    }

    public String getThisTime() {
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return getTime(dateFm);
    }

    public String getYearMonth() {
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMM");
        return getTime(dateFm);
    }

    public String getThreeDayBefore() {
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMddHHmm");
        java.util.Date date = new java.util.Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        return dateFm.format(calendar.getTime());
    }

    private String getTime(SimpleDateFormat dateFm) {
        return dateFm.format(new java.util.Date());
    }

    private String getYesToDay(SimpleDateFormat dateFm) {
        java.util.Date date = new java.util.Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return dateFm.format(calendar.getTime());
    }

    public FtpOperate createFtpClient() {
        FtpOperate ftpOperate = new FtpOperate(host, port, user, password);
//        ftpOperate.setPrintLog(true);  //是否打印FTP日志
        ftpOperate.createFtpClient();
        ftpOperate.setFileType(FTP.BINARY_FILE_TYPE);// 设置传输二进制文件 
        return ftpOperate;
    }

    public static void main(String[] args) {
//        String[] paths = org.apache.commons.lang.StringUtils.split("/home/bsmp/receive/receive", "/");
//        System.err.println(paths.length);

        FtpClient ftpClient = new FtpClient();
        FtpOperate client = ftpClient.createFtpClient();



    }
}