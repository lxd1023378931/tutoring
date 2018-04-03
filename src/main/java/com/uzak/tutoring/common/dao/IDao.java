package com.uzak.tutoring.common.dao;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 实体类通用方法重写
 * 
 * @author 梁秀斗
 * @mail liangxiudou@zving.com
 * @date 2018年3月29日
 */
public interface IDao {
	/**
	 * 实体类转Map
	 * 
	 * @return
	 */
	Map<String, Object> toMap();

	/**
	 * 实体类转JSONObject
	 * 
	 * @return
	 */
	JSONObject toJSONObject();
}
