package com.uzak.tutoring.common.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Dao<T> implements Serializable, Cloneable, IDao<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Map<String, Object> toMap() {
		try {
			@SuppressWarnings("rawtypes")
			Class<? extends Dao> clazz = this.getClass();
			Map<String, Object> map = new HashMap<>();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(this));
			}
			return map;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		boolean flag = true;
		try {
			@SuppressWarnings("rawtypes")
			Class clazz = this.getClass();
			if (clazz.getName().equals(obj.getClass().getName())) {
				Field[] fieldsA = clazz.getDeclaredFields();
				Field[] fieldsB = obj.getClass().getDeclaredFields();
				for (int i = 0; i < fieldsA.length; i++) {
					fieldsA[i].setAccessible(true);
					fieldsB[i].setAccessible(true);
					if (!fieldsA[i].get(this).equals(fieldsB[i].get(obj))) {
						flag = false;
					}
				}
			}
		} catch (IllegalArgumentException e) {
			flag = false;
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public String toString() {
		Map<String, Object> map = this.toMap();
		if (map == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		map.forEach((k, v) -> {
			sb.append(k).append(":").append(v).append(",");
		});
		sb.deleteCharAt(sb.length() - 1);
		sb.append("}");
		return sb.toString();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
