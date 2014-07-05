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
     * 获取昨天凌晨时间
     *
     * @return Date
     */
    public static Date getYesterday() {
        Date _date = getPosDate(new Date(), -1, Calendar.DAY_OF_MONTH);
        return DateUtils.truncate(_date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当天凌晨的时间
     *
     * @param date
     * @return Date
     */
    public static Date getDateBegin(Date date) {
        return DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取第二天凌晨的时间
     *
     * @param date
     * @return Date
     */
    public static Date getNextDay(Date date) {
        Date _date = getPosDate(date, 1, Calendar.DAY_OF_MONTH);
        return DateUtils.truncate(_date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定月第一天凌晨的时间
     *
     * @param date
     * @return Date
     */
    public static Date getMonthBegin(Date date) {
        return DateUtils.truncate(date, Calendar.MONTH);
    }

    /**
     * 根据字符串生成日期
     *
     * @param dateStr yyyy-MM-dd HH:mm:ss 格式的字符串
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
     * 根据日期获得“yyyy-MM-dd HH:mm:ss”字符串
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
     * 获取下个月的第一天
     *
     * @param date
     * @return Date
     */
    public static Date getNextMonthFirstDay(Date date) {
        Date _date = DateUtils.truncate(date, Calendar.MONTH);
        return getPosDate(_date, 1, Calendar.MONTH);
    }

    /**
     * 获取上个月第一天
     *
     * @param date
     * @return Date
     */
    public static Date getPreMonthFirstDay(Date date) {
        Date _date = DateUtils.truncate(date, Calendar.MONTH);
        return getPosDate(_date, -1, Calendar.MONTH);
    }

    /**
     * 获取 yyyy-MM-dd HH 根式的日期字符串
     *
     * @param date
     * @return String
     */
    public static String getDateHourStr(Date date) {
        return HOURDATEFORMAT.format(date);
    }

    /**
     * 根据 yyyy-MM-dd HH 根式字符串获取日期
     *
     * @param dateStr
     * @return Date
     */
    public static Date getDateByHourStr(String dateStr) {
        return getDateByString(dateStr, HOURDATEFORMAT);
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
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 截取指定日期到小时
     *
     * @param date
     * @return Date
     */
    public static Date getDateByHour(Date date) {
        return DateUtils.truncate(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取上一个小时
     *
     * @param date
     * @return Date
     */
    public static Date getPreHour(Date date) {
        Date _date = getPosDate(date, -1, Calendar.HOUR_OF_DAY);
        return DateUtils.truncate(_date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取下一个小时
     *
     * @param date
     * @return Date
     */
    public static Date getNextHour(Date date) {
        Date _date = getPosDate(date, 1, Calendar.HOUR_OF_DAY);
        return DateUtils.truncate(_date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取当前时间的多少分钟之前
     */
    public static Date getPreMinute(Date date, int minute, int second) {
        Date _date = getPosDate(date, 0 - minute, Calendar.MINUTE);
        _date = getPosDate(_date, 0 - second, Calendar.SECOND);
        return DateUtils.truncate(_date, Calendar.SECOND);
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
        Date _date = getPosDate(date, minute, Calendar.MINUTE);
        _date = getPosDate(_date, second, Calendar.SECOND);
        return DateUtils.truncate(_date, Calendar.SECOND);
    }

    /**
     * 根据指定格式的日期字符串生成日期
     *
     * @param dateStr    日期字符串
     * @param dateFormat 日期格式
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
     * 把移动OC4J日志日期字符转换成日期对象
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
     * 获取 yyyy,MM.dd 格式的日期字符串
     *
     * @param date
     * @return String
     */
    public static String getDotDateStr(Date date) {
        return DOTDATEFORMAT.format(date);
    }

    /**
     * 由 yyyy.MM.dd 格式的字符串获取日期
     *
     * @param dateStr
     * @return Date
     */
    public static Date getDotDateByStr(String dateStr) {
        return getDateByString(dateStr, DOTDATEFORMAT);
    }

    /**
     * 获取 yyyy-MM-dd 格式的日期字符串
     *
     * @param date
     * @return String
     */
    public static String getDateBasicStr(Date date) {
        return BASICDATEFORMAT.format(date);
    }

    /**
     * 由 yyyy-MM-dd 格式的字符串获取日期
     *
     * @param dateStr
     * @return Date
     */
    public static Date getDateByBasicStr(String dateStr) {
        return getDateByString(dateStr, BASICDATEFORMAT);
    }

    /**
     * 获取前几天
     *
     * @param date
     * @param preNum 正数取前几天，负数取后几天
     * @return Date
     */
    public static Date getPreDateByNum(Date date, int preNum) {
        return getPosDate(date, 0 - preNum, Calendar.DAY_OF_MONTH);
    }

    /**
     * 得到当天某个时刻
     *
     * @param hour 设定当天的小时数
     * @return date
     */
    public static Date getHourDateByDate(Date date, int hour) {
        Date retDate = setTime(date, hour, Calendar.HOUR_OF_DAY);
        return DateUtils.truncate(retDate, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取第二天的时间,天数加一,时间用hour,其余清零
     *
     * @param date 基础日期
     * @param hour 下一天时间
     * @return Date
     */
    public static Date getNextDayOnHour(Date date, int hour) {
        Date _date = getPosDate(date, 1, Calendar.DAY_OF_MONTH);
        _date = setTime(_date, hour, Calendar.HOUR_OF_DAY);
        return DateUtils.truncate(_date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取间隔时间,并提供截取功能
     *
     * @param date     修改时间
     * @param posCount 间隔数
     * @param posType  间隔单位
     * @return 修改后时间
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
     * 设置某个时间单位的时间
     *
     * @param date
     * @param value
     * @param type
     * @return 修改后时间
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
     * 获取日期的天
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
     * 获取日期的月份
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
     * 获取日期的年份
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
     * 根据 yyyy-MM-dd HH:mm:ss 格式获取日期字符串
     *
     * @param date
     * @return String
     */
    public static String getDateFullStr(Date date) {
        return FULLFORMAT.format(date);
    }


    /**
     * 计算两个时间之间的天数差值
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return long 天数
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

        System.out.println("dayCount：" + date);



    }
}
