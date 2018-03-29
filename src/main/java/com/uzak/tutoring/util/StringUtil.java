package com.uzak.tutoring.util;

import java.util.regex.Pattern;

/**
 * String 操作工具类
 * 
 * @author 梁秀斗
 * @mail liangxiudou@zving.com
 * @date 2018年3月29日
 */
public class StringUtil {
	public static final Pattern PDigit = Pattern.compile("^\\d+(\\.\\d+)*$");
	/**
	 * 判断对象是否为字符串类型
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isString(Object obj) {
		return obj instanceof String;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}
		return str.trim().length() == 0 ? true : false;
	}

	/**
	 * 判断字符串是否不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 判断是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDigit(String str) {
		return isEmpty(str) ? false : PDigit.matcher(str).find();
	}
}
