package com.uzak.tutoring.annocation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
// 注解可用在方法，类，接口等
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented // 注解将被包含在JavaDoc中
@Inherited // 子类可以继承父类的注解
public @interface LoggedIn {
	String value() default "";
}
