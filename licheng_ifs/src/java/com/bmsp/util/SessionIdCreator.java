package com.bmsp.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Copyright lr.
 * User: Administrator
 * Date: 2008-8-14
 * Time: 17:11:04
 * SessionID创建器
 */
public class SessionIdCreator {
    private static int seqNumber;
    private static String timeStampStr = "";

    /**
     * ID创建器，以毫秒为单位，在同一时间内最后三位数字递增，同步静态方法
     *
     * @return String SessionID
     */
    public static synchronized String createSessionId() {
        return createSessionId("BMP");
    }

    public static synchronized String createSessionId(String prefix) {
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateTime = dateFm.format(new java.util.Date());
        if (dateTime.equals(timeStampStr)) {
            seqNumber++;
        } else {
            seqNumber = 1;
            timeStampStr = dateTime;
        }
        DecimalFormat df = new DecimalFormat("000000");
        return prefix + dateTime + df.format(seqNumber);
    }
}
