package com.uzak.tutoring.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 认证有效时间
 * 
 * @author 梁秀斗
 * @mail liangxiudou@zving.com
 * @date 2018年3月30日
 */
@Configuration
@ConfigurationProperties(prefix = "config", ignoreUnknownFields = false)
@PropertySource("classpath:token.properties") // 配置文件路径
@Component
public class TokenProperties {
	private long token;
	private long apptoken;

	public long getToken() {
		return token;
	}

	public void setToken(long token) {
		this.token = token;
	}

	public long getApptoken() {
		return apptoken;
	}

	public void setApptoken(long apptoken) {
		this.apptoken = apptoken;
	}

}
