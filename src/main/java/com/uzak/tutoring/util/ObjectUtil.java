package com.uzak.tutoring.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Object对象操作工具类
 * 
 * @author 梁秀斗
 * @mail liangxiudou@zving.com
 * @date 2018年3月29日
 */
public class ObjectUtil {
	private static final String DIGIT_PATTERN = "^\\d+(.\\d+)*$";

	/**
	 * 判断是否是可转为数字
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isDigit(Object o) {
		if (o == null || o.toString().length() == 0) {
			return false;
		}
		String s = o.toString();
		return Pattern.matches(DIGIT_PATTERN, s);
	}

	public static void main(String[] args) {
		System.out.println(isDigit("0.1"));
	}

	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}

	/**
	 * 判断对象是否为空，字符串去掉两端空格后为""，数字为0，Collection长度为0
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if (o instanceof String) {
			return o.toString().trim().length() == 0 ? true : false;
		}
		if (o instanceof Number) {
			return ((Number) o).doubleValue() == 0.0D ? true : false;
		}
		if (o instanceof Collection) {
			return ((Collection<?>) o).isEmpty();
		}
		if (o instanceof Map<?, ?>) {
			return ((Map<?, ?>) o).isEmpty();
		}
		return false;
	}

	public static Map<String, Object> toMap(Object obj) {
		Map<String, Object> map = new HashMap<>();
		@SuppressWarnings("rawtypes")
		Class clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				// 过滤掉由final修饰的属性
				if (!Modifier.isFinal(field.getModifiers())) {
					map.put(field.getName(), field.get(obj));
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return map;
	}
}
