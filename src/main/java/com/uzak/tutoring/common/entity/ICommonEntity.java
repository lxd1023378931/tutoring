package com.uzak.tutoring.common.entity;

import java.util.Map;
/**
 * 实体类通用方法重写
 * @author 梁秀斗
 * @mail liangxiudou@zving.com
 * @date 2018年3月29日
 */
public interface ICommonEntity<T> {
	/**
	 * 实体类转Map
	 * @return
	 */
	public Map<String, Object> toMap();
}
