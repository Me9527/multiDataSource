package com.cpst.framework.base.util;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateFormat {

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String format(Date date) {

		return format.format(date);
	}
}
