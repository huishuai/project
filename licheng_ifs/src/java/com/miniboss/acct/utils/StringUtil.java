package com.miniboss.acct.utils;

import java.io.Serializable;

public class StringUtil implements Serializable {

    private static final long serialVersionUID = 8883058963127863283L;

    /**
     * ��ʶ�ļ���Ȩ��
     */
    public static final String R = "r";
    /**
     * �ļ���ȡ�������0�Ĵ�����Ϣ
     */
    public static final String BUFBITLEN_SIZE_MUST_0 = "bufbitlength size must >= 0";

    /**
     * ����MD5���룬�˷���Ϊ��̬����
     *
     * @param source byte[] �����ֽ�����
     * @return String
     */
    public static String getMD5(byte[] source) {
        String s = null;
        char hexDigits[] = {       // �������ֽ�ת���� 16 ���Ʊ�ʾ���ַ�
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest();          // MD5 �ļ�������һ�� 128 λ�ĳ�������
            // ���ֽڱ�ʾ���� 16 ���ֽ�
            char str[] = new char[16 * 2];   // ÿ���ֽ��� 16 ���Ʊ�ʾ�Ļ���ʹ�������ַ���
            // ���Ա�ʾ�� 16 ������Ҫ 32 ���ַ�
            int k = 0;                                // ��ʾת������ж�Ӧ���ַ�λ��
            for (int i = 0; i < 16; i++) {          // �ӵ�һ���ֽڿ�ʼ���� MD5 ��ÿһ���ֽ�
                // ת���� 16 �����ַ���ת��
                byte byte0 = tmp[i];                 // ȡ�� i ���ֽ�
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  // ȡ�ֽ��и� 4 λ������ת��,
                // >>> Ϊ�߼����ƣ�������λһ������
                str[k++] = hexDigits[byte0 & 0xf];            // ȡ�ֽ��е� 4 λ������ת��
            }
            s = new String(str);                                 // ����Ľ��ת��Ϊ�ַ���

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

}
