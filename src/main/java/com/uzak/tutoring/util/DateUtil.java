package com.uzak.tutoring.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static final String CHINESE_SIMPLE_FORMAT = "yyyy年MM月dd日";
	public static final String CHINESE_FORMAT = "yyyy年MM月dd日 HH时mm分ss秒";
	public static final String SIMPLE_FORMAT = "yyyy-MM-dd";
	public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String STANDARD_SIMPLE_FORMAT = "yyyy/MM/dd";
	public static final String STANDARD_FORMAT = "yyyy/MM/dd HH:mm:ss";
	private static SimpleDateFormat sdf = null;

	public static String format(Date date) {
		return format(date, FORMAT);
	}

	public static String format(Date date, String format) {
		if (StringUtil.isEmpty(format)) {
			System.out.println("时间格式为空");
			format = FORMAT;
		}
		sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
