package com.web.app.tools;

import java.text.SimpleDateFormat;

public class DateTools {
	public static String getCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new java.util.Date());
    }
	
	public static String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new java.util.Date());
    }
	
	public static String getTimes() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        return format.format(new java.util.Date());
    }
}
