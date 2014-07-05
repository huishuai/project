package com.miniboss.sync.ftp

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-6-30
 * Time: 16:10:31
 * To change this template use File | Settings | File Templates.
 */
public interface FtpBean {

    //todo：结构不爽有机会重构结构和所有实现
    public static final String SPLIT = "|";
    public static final String SPLIT_TMP = "|";
    public static final String DEFAULT_CATALOG_ID = "";
    public static final String LINE_END = "\n";
    //用于正则的竖线
    public static final String SPARATOR_LINE_REGEX = "\\|";


    //转换当前对象为FTP同步日志记录
    public String convertBeanToFtpLog() throws Exception

    //转换FTP同步日志记录为当前对象
    public void convertFtpLogToBean(String ftpLog) throws Exception


}