package com.uzak.tutoring.util;

import com.alibaba.fastjson.JSONObject;

/**
 * JSON操作类
 * 
 * @author 梁秀斗
 * @mail liangxiudou@zving.com
 * @date 2018年4月4日
 */
public class JSONUtil {
	public static JSONObject merge(JSONObject jo1, JSONObject jo2) {
		jo2.forEach((k, v) -> {
			jo1.put(k, v);
		});
		return jo1;
	}
}
