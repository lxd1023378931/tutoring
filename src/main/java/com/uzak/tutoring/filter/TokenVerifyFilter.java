package com.uzak.tutoring.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.uzak.tutoring.properties.CorsProperties;

/**
 * 权限认证拦截器
 * 
 * @author 梁秀斗
 * @mail liangxiudou@zving.com
 * @date 2018年3月29日
 */
@Component
@EnableConfigurationProperties(CorsProperties.class)
@WebFilter(urlPatterns = { "/api/v/*" }, filterName = "tokenVerifyFilter")
public class TokenVerifyFilter implements Filter {

	@Autowired
	private CorsProperties corsProperties;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// 设置跨域
		allowCors(response);
		boolean flag = false;
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json; charset=utf-8");
		// String token = request.getHeader(corsProperties.getToken());

		String method = request.getMethod();
		if ("OPTIONS".equals(method)) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			// token未过期
		}

		if (flag) {
			chain.doFilter(req, resp);
		} else {
			// 重定向到登录页
			request.getRequestDispatcher("/api/login").forward(req, resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	public void allowCors(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", corsProperties.getAccessControlAllowOrigin());
		response.setHeader("Access-Control-Allow-Methods", corsProperties.getAccessControlAllowMethods());
		response.setHeader("Access-Control-Max-Age", corsProperties.getAccessControlMaxAge());
		response.setHeader("Access-Control-Allow-Headers", corsProperties.getAccessControlAllowHeaders());
	}
}
