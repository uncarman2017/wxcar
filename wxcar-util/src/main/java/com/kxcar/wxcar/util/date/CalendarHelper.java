package com.kxcar.wxcar.util.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jiaweiyu on 2016/11/1.
 */
public class CalendarHelper {
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";


    //add by Jiawei Yu 2016-10-17
    public static Calendar addDays(Date date, int days) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal;
    }

    /*
    * 增加小时数
    * */
    public static Calendar addHours(Date date, int hours) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal;
    }

    /*
    * 增加分钟数
    * */
    public static Calendar addMinutes(Date date, int minutes) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal;
    }

    /*
    * 增加秒数
    * */
    public static Calendar addSeconds(Date date, int seconds) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);
        return cal;

    }

    public static String formatDate(Date date, String... pattern) {
        SimpleDateFormat df = pattern.length == 0 ? new SimpleDateFormat(DEFAULT_DATE_PATTERN) : new SimpleDateFormat(pattern[0]);
        return df.format(date);
    }

    public static Calendar timestampToCalendar(Timestamp timeStamp) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        String str = sdf.format(timeStamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(str));

        return calendar;
    }

    public static Timestamp calendarToTimestamp(Calendar calendar) throws ParseException {
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static String addDaysStr(Date date, int days) throws Exception {
        Calendar cal = addDays(date, days);
        return formatDate(cal.getTime());
    }

    public static String addHoursStr(Date date, int hours) throws Exception {
        Calendar cal = addHours(date, hours);
        return formatDate(cal.getTime());
    }

    public static String addMinutesStr(Date date, int minutes) throws Exception {
        Calendar cal = addMinutes(date, minutes);
        return formatDate(cal.getTime());
    }

    public static String addSecondsStr(Date date, int seconds) throws Exception {
        Calendar cal = addSeconds(date, seconds);
        return formatDate(cal.getTime());
    }

    public static Calendar max() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        Date date = sdf.parse("9999-12-31 23:59:59");
        return dateToCalendar(date);
    }

    public static Calendar min() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        Date date = sdf.parse("1900-01-01 00:00:00");
        return dateToCalendar(date);
    }

    public static Calendar now() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return cal;
    }

    public static int dayDiff(Calendar startDate,Calendar endDate){
        startDate.set(Calendar.HOUR_OF_DAY,0);
        startDate.set(Calendar.MINUTE,0);
        startDate.set(Calendar.SECOND,0);
        endDate.set(Calendar.HOUR_OF_DAY,0);
        endDate.set(Calendar.MINUTE,0);
        endDate.set(Calendar.SECOND,0);

        int days =  ((int)(endDate.getTime().getTime()/1000)-(int)(startDate.getTime().getTime()/1000))/3600/24;

        return days;
    }



}


