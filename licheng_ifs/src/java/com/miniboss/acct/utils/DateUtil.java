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
    private static final DateTimeFormatter BASICZHDATEFORMAT = DateTimeFormat.forPattern("yyyy年MM月dd日");
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
     * 获取昨天凌晨时间
     *
     * @return Date
     */
    public static Date getYesterday() {
        DateMidnight dt = new DateMidnight();
        return dt.plusDays(-1).toDate();
    }

    /**
     * 获取当天凌晨的时间
     *
     * @param date
     * @return Date
     */
    public static Date getDateBegin(Date date) {
        return new DateMidnight(date).toDate();
    }

    /**
     * 获取第二天凌晨的时间
     *
     * @param date
     * @return Date
     */
    public static Date getNextDay(Date date) {
        DateMidnight dt = new DateMidnight(date);
        return dt.plusDays(1).toDate();
    }

    /**
     * 获取指定月第一天凌晨的时间
     *
     * @param date
     * @return Date
     */
    public static Date getMonthBegin(Date date) {
        DateTime dt = new DateTime(date);
        return new DateMidnight(dt.getYear(), dt.getMonthOfYear(), 1).toDate();
    }

    /**
     * 根据字符串生成日期
     *
     * @param dateStr yyyy-MM-dd HH:mm:ss 格式的字符串
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
     * 根据日期获得“yyyy-MM-dd HH:mm:ss”字符串
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
     * 获取下个月的第一天
     *
     * @param date
     * @return Date
     */
    public static Date getNextMonthFirstDay(Date date) {
        DateTime dt = new DateTime(date);
        return new DateMidnight(dt.getYear(), dt.getMonthOfYear(), 1).plusMonths(1).toDate();
    }

    /**
     * 获取上个月第一天
     *
     * @param date
     * @return Date
     */
    public static Date getPreMonthFirstDay(Date date) {
        DateTime dt = new DateTime(date);
        return new DateMidnight(dt.getYear(), dt.getMonthOfYear(), 1).plusMonths(-1).toDate();
    }

    /**
     * 获取 yyyy-MM-dd HH 根式的日期字符串
     *
     * @param date
     * @return String
     */
    public static String getDateHourStr(Date date) {
        return HOURDATEFORMAT.print(new DateTime(date));
    }

    /**
     * 根据 yyyy-MM-dd HH 根式字符串获取日期
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
     * 得到小时数
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
     * 截取指定日期到小时
     *
     * @param date
     * @return Date
     */
    public static Date getDateByHour(Date date) {
        DateTime dt = new DateTime(date);
        return new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), dt.getHourOfDay(), 0, 0, 0).toDate();
    }

    /**
     * 获取上一个小时
     *
     * @param date
     * @return Date
     */
    public static Date getPreHour(Date date) {
        DateTime dt = new DateTime(date).plusHours(-1);
        return new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), dt.getHourOfDay(), 0, 0, 0).toDate();
    }

    /**
     * 获取下一个小时
     *
     * @param date
     * @return Date
     */
    public static Date getNextHour(Date date) {
        DateTime dt = new DateTime(date).plusHours(1);
        return new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), dt.getHourOfDay(), 0, 0, 0).toDate();
    }

    /**
     * 获取当前时间的多少分钟之前
     */
    public static Date getPreMinute(Date date, int minute, int second) {
        DateTime dt = new DateTime(date).plusMinutes(0 - minute).plusSeconds(0 - second);
        return dt.withMillisOfSecond(0).toDate();
    }

    /**
     * 获取日期的多少分钟,多少秒之后
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
     * 获取 yyyy-MM-dd 格式的日期字符串
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
     * 获取 yyyy,MM.dd 格式的日期字符串
     *
     * @param date
     * @return String
     */
    public static String getDotDateStr(Date date) {
        return DOTDATEFORMAT.print(new DateTime(date));
    }

    /**
     * 由 yyyy.MM.dd 格式的字符串获取日期
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
     * 由 yyyy-MM-dd 格式的字符串获取日期
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
     * 获取前几天
     *
     * @param date
     * @param preNum 正数取前几天，负数取后几天
     * @return Date
     */
    public static Date getPreDateByNum(Date date, int preNum) {
        return new DateTime(date).plusDays(0 - preNum).toDate();
    }

     /**
     * 获取前几月
     *
     * @param startDate
     * @param nextMonths 正数取前几月，负数取后几月
     * @return Date
     */
    public static Date getPreDateByMonth(Date startDate,int nextMonths){
        return new DateTime(startDate).plusMonths(0 - nextMonths).toDate();
    }

    /**
     * 得到当天某个时刻
     *
     * @param hour 设定当天的小时数
     * @return date
     */
    public static Date getHourDateByDate(Date date, int hour) {

        DateTime dt = new DateTime(date).withHourOfDay(hour);
        return dt.withTime(dt.getHourOfDay(), 0, 0, 0).toDate();
    }

    /**
     * 获取第二天的时间,天数加一,时间用hour,其余清零
     *
     * @param date 基础日期
     * @param hour 下一天时间
     * @return Date
     */
    public static Date getNextDayOnHour(Date date, int hour) {
        DateTime dt = new DateTime(date).plusDays(1).withHourOfDay(hour);
        return dt.withTime(dt.getHourOfDay(), 0, 0, 0).toDate();
    }

    /**
     * 获取间隔时间,并提供截取功能,posType支持：Calendar.YEAR，Calendar.MONTH,Calendar.DAY_OF_MONTH,
     * Calendar.HOUR_OF_DAY,Calendar.MINUTE,Calendar.MILLISECOND
     * @param date     修改时间
     * @param posCount 间隔数
     * @param posType  间隔单位
     * @return 修改后时间
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
     * 获取日期的天
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
     * 获取日期的月份
     *
     * @param dDate
     * @return int
     */
    public static int getMonthOfDate(Date dDate) {
        return new DateTime(dDate).getMonthOfYear() - 1;
    }

    /**
     * 获取日期的年份
     *
     * @param dDate
     * @return int
     */
    public static int getYearOfDate(Date dDate) {
        return new DateTime(dDate).year().get();
    }

    /**
     * 根据 yyyy-MM-dd HH:mm:ss 格式获取日期字符串
     *
     * @param date
     * @return String
     */
    public static String getDateFullStr(Date date) {
        return FULLFORMAT.print(new DateTime(date));
    }


    /**
     * 计算两个时间之间的天数差值
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return long 天数
     */
    public static long getDaysDiff(Date startDate, Date endDate) {
        startDate = removeHHmmss(startDate);
        endDate = removeHHmmss(endDate);
        long diff = endDate.getTime() - startDate.getTime();
        return diff / (1000 * 60 * 60 * 24);
    }

    /**
     * 取得某月的的最后一天
     * @param date 日期参数
     * @return Date 月末时间
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);//月初
        cal.add(Calendar.MONTH, 1);//下月
        cal.add(Calendar.DATE, -1);//上月最后一天
        return cal.getTime();
    }

    /**
     * 将时间的时分秒数据清0
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
     * 获取日期的年份
     *
     * @param dDate
     * @return int
     */
    public static int getYear(Date dDate) {
        return new DateTime(dDate).year().get();
    }
  /**
     * 获取日期的月份
     *
     * @param dDate
     * @return int
     */
    public static int getMonth(Date dDate) {
        return new DateTime(dDate).getMonthOfYear();
    }

    //获取请求时间参数中最大值
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
