package com.uzak.tutoring.bl;

import com.uzak.tutoring.entity.common.UZToken;

public interface LoginBL {
	/**
	 * 判断是否在登录状态
	 * 
	 * @param token
	 * @return
	 */
	public boolean isLogin(String token, String type);

	/**
	 * 登录，创建token
	 * 
	 * @param userName
	 * @param password
	 * @param type
	 * @return
	 */
	public UZToken login(String userName, String password, String type);

	/**
	 * 退出登录，删除token
	 * 
	 * @param userName
	 * @param password
	 * @param type
	 * @return
	 */
	public boolean logout(String userName, String password, String type);
}
