package com.miniboss.sync.ftp

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-6-30
 * Time: 16:10:31
 * To change this template use File | Settings | File Templates.
 */
public interface FtpBean {

    //todo���ṹ��ˬ�л����ع��ṹ������ʵ��
    public static final String SPLIT = "|";
    public static final String SPLIT_TMP = "|";
    public static final String DEFAULT_CATALOG_ID = "";
    public static final String LINE_END = "\n";
    //�������������
    public static final String SPARATOR_LINE_REGEX = "\\|";


    //ת����ǰ����ΪFTPͬ����־��¼
    public String convertBeanToFtpLog() throws Exception

    //ת��FTPͬ����־��¼Ϊ��ǰ����
    public void convertFtpLogToBean(String ftpLog) throws Exception


}