package com.uzak.tutoring.component.manage.api;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uzak.tutoring.annocation.LoggedIn;
import com.uzak.tutoring.bl.LoginBL;
import com.uzak.tutoring.code.StatusCode;
import com.uzak.tutoring.code.UserType;
import com.uzak.tutoring.component.manage.bl.MLoginBL;
import com.uzak.tutoring.entity.UZManager;
import com.uzak.tutoring.pojo.AjaxInfo;
import com.uzak.tutoring.util.StringUtil;

@RestController
@RequestMapping("api/m/")
public class LoginUI {
	@Autowired
	private LoginBL loginBL;

	@Autowired
	private MLoginBL mLoginBL;

	/**
	 * 登录接口
	 * 
	 * @param name
	 * @param password
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping(value="/login")
	public AjaxInfo login(@RequestBody UZManager manager) {
		AjaxInfo info = new AjaxInfo();
		if (StringUtil.isEmpty(manager.getName()) || StringUtil.isEmpty(manager.getPassword())) {
			info.put(StatusCode.PARAMS_ERROR, "用户名或密码为空");
			return info;
		}
		info = mLoginBL.login(manager.getName(), manager.getPassword());
		return info;
	}

	/**
	 * 退出接口
	 * 
	 * @param manager
	 * @return
	 */
	@GetMapping(value="/logout/{id}")
	@LoggedIn(UserType.MANAGER)
	public AjaxInfo logout(@PathVariable(value = "id") Long id) {
		AjaxInfo info = new AjaxInfo();
		if (id == null || id == 0) {
			info.put(StatusCode.PARAMS_ERROR, "用户ID为空");
			return info;
		}
		info = mLoginBL.logout(id);
		return info;
	}

	/**
	 * 判断是否在登录状态
	 * 
	 * @param token
	 * @return
	 */
	@GetMapping(value = "/logged")
	public AjaxInfo isLogin(@RequestParam String token, @RequestParam String type) {
		AjaxInfo info = new AjaxInfo();
		if (StringUtil.isEmpty(token) || StringUtil.isEmpty(type)) {
			info.put(StatusCode.NO_LOGGEDIN, "未登录");
			return info;
		}
		if (loginBL.isLogin(token, type)) {
			info.put(StatusCode.SUCCESS, "登录成功");
		} else {
			info.put(StatusCode.NO_LOGGEDIN, "未登录");
		}
		return info;
	}
}
