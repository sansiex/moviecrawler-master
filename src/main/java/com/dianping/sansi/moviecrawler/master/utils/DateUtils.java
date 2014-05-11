package com.dianping.sansi.moviecrawler.master.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static final String TIMESTAMP_FORMAT="yyyy/MM/dd HH:mm:ss";
	public static final String DATE_FORMAT="yyyy/MM/dd";
	
	public static Date string2Date(String str,String format){
		try {
			DateFormat df = new SimpleDateFormat(format);
			return df.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public static Date string2Date(String str){
		return string2Date(str,DATE_FORMAT);
	}
	
	public static String date2String(Date date){
		//timeDemoField > to_date( 'time'，'yyyy-mm-dd'）
		return date2String(date,DATE_FORMAT);
	}
	
	public static String date2String(Date date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String strDate  = sdf.format(date);

		return strDate;
	}
}
