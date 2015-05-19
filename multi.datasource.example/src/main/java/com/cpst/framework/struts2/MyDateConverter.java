package com.cpst.framework.struts2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

public class MyDateConverter extends DefaultTypeConverter {

	private static final DateFormat[] ACCEPT_DATE_FORMATS = { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a"),
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), new SimpleDateFormat("yyyy-MM-dd HH:mm a"), new SimpleDateFormat("yyyy-MM-dd HH:mm"),
			new SimpleDateFormat("dd/MM/yyyy"), new SimpleDateFormat("yyyy-MM-dd"), new SimpleDateFormat("yyyy/MM/dd"), new SimpleDateFormat("yyyyMMdd"),new SimpleDateFormat("yyMMdd") }; // 支持转换的日期格式

	public Object convertValue(Map context, Object value, Class toType) {
		if (toType == Date.class) { // 浏览器向服务器提交时，进行String to Date的转换

			String dateString = null;
			String[] params = (String[]) value;
			dateString = params[0]; // 获取日期的字符串
			for (DateFormat format : ACCEPT_DATE_FORMATS) {
				try {
					return format.parse(dateString); // 遍历日期支持格式，进行转换
				} catch (Exception e) {
					continue;
				}
			}
			return null;
		} else if (toType == String.class) { // 服务器向浏览器输出时，进行Date to String的类型转换
			Date date = (Date) value;
			return new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(date);// 输出的格式是yyyy-MM-dd
		}
		return null;
	}
}