package com.daocren.server.communication.util;

/**
 * @author Daocren
 */
public class StringUtils {

    /**
     * ���ַ�����Ϊָ�����ȵ��ַ���,����ʹ��\0����
     *
     * @param str
     * @param length
     * @return �ַ�����Ϣ
     */
    public static String fixLength(String str, int length) {
        if (str.length() < length) {

            StringBuffer sb = new StringBuffer();
            sb.append(str);

            for (int i = 0; i < length - str.length(); i++) {
                sb.append("\0");
            }
            return sb.toString();

        } else if (str.length() == length) {
            return str;
        } else
            return str.substring(0, length);

    }

    /**
     * ���ַ�����ת���ɿɶ��ַ�����Ϣ
     * @param buf
     * @return �ɶ��ַ�����Ϣ
     */
    public static String stringify(byte buf[]) {
        StringBuffer sb = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            int h = (buf[i] & 0xf0) >> 4;
            int l = (buf[i] & 0x0f);
            sb.append(new Character((char) ((h > 9) ? 'a' + h - 10 : '0' + h)));
            sb.append(new Character((char) ((l > 9) ? 'a' + l - 10 : '0' + l)));
        }
        return sb.toString();
    }

    /**
     * ��short����ֵת��Ϊ�ַ�����Ϣ
     * @param i
     * @return �ַ�����Ϣ
     */
    public static String short2Str(short i) {

        byte[] bytes = new byte[2];
        bytes[0] = (byte) ((i >> 8) & 0xff);
        bytes[1] = (byte) (i & 0xff);

        return stringify(bytes);

    }


    /**
     * ���ַ�����Ϣת��Ϊbyte����
     * @param src
     * @return byte����
     */
    public static byte[] str2Bytes(String src) {

        byte[] result = new byte[src.length() / 2];

        for (int i = 0; i < result.length; i++) {

            result[i] = (byte) Integer.parseInt(
                    src.substring(2 * i, 2 * i + 2), 16);

        }
        return result;
    }

    /**
     * ��c�����ַ�����д��java�����ַ���
     *
     * @param srcData
     * @return java�����ַ���
     */
    public static String cTrim(byte[] srcData) {
        String tempStr = new String(srcData);
        // �ҵ�c���Ե��ִ�������
        int tailIndex = tempStr.indexOf('\0');
        if (tailIndex != -1) {
            tempStr = tempStr.substring(0, tailIndex);
        }
        //������Ϣ�����ݿ������Ժ󣬻����ո�
        return tempStr.trim();
    }

}