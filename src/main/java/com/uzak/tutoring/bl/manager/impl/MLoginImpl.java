package com.uzak.tutoring.bl.manager.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.uzak.tutoring.bl.LoginBL;
import com.uzak.tutoring.bl.manager.MLoginBL;
import com.uzak.tutoring.common.util.IDaoUtil;
import com.uzak.tutoring.entity.UZManager;
import com.uzak.tutoring.entity.common.UZToken;
import com.uzak.tutoring.util.AjaxInfo;
import com.uzak.tutoring.util.StatusCode;
import com.uzak.tutoring.util.StringUtil;
import com.uzak.tutoring.util.UserType;

@Service
public class MLoginImpl implements MLoginBL {
	@SuppressWarnings("rawtypes")
	@Autowired
	private IDaoUtil dao;
	@Autowired
	private LoginBL loginBL;

	@SuppressWarnings("unchecked")
	@Override
	public AjaxInfo login(String name, String password) {
		String md5Password = "";
		List<UZManager> mList = dao.query("from UZManager where Name=?", name);
		UZManager manager = null;
		try {
			md5Password = StringUtil.md5Hex(password);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for (UZManager m : mList) {
			if (m.getPassword().equals(md5Password)) {
				manager = m;
				break;
			}
		}
		if (manager != null) {
			UZToken uzToken = loginBL.login(manager.getName(), manager.getPassword(), UserType.MANAGER);
			if (uzToken != null) {
				manager.setLastLoginTime(new Date());
				dao.update(manager);
				JSONObject result = new JSONObject();
				result.put("token", uzToken.getToken());
				result.put("manager", manager);
				return new AjaxInfo(StatusCode.SUCCESS, "登录成功", result);
			} else {
				return new AjaxInfo(StatusCode.UNCATCH_EXCEPTION, "系统异常", null);
			}
		} else {
			return new AjaxInfo(StatusCode.FAIL, "用户名或密码错误");
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public AjaxInfo logout(long id) {
		UZManager manager = new UZManager();
		manager.setId(id);
		if (dao.fill(manager)) {
			if (loginBL.logout(manager.getName(), manager.getPassword(), UserType.MANAGER)) {
				return new AjaxInfo(StatusCode.SUCCESS, "退出成功");
			} else {
				return new AjaxInfo(StatusCode.FAIL, "退出失败");
			}
		} else {
			return new AjaxInfo(StatusCode.FAIL, "用户不存在");
		}
	}
}
