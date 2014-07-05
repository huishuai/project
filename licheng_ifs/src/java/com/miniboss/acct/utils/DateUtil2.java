package com.miniboss.acct.utils;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: lutao
 * Date: 2008-1-28
 * Time: 11:53:12
 */
public class DateUtil2 {

    private static final SimpleDateFormat DOTDATEFORMAT = new SimpleDateFormat("yyyy.MM.dd");
    private static final SimpleDateFormat BASICDATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat HOURDATEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH");
    private static final DateFormat FULLFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final Logger logger = Logger.getLogger(DateUtil.class);

    /**
     * ��ȡ�����賿ʱ��
     *
     * @return Date
     */
    public static Date getYesterday() {
        Date _date = getPosDate(new Date(), -1, Calendar.DAY_OF_MONTH);
        return DateUtils.truncate(_date, Calendar.DAY_OF_MONTH);
    }

    /**
     * ��ȡ�����賿��ʱ��
     *
     * @param date
     * @return Date
     */
    public static Date getDateBegin(Date date) {
        return DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * ��ȡ�ڶ����賿��ʱ��
     *
     * @param date
     * @return Date
     */
    public static Date getNextDay(Date date) {
        Date _date = getPosDate(date, 1, Calendar.DAY_OF_MONTH);
        return DateUtils.truncate(_date, Calendar.DAY_OF_MONTH);
    }

    /**
     * ��ȡָ���µ�һ���賿��ʱ��
     *
     * @param date
     * @return Date
     */
    public static Date getMonthBegin(Date date) {
        return DateUtils.truncate(date, Calendar.MONTH);
    }

    /**
     * �����ַ�����������
     *
     * @param dateStr yyyy-MM-dd HH:mm:ss ��ʽ���ַ���
     * @return Date
     */
    public static Date getDateByStrNoMis(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * �������ڻ�á�yyyy-MM-dd HH:mm:ss���ַ���
     *
     * @param date
     * @return String
     */
    public static String getDateStr(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * ��ȡ�¸��µĵ�һ��
     *
     * @param date
     * @return Date
     */
    public static Date getNextMonthFirstDay(Date date) {
        Date _date = DateUtils.truncate(date, Calendar.MONTH);
        return getPosDate(_date, 1, Calendar.MONTH);
    }

    /**
     * ��ȡ�ϸ��µ�һ��
     *
     * @param date
     * @return Date
     */
    public static Date getPreMonthFirstDay(Date date) {
        Date _date = DateUtils.truncate(date, Calendar.MONTH);
        return getPosDate(_date, -1, Calendar.MONTH);
    }

    /**
     * ��ȡ yyyy-MM-dd HH ��ʽ�������ַ���
     *
     * @param date
     * @return String
     */
    public static String getDateHourStr(Date date) {
        return HOURDATEFORMAT.format(date);
    }

    /**
     * ���� yyyy-MM-dd HH ��ʽ�ַ�����ȡ����
     *
     * @param dateStr
     * @return Date
     */
    public static Date getDateByHourStr(String dateStr) {
        return getDateByString(dateStr, HOURDATEFORMAT);
    }

    /**
     * �õ�Сʱ��
     *
     * @param date
     * @return int
     */
    public static int getHour(Date date) {
        if (date == null) {
            return -1;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * ��ȡָ�����ڵ�Сʱ
     *
     * @param date
     * @return Date
     */
    public static Date getDateByHour(Date date) {
        return DateUtils.truncate(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * ��ȡ��һ��Сʱ
     *
     * @param date
     * @return Date
     */
    public static Date getPreHour(Date date) {
        Date _date = getPosDate(date, -1, Calendar.HOUR_OF_DAY);
        return DateUtils.truncate(_date, Calendar.HOUR_OF_DAY);
    }

    /**
     * ��ȡ��һ��Сʱ
     *
     * @param date
     * @return Date
     */
    public static Date getNextHour(Date date) {
        Date _date = getPosDate(date, 1, Calendar.HOUR_OF_DAY);
        return DateUtils.truncate(_date, Calendar.HOUR_OF_DAY);
    }

    /**
     * ��ȡ��ǰʱ��Ķ��ٷ���֮ǰ
     */
    public static Date getPreMinute(Date date, int minute, int second) {
        Date _date = getPosDate(date, 0 - minute, Calendar.MINUTE);
        _date = getPosDate(_date, 0 - second, Calendar.SECOND);
        return DateUtils.truncate(_date, Calendar.SECOND);
    }

    /**
     * ��ȡ���ڵĶ��ٷ���,������֮��
     *
     * @param date
     * @param minute
     * @param second
     * @return Date
     */
    public static Date getAfterMinute(Date date, int minute, int second) {
        Date _date = getPosDate(date, minute, Calendar.MINUTE);
        _date = getPosDate(_date, second, Calendar.SECOND);
        return DateUtils.truncate(_date, Calendar.SECOND);
    }

    /**
     * ����ָ����ʽ�������ַ�����������
     *
     * @param dateStr    �����ַ���
     * @param dateFormat ���ڸ�ʽ
     * @return Date
     */
    private static Date getDateByString(String dateStr, DateFormat dateFormat) {
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * ���ƶ�OC4J��־�����ַ�ת�������ڶ���
     *
     * @param dateStr
     * @return Date
     */
    public static Date getLogDateByString(String dateStr) {
        try {
            DateFormat MONLOGDATEFORMAT = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.US);
            return MONLOGDATEFORMAT.parse(dateStr);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

     /**
     * ��ȡ yyyy,MM.dd ��ʽ�������ַ���
     *
     * @param date
     * @return String
     */
    public static String getDotDateStr(Date date) {
        return DOTDATEFORMAT.format(date);
    }

    /**
     * �� yyyy.MM.dd ��ʽ���ַ�����ȡ����
     *
     * @param dateStr
     * @return Date
     */
    public static Date getDotDateByStr(String dateStr) {
        return getDateByString(dateStr, DOTDATEFORMAT);
    }

    /**
     * ��ȡ yyyy-MM-dd ��ʽ�������ַ���
     *
     * @param date
     * @return String
     */
    public static String getDateBasicStr(Date date) {
        return BASICDATEFORMAT.format(date);
    }

    /**
     * �� yyyy-MM-dd ��ʽ���ַ�����ȡ����
     *
     * @param dateStr
     * @return Date
     */
    public static Date getDateByBasicStr(String dateStr) {
        return getDateByString(dateStr, BASICDATEFORMAT);
    }

    /**
     * ��ȡǰ����
     *
     * @param date
     * @param preNum ����ȡǰ���죬����ȡ����
     * @return Date
     */
    public static Date getPreDateByNum(Date date, int preNum) {
        return getPosDate(date, 0 - preNum, Calendar.DAY_OF_MONTH);
    }

    /**
     * �õ�����ĳ��ʱ��
     *
     * @param hour �趨�����Сʱ��
     * @return date
     */
    public static Date getHourDateByDate(Date date, int hour) {
        Date retDate = setTime(date, hour, Calendar.HOUR_OF_DAY);
        return DateUtils.truncate(retDate, Calendar.HOUR_OF_DAY);
    }

    /**
     * ��ȡ�ڶ����ʱ��,������һ,ʱ����hour,��������
     *
     * @param date ��������
     * @param hour ��һ��ʱ��
     * @return Date
     */
    public static Date getNextDayOnHour(Date date, int hour) {
        Date _date = getPosDate(date, 1, Calendar.DAY_OF_MONTH);
        _date = setTime(_date, hour, Calendar.HOUR_OF_DAY);
        return DateUtils.truncate(_date, Calendar.HOUR_OF_DAY);
    }

    /**
     * ��ȡ���ʱ��,���ṩ��ȡ����
     *
     * @param date     �޸�ʱ��
     * @param posCount �����
     * @param posType  �����λ
     * @return �޸ĺ�ʱ��
     */
    public static Date getPosDate(Date date, int posCount, int posType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (posCount != 0) {
            calendar.add(posType, posCount);
        }
        return calendar.getTime();
    }

    /**
     * ����ĳ��ʱ�䵥λ��ʱ��
     *
     * @param date
     * @param value
     * @param type
     * @return �޸ĺ�ʱ��
     */
    private static Date setTime(Date date, int value, int type) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (type > 0) {
            calendar.set(type, value);
        }
        return calendar.getTime();
    }

    /**
     * ��ȡ���ڵ���
     *
     * @param dDate
     * @return int
     */
    public static int getDayOfMonth(Date dDate) {
        if (dDate == null) {
            return -1;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dDate);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * ��ȡ���ڵ��·�
     *
     * @param dDate
     * @return int
     */
    public static int getMonthOfDate(Date dDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dDate);
        return cal.get(Calendar.MONTH);
    }

    /**
     * ��ȡ���ڵ����
     *
     * @param dDate
     * @return int
     */
    public static int getYearOfDate(Date dDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dDate);
        return cal.get(Calendar.YEAR);
    }

    /**
     * ���� yyyy-MM-dd HH:mm:ss ��ʽ��ȡ�����ַ���
     *
     * @param date
     * @return String
     */
    public static String getDateFullStr(Date date) {
        return FULLFORMAT.format(date);
    }


    /**
     * ��������ʱ��֮���������ֵ
     *
     * @param startDate ��ʼʱ��
     * @param endDate   ����ʱ��
     * @return long ����
     */
    public static long getDaysDiff(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        return diff / (1000 * 60 * 60 * 24);
    }


    public static void main(String[] args) {

        Date startDate = getDateByBasicStr("0050-02-11 10:10:10");
        Date endDate = getDateByBasicStr("2047-02-11 10:10:10");
//        Date startDate = getDateByStrNoMis("2009-06-30 23:59:59");
//        Date endDate = getDateByStrNoMis("2009-08-1  23:59:59");


        long day = getDaysDiff(startDate, endDate);

        Date date = getPosDate(new Date(), 50, Calendar.YEAR);

        System.out.println("dayCount��" + date);



    }
}
