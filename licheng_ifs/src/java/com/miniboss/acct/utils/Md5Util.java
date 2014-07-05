package com.miniboss.acct.utils;

import java.security.MessageDigest;
 
public class Md5Util {
    /**
     * »Ø³µ·ûºÅ
     */
    public static final String SPARATOR_N = "\n";
    public static byte[] encryptMD5(byte[] data) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return md5.digest(data);
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        if (b == null) {
            return hs;
        }
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs;
    }
    
    public static String getMD5(String str){
      return  byte2hex(encryptMD5(str.getBytes()));
    }

    public static void main(String[] args) throws Exception {
        int a = 0x81000001;

        System.out.println("int" + (Integer.MAX_VALUE > a));
        String fileName = "A0300120100507110001";
        String fileSize = "700";
        String n = "\n";
        fileName = fileName + fileSize + n;
        String s = byte2hex(encryptMD5(fileName.getBytes()));
        System.out.println(s);

//        new GetBossData().run();


    }
}