package com.uzak.tutoring.bl.impl.manager;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uzak.tutoring.bl.manager.MLoginBL;
import com.uzak.tutoring.common.util.IDaoUtil;
import com.uzak.tutoring.entity.UZManager;
import com.uzak.tutoring.properties.TokenProperties;
import com.uzak.tutoring.util.AjaxInfo;
import com.uzak.tutoring.util.StatusCode;
import com.uzak.tutoring.util.StringUtil;

@Service
public class MLoginBLImpl implements MLoginBL {
	@Autowired
	private IDaoUtil dao;
	
	@Autowired
	private TokenProperties tokenProperties;

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
			manager.setPassword("***");
			return new AjaxInfo(StatusCode.SUCCESS, "登录成功", manager);
		} else {
			return new AjaxInfo(StatusCode.FAIL, "用户名或密码错误");
		}

	}
}
