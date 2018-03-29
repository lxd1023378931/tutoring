package com.uzak.tutoring.util;

import java.util.Collection;
import java.util.Map;

/**
 * Object对象操作工具类
 * 
 * @author 梁秀斗
 * @mail liangxiudou@zving.com
 * @date 2018年3月29日
 */
public class ObjectUtil {
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
}
