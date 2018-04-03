package com.uzak.tutoring.bl.impl.manager;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.uzak.tutoring.bl.manager.MLoginBL;
import com.uzak.tutoring.common.util.IDaoUtil;
import com.uzak.tutoring.entity.UZManager;
import com.uzak.tutoring.entity.common.UZToken;
import com.uzak.tutoring.properties.TokenProperties;
import com.uzak.tutoring.util.AjaxInfo;
import com.uzak.tutoring.util.ObjectUtil;
import com.uzak.tutoring.util.StatusCode;
import com.uzak.tutoring.util.StringUtil;
import com.uzak.tutoring.util.UserType;

@Service
public class MLoginBLImpl implements MLoginBL {
	@SuppressWarnings("rawtypes")
	@Autowired
	private IDaoUtil dao;

	@Autowired
	private TokenProperties tokenProperties;

	@SuppressWarnings("unchecked")
	@Override
	public AjaxInfo login(String name, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String md5Password = StringUtil.md5Hex(password);
		List<UZManager> mList = dao.query("from UZManager where Name=?", name);
		UZManager manager = null;
		for (UZManager m : mList) {
			if (m.getPassword().equals(md5Password)) {
				manager = m;
				break;
			}
		}
		if (manager != null) {
			// 返回密码不显示
			String token = StringUtil.md5Hex(manager.getName() + manager.getPassword() + UserType.MANAGER);
			System.out.println(token);
			Object obj = ObjectUtil.toMap(tokenProperties).get(UserType.MANAGER);
			long time = (ObjectUtil.isDigit(obj) && obj.toString().indexOf(".") == -1) ? Long.parseLong(obj.toString()) : 3600000;
			UZToken uzToken = new UZToken();
			uzToken.setType(UserType.MANAGER);
			uzToken.setToken(token);
			if (dao.fill(uzToken)) {
				uzToken.setExpireTime(uzToken.getExpireTime() + time);
				dao.update(uzToken);
			} else {
				uzToken.setExpireTime(System.currentTimeMillis() + time);
				dao.save(uzToken);
			}
			JSONObject result = new JSONObject();
			result.put("token", token);
			result.put("manager", manager);
			return new AjaxInfo(StatusCode.SUCCESS, "登录成功", result);
		} else {
			return new AjaxInfo(StatusCode.FAIL, "用户名或密码错误");
		}

	}
}
