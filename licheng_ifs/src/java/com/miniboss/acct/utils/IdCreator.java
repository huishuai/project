package com.miniboss.acct.utils;
 
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

/**
 * Copyright lr.
 * User: junhai
 * Date: 2008-8-18
 * Time: 15:00:48
 * ID������
 */
public class IdCreator {
    private static Hashtable<String, Creator> creatorHashtable = new Hashtable<String, Creator>();
    private static SimpleDateFormat _dateFm = new SimpleDateFormat("HH");
     public static final String BOSS_FTP_FILE_ID_FORMAT="0000";
    /**
     * ����ID���룬ͬһ����ID������� ��ͬ������
     *
     * @param key      Ҫ����ID��key
     * @param yyyymmdd ʱ�������������
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
     * ����ID���룬ͬһ����ID���벻��
     *
     * @param yyyymmdd ʱ�������������
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
     * ����ID���룬ͬһ����ID���벻��
     *
     * @param yyyymmdd ʱ�������������
     * @return String
     */
    public static synchronized String createIdByTime(String yyyymmdd) {
        return yyyymmdd + "0" + _dateFm.format(new java.util.Date());
    }
}
