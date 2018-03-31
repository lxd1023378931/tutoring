package com.uzak.tutoring.util;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.uzak.tutoring.properties.ConfigProperties;

/**
 * 网络配置工具
 * 
 * @author 梁秀斗
 * @mail liangxiudou@zving.com
 * @date 2018年3月29日
 */
@EnableConfigurationProperties(ConfigProperties.class)
@Component
public class NetUtil {
	
}
