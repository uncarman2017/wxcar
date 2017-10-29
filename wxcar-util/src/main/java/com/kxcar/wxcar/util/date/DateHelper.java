package com.kxcar.wxcar.util.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

	public static final String CHINESE_DATE_PATTERN = "yyyy年M月d日H时m分";
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static long compare(String date1, String date2) throws Exception {
//		date1 = fix(date1);
//		date2 = fix(date2);
		SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		Date dateA = df.parse(date1);
		Date dateB = df.parse(date2);
		return dateA.getTime() - dateB.getTime();
	}

	public static String fix(String dateStr) {
		dateStr = dateStr.replaceAll("/", "-");
		dateStr = dateStr.replaceAll("T", " ");
		String[] sections = dateStr.split(":");
		if (sections != null && sections.length < 3) {
			dateStr += ":00";
		}
		return dateStr;
	}

	public static String format(Date date) {
		return format(date, DEFAULT_DATE_PATTERN);
	}

	public static String format(Date date, int hourPlus) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hourPlus); //12小时制?
		return format(cal.getTime());
	}

	public static String format(Date date, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}

	public static String format(String dateStr, int hourPlus) throws Exception {
		Date date = parse(dateStr);
		return format(date, hourPlus);
	}

	public static String format(String dateStr, String pattern) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date date = df.parse(dateStr);
		return df.format(date);
	}

	public static Date parse(String dateStr) throws Exception {
		//dateStr = fix(dateStr);
		Date date = parse(dateStr, DEFAULT_DATE_PATTERN);
		return date;
	}

	public static Date parse(String dateStr, String pattern) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date date = df.parse(dateStr);
		return date;
	}

	//add by Jiawei Yu 2016-10-17
	public static String addDays(Date date, int days) throws Exception
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return format(cal.getTime());
	}

	public static Date addDaysOfDate(Date date,int days) throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}

	public static Timestamp addDaysTimestamp(Date date, int days) throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH,days);
		return new Timestamp(cal.getTimeInMillis());
	}

	/*
	* 增加小时数
	* */
	public static String addHours(Date date,int hours) throws Exception
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, hours);
		return format(cal.getTime());
	}

	public static Date addHoursOfDate(Date date,int hours) throws Exception{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, hours);
		return cal.getTime();

	}

	public static Timestamp addHoursTimestamp(Date date,int hours) throws Exception
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, hours);
		return new Timestamp(cal.getTimeInMillis());
	}


	/*
	* 增加分钟数
	* */
	public static String addMinutes(Date date,int minutes) throws Exception
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutes);
		return format(cal.getTime());
	}

	public static Timestamp addMinutesTimestamp(Date date,int minutes) throws Exception
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutes);
		return new Timestamp(cal.getTimeInMillis());
	}

	public static Date addMinutesOfDate(Date date,int minutes) throws Exception
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutes);
		return cal.getTime();
	}

	/*
	* 增加秒数
	* */
	public static String addSeconds(Date date,int seconds) throws Exception
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, seconds);
		return format(cal.getTime());

	}

	public static Timestamp addSecondsTimestamp(Date date,int seconds) throws Exception
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, seconds);
		return new Timestamp(cal.getTimeInMillis());

	}

	public static Date addSecondsOfDate(Date date,int seconds) throws Exception
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, seconds);
		return cal.getTime();

	}

	public static int dayDiff(Timestamp start,Timestamp end) throws Exception{
		Calendar startDate = CalendarHelper.timestampToCalendar(start);
		Calendar endDate = CalendarHelper.timestampToCalendar(end);

		startDate.set(Calendar.HOUR_OF_DAY,0);
		startDate.set(Calendar.MINUTE,0);
		startDate.set(Calendar.SECOND,0);
		endDate.set(Calendar.HOUR_OF_DAY,0);
		endDate.set(Calendar.MINUTE,0);
		endDate.set(Calendar.SECOND,0);

		int days =  ((int)(endDate.getTime().getTime()/1000)-(int)(startDate.getTime().getTime()/1000))/3600/24;

		return days;
	}


	public static Timestamp max() throws ParseException {
		return Timestamp.valueOf("9999-12-31 23:59:59");
	}

	public static Timestamp min() throws ParseException {
		return Timestamp.valueOf("1900-01-01 00:00:00");

	}

	public static Timestamp now() {
		Date date = new Date();
		return new Timestamp(date.getTime());
	}

	public static Date timestampToDate(Timestamp timestamp){
		return new Date(timestamp.getTime());
	}

	public static Timestamp dateToTimestamp(Date date){
		return new Timestamp(date.getTime());
	}
}
