package com.daocren.server.communication.util;

/**
 * @author Daocren
 */
public class StringUtils {

    /**
     * 将字符串改为指定长度的字符串,不足使用\0补齐
     *
     * @param str
     * @param length
     * @return 字符串信息
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
     * 将字符数据转换成可读字符串信息
     * @param buf
     * @return 可读字符串信息
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
     * 将short类型值转换为字符串信息
     * @param i
     * @return 字符串信息
     */
    public static String short2Str(short i) {

        byte[] bytes = new byte[2];
        bytes[0] = (byte) ((i >> 8) & 0xff);
        bytes[1] = (byte) (i & 0xff);

        return stringify(bytes);

    }


    /**
     * 将字符串信息转换为byte数组
     * @param src
     * @return byte数组
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
     * 把c风格的字符串改写成java风格的字符串
     *
     * @param srcData
     * @return java风格的字符串
     */
    public static String cTrim(byte[] srcData) {
        String tempStr = new String(srcData);
        // 找到c语言的字串结束符
        int tailIndex = tempStr.indexOf('\0');
        if (tailIndex != -1) {
            tempStr = tempStr.substring(0, tailIndex);
        }
        //个别信息从数据库查出来以后，会后跟空格
        return tempStr.trim();
    }

}