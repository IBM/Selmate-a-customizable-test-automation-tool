
package com.ibm.selmate.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

	public static String format(Date date) {
		return sdf.format(date);
	}

	public static String format() {
		Date date = new Date(System.currentTimeMillis());
		return sdf.format(date);
	}

	public static Date parse(String dateStr) throws ParseException {
		return sdf.parse(dateStr);
	}
}
