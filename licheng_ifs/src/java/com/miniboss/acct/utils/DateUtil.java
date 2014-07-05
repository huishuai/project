package com.miniboss.acct.utils;

import org.apache.log4j.Logger;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: lutao
 * Date: 2008-1-28
 * Time: 11:53:12
 */
public class DateUtil {

    private static final DateTimeFormatter BASICDATEFORMAT = DateTimeFormat.forPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DOTDATEFORMAT = DateTimeFormat.forPattern("yyyy.MM.dd");
    private static final DateTimeFormatter DOTDATESTRINGFORMAT = DateTimeFormat.forPattern("yyyyMMdd");
    private static final DateTimeFormatter DOTFULLDATESTRINGFORMAT = DateTimeFormat.forPattern("yyyyMMddHHmmss");
    private static final DateTimeFormatter MONTHDAYFORMAT = DateTimeFormat.forPattern("MM-dd");
    private static final DateTimeFormatter HOURDATEFORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH");
    private static final DateTimeFormatter MINUTEDATEFORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter FULLFORMAT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter BASICZHDATEFORMAT = DateTimeFormat.forPattern("yyyy��MM��dd��");
    private static final Logger logger = Logger.getLogger(DateUtil.class);

    public static void main(String[] args) {
        Date date = new DateTime().withDate(2010, 11, 31).withTime(23, 59, 59, 0).toDate();
        Date date2 = new DateTime().withDate(2010, 12, 16).withTime(23, 59, 0, 0).toDate();

        System.out.println("now:"+getDayOfMonth(date));
//        System.out.println("now:"+getDaysDiff(date,date2));
//        System.out.println("now:"+getDateFullStr(date));
//        System.out.println("getNextDay:" + getDateFullStr(getNextDay(date)));
//        System.out.println("getPosDate:" + getDateFullStr(getPosDate(date, 181, Calendar.MILLISECOND)));
//
//        System.out.println("getDateByStrNoMis:" + (DateUtil.getDateByStrNoMis("2008-04-02 20:10:10").getTime() == DateUtil2.getDateByStrNoMis("2008-04-02 20:10:10").getTime()));
//        System.out.println("getDateByHourStr:" + (DateUtil.getDateByHourStr("2008-04-02 20").getTime() == DateUtil2.getDateByHourStr("2008-04-02 20").getTime()));
//        System.out.println("getDateByBasicStr:" + (DateUtil.getDateByBasicStr("2008-04-02").getTime() == DateUtil2.getDateByBasicStr("2008-04-02").getTime()));
//
//        System.out.println("getDateStr:" + (DateUtil.getDateStr(date).equals(DateUtil2.getDateStr(date))));
//        System.out.println("getDateHourStr:" + (DateUtil.getDateHourStr(date).equals(DateUtil2.getDateHourStr(date))));
//        System.out.println("getDateBasicStr:" + (DateUtil.getDateBasicStr(date).equals(DateUtil2.getDateBasicStr(date))));
//        System.out.println("getDateFullStr:" + (DateUtil.getDateFullStr(date).equals(DateUtil2.getDateFullStr(date))));

    }
    public static Date getGrailsParamterDate(Map map,String paramName) {
        String dateStr = (String) map.get(paramName + "_value");
        if(dateStr==null){
            return null;
        }else{
            return getDateByStrNoMis(dateStr);
        }
    }
    /**
     * ��ȡ�����賿ʱ��
     *
     * @return Date
     */
    public static Date getYesterday() {
        DateMidnight dt = new DateMidnight();
        return dt.plusDays(-1).toDate();
    }

    /**
     * ��ȡ�����賿��ʱ��
     *
     * @param date
     * @return Date
     */
    public static Date getDateBegin(Date date) {
        return new DateMidnight(date).toDate();
    }

    /**
     * ��ȡ�ڶ����賿��ʱ��
     *
     * @param date
     * @return Date
     */
    public static Date getNextDay(Date date) {
        DateMidnight dt = new DateMidnight(date);
        return dt.plusDays(1).toDate();
    }

    /**
     * ��ȡָ���µ�һ���賿��ʱ��
     *
     * @param date
     * @return Date
     */
    public static Date getMonthBegin(Date date) {
        DateTime dt = new DateTime(date);
        return new DateMidnight(dt.getYear(), dt.getMonthOfYear(), 1).toDate();
    }

    /**
     * �����ַ�����������
     *
     * @param dateStr yyyy-MM-dd HH:mm:ss ��ʽ���ַ���
     * @return Date
     */
    public static Date getDateByStrNoMis(String dateStr) {
        try {
            return FULLFORMAT.parseDateTime(dateStr).toDate();
        } catch (Exception e) {
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
        return FULLFORMAT.print(new DateTime(date));
    }

    public static String getMonthDayStr(Date date) {
        if (date == null) {
            return null;
        }
        return MONTHDAYFORMAT.print(new DateTime(date));
    }

    /**
     * ��ȡ�¸��µĵ�һ��
     *
     * @param date
     * @return Date
     */
    public static Date getNextMonthFirstDay(Date date) {
        DateTime dt = new DateTime(date);
        return new DateMidnight(dt.getYear(), dt.getMonthOfYear(), 1).plusMonths(1).toDate();
    }

    /**
     * ��ȡ�ϸ��µ�һ��
     *
     * @param date
     * @return Date
     */
    public static Date getPreMonthFirstDay(Date date) {
        DateTime dt = new DateTime(date);
        return new DateMidnight(dt.getYear(), dt.getMonthOfYear(), 1).plusMonths(-1).toDate();
    }

    /**
     * ��ȡ yyyy-MM-dd HH ��ʽ�������ַ���
     *
     * @param date
     * @return String
     */
    public static String getDateHourStr(Date date) {
        return HOURDATEFORMAT.print(new DateTime(date));
    }

    /**
     * ���� yyyy-MM-dd HH ��ʽ�ַ�����ȡ����
     *
     * @param dateStr
     * @return Date
     */
    public static Date getDateByHourStr(String dateStr) {
        try {
            return HOURDATEFORMAT.parseDateTime(dateStr).toDate();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

        /**
     * return yyyy-MM-dd HH:mm
     * @param date
     * @return
     */
    public static String getDateMinuteStr(Date date) {
        return MINUTEDATEFORMAT.print(new DateTime(date));
    }
     /**
     * return yyyyMMdd
     * @param date
     * @return
     */
    public static String getDateDayStr(Date date) {
        return DOTDATESTRINGFORMAT.print(new DateTime(date));
    }

    /**
     * return 'YYYYMMDDHH24MISS'
     * @param date
     * @return
     */
    public static String getFullDateDayStr(Date date) {
        return DOTFULLDATESTRINGFORMAT.print(new DateTime(date));
    }

    /**
     * parse yyyy-MM-dd HH:mm to date
     * @param dateStr
     * @return
     */
    public static Date getDateByMinuteStr(String dateStr) {
        try {
            return MINUTEDATEFORMAT.parseDateTime(dateStr).toDate();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
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
        return new DateTime(date).getHourOfDay();
    }

    /**
     * ��ȡָ�����ڵ�Сʱ
     *
     * @param date
     * @return Date
     */
    public static Date getDateByHour(Date date) {
        DateTime dt = new DateTime(date);
        return new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), dt.getHourOfDay(), 0, 0, 0).toDate();
    }

    /**
     * ��ȡ��һ��Сʱ
     *
     * @param date
     * @return Date
     */
    public static Date getPreHour(Date date) {
        DateTime dt = new DateTime(date).plusHours(-1);
        return new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), dt.getHourOfDay(), 0, 0, 0).toDate();
    }

    /**
     * ��ȡ��һ��Сʱ
     *
     * @param date
     * @return Date
     */
    public static Date getNextHour(Date date) {
        DateTime dt = new DateTime(date).plusHours(1);
        return new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), dt.getHourOfDay(), 0, 0, 0).toDate();
    }

    /**
     * ��ȡ��ǰʱ��Ķ��ٷ���֮ǰ
     */
    public static Date getPreMinute(Date date, int minute, int second) {
        DateTime dt = new DateTime(date).plusMinutes(0 - minute).plusSeconds(0 - second);
        return dt.withMillisOfSecond(0).toDate();
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
        DateTime dt = new DateTime(date).plusMinutes(minute).plusSeconds(second);
        return dt.withMillisOfSecond(0).toDate();
    }


    /**
     * ��ȡ yyyy-MM-dd ��ʽ�������ַ���
     *
     * @param date
     * @return String
     */
    public static String getDateBasicStr(Date date) {
        return BASICDATEFORMAT.print(new DateTime(date));
    }

    public static String getDateBasicZHStr(Date date) {
        return BASICZHDATEFORMAT.print(new DateTime(date));
    }

     /**
     * ��ȡ yyyy,MM.dd ��ʽ�������ַ���
     *
     * @param date
     * @return String
     */
    public static String getDotDateStr(Date date) {
        return DOTDATEFORMAT.print(new DateTime(date));
    }

    /**
     * �� yyyy.MM.dd ��ʽ���ַ�����ȡ����
     *
     * @param dateStr
     * @return Date
     */
    public static Date getDotDateByStr(String dateStr) {
        try {
            return DOTDATEFORMAT.parseDateTime(dateStr).toDate();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * �� yyyy-MM-dd ��ʽ���ַ�����ȡ����
     *
     * @param dateStr
     * @return Date
     */
    public static Date getDateByBasicStr(String dateStr) {
        try {
            return BASICDATEFORMAT.parseDateTime(dateStr).toDate();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * ��ȡǰ����
     *
     * @param date
     * @param preNum ����ȡǰ���죬����ȡ����
     * @return Date
     */
    public static Date getPreDateByNum(Date date, int preNum) {
        return new DateTime(date).plusDays(0 - preNum).toDate();
    }

     /**
     * ��ȡǰ����
     *
     * @param startDate
     * @param nextMonths ����ȡǰ���£�����ȡ����
     * @return Date
     */
    public static Date getPreDateByMonth(Date startDate,int nextMonths){
        return new DateTime(startDate).plusMonths(0 - nextMonths).toDate();
    }

    /**
     * �õ�����ĳ��ʱ��
     *
     * @param hour �趨�����Сʱ��
     * @return date
     */
    public static Date getHourDateByDate(Date date, int hour) {

        DateTime dt = new DateTime(date).withHourOfDay(hour);
        return dt.withTime(dt.getHourOfDay(), 0, 0, 0).toDate();
    }

    /**
     * ��ȡ�ڶ����ʱ��,������һ,ʱ����hour,��������
     *
     * @param date ��������
     * @param hour ��һ��ʱ��
     * @return Date
     */
    public static Date getNextDayOnHour(Date date, int hour) {
        DateTime dt = new DateTime(date).plusDays(1).withHourOfDay(hour);
        return dt.withTime(dt.getHourOfDay(), 0, 0, 0).toDate();
    }

    /**
     * ��ȡ���ʱ��,���ṩ��ȡ����,posType֧�֣�Calendar.YEAR��Calendar.MONTH,Calendar.DAY_OF_MONTH,
     * Calendar.HOUR_OF_DAY,Calendar.MINUTE,Calendar.MILLISECOND
     * @param date     �޸�ʱ��
     * @param posCount �����
     * @param posType  �����λ
     * @return �޸ĺ�ʱ��
     */
    public static Date getPosDate(Date date, int posCount, int posType) {
        DateTime dt = new DateTime(date);
        switch (posType) {
            case Calendar.DAY_OF_MONTH:
                dt = dt.plusDays(posCount);
                break;
            case Calendar.MONTH:
                dt = dt.plusMonths(posCount);
                break;
            case Calendar.YEAR:
                dt = dt.plusYears(posCount);
                break;
            case Calendar.HOUR_OF_DAY:
                dt = dt.plusHours(posCount);
                break;
            case Calendar.MINUTE:
                dt = dt.plusMinutes(posCount);
                break;
            case Calendar.MILLISECOND:
                dt = dt.plusSeconds(posCount);
                break;
            default:
                break;
        }
        return dt.toDate();
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
        return new DateTime(dDate).dayOfMonth().get();
    }

    /**
     * ��ȡ���ڵ��·�
     *
     * @param dDate
     * @return int
     */
    public static int getMonthOfDate(Date dDate) {
        return new DateTime(dDate).getMonthOfYear() - 1;
    }

    /**
     * ��ȡ���ڵ����
     *
     * @param dDate
     * @return int
     */
    public static int getYearOfDate(Date dDate) {
        return new DateTime(dDate).year().get();
    }

    /**
     * ���� yyyy-MM-dd HH:mm:ss ��ʽ��ȡ�����ַ���
     *
     * @param date
     * @return String
     */
    public static String getDateFullStr(Date date) {
        return FULLFORMAT.print(new DateTime(date));
    }


    /**
     * ��������ʱ��֮���������ֵ
     *
     * @param startDate ��ʼʱ��
     * @param endDate   ����ʱ��
     * @return long ����
     */
    public static long getDaysDiff(Date startDate, Date endDate) {
        startDate = removeHHmmss(startDate);
        endDate = removeHHmmss(endDate);
        long diff = endDate.getTime() - startDate.getTime();
        return diff / (1000 * 60 * 60 * 24);
    }

    /**
     * ȡ��ĳ�µĵ����һ��
     * @param date ���ڲ���
     * @return Date ��ĩʱ��
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);//�³�
        cal.add(Calendar.MONTH, 1);//����
        cal.add(Calendar.DATE, -1);//�������һ��
        return cal.getTime();
    }

    /**
     * ��ʱ���ʱ����������0
     * @param date
     * @return
     */
    public static Date removeHHmmss(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * ��ȡ���ڵ����
     *
     * @param dDate
     * @return int
     */
    public static int getYear(Date dDate) {
        return new DateTime(dDate).year().get();
    }
  /**
     * ��ȡ���ڵ��·�
     *
     * @param dDate
     * @return int
     */
    public static int getMonth(Date dDate) {
        return new DateTime(dDate).getMonthOfYear();
    }

    //��ȡ����ʱ����������ֵ
    public static Date getMaxDate(Date dateX,Date dateY){
        if(dateX==null){
            return dateY;
        }
        if(dateY==null){
            return dateX;
        }
        return dateX.after(dateY)?dateX:dateY;

    }
}
