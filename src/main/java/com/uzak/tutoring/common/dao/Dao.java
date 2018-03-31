package com.uzak.tutoring.common.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "handler", "hibernateLazyInitializer" })
public class Dao<T> implements Serializable, Cloneable, IDao<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<>();
		try {
			@SuppressWarnings("rawtypes")
			Class<? extends Dao> clazz = this.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				// 过滤掉由final修饰的属性
				if (!Modifier.isFinal(field.getModifiers())) {
					map.put(field.getName(), field.get(this));
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 比较所有对象属性值，不包括final修饰的属性
	 */
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
				if (fieldsA.length == fieldsB.length) {
					for (int i = 0; i < fieldsA.length; i++) {
						fieldsA[i].setAccessible(true);
						fieldsB[i].setAccessible(true);
						// 过滤掉由final修饰的属性
						if (!Modifier.isFinal(fieldsA[i].getModifiers()) && !Modifier.isFinal(fieldsB[i].getModifiers())) {
							flag = fieldsA[i].get(this) == null ? (fieldsB[i].get(obj) == null ? true : false)
									: (fieldsA[i].get(this).equals(fieldsB[i].get(obj)) ? true : false);
						}
					}
				} else {
					flag = false;
				}
			} else {
				flag = false;
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

	/**
	 * 不能保证equals为false时hashCode一定不相等
	 * 然而，程序员必须意识到，hashCode返回独一无二的散列码，会让存储这个对象的hashtables更好地工作。
	 */
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public JSONObject toJSONObject() {
		return new JSONObject(this.toMap());
	}

	@Override
	public String toString() {
		return this.toMap().toString();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
