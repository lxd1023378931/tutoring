package com.uzak.tutoring.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.uzak.tutoring.properties.ConfigProperties;

/**
 * 跨域拦截器
 * 
 * @author 梁秀斗
 * @mail 1023378931@qq.com
 * @date 2018年3月31日
 */
@Component
@EnableConfigurationProperties(ConfigProperties.class)
@WebFilter(urlPatterns = { "/api/*" }, filterName = "CorsFilter")
public class CorsFilter implements Filter {

	@Autowired
	private ConfigProperties configProperties;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) resp;
		// 设置跨域
		allowCors(response);
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("拦截请求，设置跨域!");
	}

	public void allowCors(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", configProperties.getAccessControlAllowOrigin());
		response.setHeader("Access-Control-Allow-Methods", configProperties.getAccessControlAllowMethods());
		response.setHeader("Access-Control-Max-Age", configProperties.getAccessControlMaxAge());
		response.setHeader("Access-Control-Allow-Headers", configProperties.getAccessControlAllowHeaders());
	}
}
