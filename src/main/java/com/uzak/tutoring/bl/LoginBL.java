package com.uzak.tutoring.bl;

public interface LoginBL {
	/**
	 * 判断是否在登录状态
	 * 
	 * @param token
	 * @return
	 */
	public boolean isLogin(String token, String type);
}
