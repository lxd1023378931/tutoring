package com.uzak.tutoring.bl.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uzak.tutoring.bl.LoginBL;
import com.uzak.tutoring.code.UserType;
import com.uzak.tutoring.common.util.IDaoUtil;
import com.uzak.tutoring.entity.common.UZToken;
import com.uzak.tutoring.properties.TokenProperties;
import com.uzak.tutoring.util.ObjectUtil;
import com.uzak.tutoring.util.StringUtil;

@Service
public class LoginBLImpl implements LoginBL {

	@Autowired
	private TokenProperties tokenProperties;
	@Autowired
	private IDaoUtil<UZToken> dao;

	@Override
	/**
	 * 判断是否登录
	 * 
	 * @param token
	 * @param type
	 * @return
	 */
	public boolean isLogin(String token, String type) {
		UZToken uzToken = new UZToken();
		uzToken.setType(type);
		uzToken.setToken(token);
		if (dao.fill(uzToken)) {
			long currentTime = System.currentTimeMillis();
			if (currentTime < uzToken.getExpireTime()) {
				Object obj = ObjectUtil.toMap(tokenProperties).get(type);
				long time = (ObjectUtil.isDigit(obj) && obj.toString().indexOf(".") == -1) ? Long.parseLong(obj.toString()) : 0;
				uzToken.setExpireTime(uzToken.getExpireTime() + time);
				dao.update(uzToken);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public UZToken login(String userName, String password, String type) {
		UZToken uzToken = null;
		try {
			uzToken = new UZToken();
			String token = StringUtil.md5Hex(userName + password + type);
			System.out.println(token);
			Object obj = ObjectUtil.toMap(tokenProperties).get(type);
			long time = (ObjectUtil.isDigit(obj) && obj.toString().indexOf(".") == -1) ? Long.parseLong(obj.toString()) : 3600000;
			uzToken.setType(UserType.MANAGER);
			uzToken.setToken(token);
			if (dao.fill(uzToken)) {
				dao.delete(uzToken);
			}
			uzToken.setExpireTime(System.currentTimeMillis() + time);
			dao.save(uzToken);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return uzToken;
	}

	@Override
	public boolean logout(String userName, String password, String type) {
		UZToken uzToken = null;
		try {
			uzToken = new UZToken();
			String token = StringUtil.md5Hex(userName + password + type);
			uzToken.setType(UserType.MANAGER);
			uzToken.setToken(token);
			if (dao.fill(uzToken)) {
				dao.delete(uzToken);
			}
			return true;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}
}
