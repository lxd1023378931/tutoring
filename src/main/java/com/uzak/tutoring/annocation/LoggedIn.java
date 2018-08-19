package com.uzak.tutoring.annocation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 登录判断注解
 * @author 梁秀斗
 * @mail 1023378931@qq.com
 * @date 2018年5月29日
 */
@Retention(RetentionPolicy.RUNTIME)
// 注解可用在方法，类，接口等
@Target({ ElementType.METHOD })
@Documented // 注解将被包含在JavaDoc中
@Inherited // 子类可以继承父类的注解
public @interface LoggedIn {
	String value() default "";
}
