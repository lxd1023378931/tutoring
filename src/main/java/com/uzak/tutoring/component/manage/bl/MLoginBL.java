package com.uzak.tutoring.component.manage.bl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.uzak.tutoring.pojo.AjaxInfo;

/**
 * 管理员操作接口
 * 
 * @author 梁秀斗
 * @mail 1023378931@qq.com
 * @date 2018年4月1日
 */
public interface MLoginBL {
	/**
	 * 登录功能
	 * 
	 * @param name
	 * @param password
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public AjaxInfo login(String name, String password);

	/**
	 * 退出功能
	 * 
	 * @param id
	 * @return
	 */
	public AjaxInfo logout(long id);
}
