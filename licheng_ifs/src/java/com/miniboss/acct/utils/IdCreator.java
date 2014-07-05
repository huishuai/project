package com.miniboss.acct.utils;
 
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-18
 * Time: 15:00:48
 * ID创建器
 */
public class IdCreator {
    private static Hashtable<String, Creator> creatorHashtable = new Hashtable<String, Creator>();
    private static SimpleDateFormat _dateFm = new SimpleDateFormat("HH");
     public static final String BOSS_FTP_FILE_ID_FORMAT="0000";
    /**
     * 创建ID号码，同一天内ID号码递增 ，同步方法
     *
     * @param key      要创建ID的key
     * @param yyyymmdd 时间参数，年月日
     * @return String
     */
    public static synchronized String formatIndex(String key, String yyyymmdd) {
        Creator creator = null;
        int seqNumber = 1;
        if (!creatorHashtable.containsKey(key)) {
            creator = new Creator();
            creatorHashtable.put(key, creator);
        } else {
            creator = creatorHashtable.get(key);
        }
        if (creator.getTimeStampStr() == null || !creator.getTimeStampStr().equals(yyyymmdd)) {
            creator.setTimeStampStr(yyyymmdd);
            creator.setSeqNumber(1);
        } else if (creator.getTimeStampStr().equals(yyyymmdd)) {
            seqNumber = creator.getSeqNumber() + 1;
            creator.setSeqNumber(seqNumber);
        }
        DecimalFormat df = new DecimalFormat(BOSS_FTP_FILE_ID_FORMAT);
        return yyyymmdd + df.format(seqNumber);
    }

    /**
     * 创建ID号码，同一天内ID号码不变
     *
     * @param yyyymmdd 时间参数，年月日
     * @return String
     */
    public static synchronized String formatIndex(String yyyymmdd) {
        int seqNumber = 1;
        DecimalFormat df = new DecimalFormat(BOSS_FTP_FILE_ID_FORMAT);
        return yyyymmdd + df.format(seqNumber);
    }

    public static  String formatIndex(int index) {
        DecimalFormat df = new DecimalFormat(BOSS_FTP_FILE_ID_FORMAT);
        return df.format(index);
    }

    /**
     * 创建ID号码，同一天内ID号码不变
     *
     * @param yyyymmdd 时间参数，年月日
     * @return String
     */
    public static synchronized String createIdByTime(String yyyymmdd) {
        return yyyymmdd + "0" + _dateFm.format(new java.util.Date());
    }
}
