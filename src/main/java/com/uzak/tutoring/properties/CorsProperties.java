package com.uzak.tutoring.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author 梁秀斗
 * @mail liangxiudou@zving.com
 * @date 2018年3月30日
 */
@Configuration // 表明这是一个配置类
// 绑定属性。prefix用来选择属性的前缀，ignoreUnknownFields是用来告诉SpringBoot在有属性不能匹配到声明的域时抛出异常。
@ConfigurationProperties(prefix = "cors", ignoreUnknownFields = false)
@PropertySource("classpath:config.properties") // 配置文件路径
@Component
public class CorsProperties {
	private String AccessControlAllowOrigin;
	private String AccessControlAllowMethods;
	private String AccessControlMaxAge;
	private String AccessControlAllowHeaders;
	private String Token;

	public String getAccessControlAllowOrigin() {
		return AccessControlAllowOrigin;
	}

	public void setAccessControlAllowOrigin(String accessControlAllowOrigin) {
		AccessControlAllowOrigin = accessControlAllowOrigin;
	}

	public String getAccessControlAllowMethods() {
		return AccessControlAllowMethods;
	}

	public void setAccessControlAllowMethods(String accessControlAllowMethods) {
		AccessControlAllowMethods = accessControlAllowMethods;
	}

	public String getAccessControlMaxAge() {
		return AccessControlMaxAge;
	}

	public void setAccessControlMaxAge(String accessControlMaxAge) {
		AccessControlMaxAge = accessControlMaxAge;
	}

	public String getAccessControlAllowHeaders() {
		return AccessControlAllowHeaders;
	}

	public void setAccessControlAllowHeaders(String accessControlAllowHeaders) {
		AccessControlAllowHeaders = accessControlAllowHeaders;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

}
