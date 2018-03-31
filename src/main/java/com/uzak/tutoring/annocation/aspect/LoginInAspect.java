package com.uzak.tutoring.annocation.aspect;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.uzak.tutoring.annocation.LoggedIn;
import com.uzak.tutoring.common.util.IDaoUtil;
import com.uzak.tutoring.entity.common.UZToken;
import com.uzak.tutoring.exception.LoggedInException;
import com.uzak.tutoring.properties.ConfigProperties;
import com.uzak.tutoring.properties.TokenProperties;
import com.uzak.tutoring.util.AjaxInfo;
import com.uzak.tutoring.util.ObjectUtil;
import com.uzak.tutoring.util.StringUtil;

/**
 * 按类型判断检查用户是否登录
 * 
 * @author 梁秀斗
 * @mail 1023378931@qq.com
 * @date 2018年3月31日
 */
@Aspect
@Component
public class LoginInAspect {
	@Autowired
	private ConfigProperties configProperties;
	@Autowired
	private TokenProperties tokenProperties;
	@Autowired
	private IDaoUtil<UZToken> dao;

	@Before(value = "within(@org.springframework.stereotype.Controller *) && @annotation(loggedIn)")
	public void loginLimit(JoinPoint jp, LoggedIn loggedIn) throws LoggedInException {
		boolean isLogin = false;
		// 获取HttpServletRequest
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		String tokenType = loggedIn.value();
		String token = request.getHeader(tokenType);
		String defaultTokens = configProperties.getToken();
		List<String> tokenTypesList = Arrays.asList(defaultTokens.split(","));
		if (StringUtil.isEmpty(defaultTokens)) {
			throw new LoggedInException("请先在config.properties配置Token参数，作为登录验证参数");
		}
		if (StringUtil.isNotEmpty(tokenType) && !tokenTypesList.contains(tokenType)) {
			throw new LoggedInException("未配置" + tokenType + "类型权限");
		}
		if (StringUtil.isEmpty(token)) {
			isLogin = false;
		}

		if (StringUtil.isEmpty(tokenType)) {
			// 请求满足配置中任一登录权限即可
			for (String type : tokenTypesList) {
				isLogin = isLogin(token, type);
				if (isLogin) {
					break;
				}
			}
		} else {
			// 必须满足传入的指定登录权限
			isLogin = isLogin(token, tokenType);
		}

		if (!isLogin) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			ServletOutputStream sos = null;
			try {
				sos = response.getOutputStream();
				AjaxInfo info = new AjaxInfo(200, "您尚未登录，请先登录！");
				sos.write(info.toString().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (sos != null) {
					try {
						sos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 判断是否登录
	 * 
	 * @param token
	 * @param type
	 * @return
	 */
	private boolean isLogin(String token, String type) {
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
			}
			return true;
		} else {
			return false;
		}
	}
}
