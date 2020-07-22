package com.fb.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

	public static String formatDate(Date date){
		String str = "NA";
		if(date!=null){
			SimpleDateFormat  sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			str = sdf.format(date);
		}
		
		return str;
	}
	
	public static Date starteOfDay(Date date) {
		date.setHours(00);
		date.setMinutes(00);
		date.setSeconds(00);
		return date;
	}
	
	public static Date endOfDate(Date date) {
		date.setHours(23);
		date.setMinutes(59);
		date.setSeconds(59);
		return date;
	}
}
