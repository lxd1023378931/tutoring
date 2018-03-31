package com.uzak.tutoring.exception.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.uzak.tutoring.common.util.IDaoUtil;
import com.uzak.tutoring.entity.common.UZException;

/**
 * 异常收集器，未生效
 * 
 * @author 梁秀斗
 * @mail 1023378931@qq.com
 * @date 2018年3月31日
 */
@ControllerAdvice
public class RunTimeExceptionHandler {
	@Autowired
	private IDaoUtil<UZException> dao;

	@ExceptionHandler
	public void exception(Exception e) {
		System.out.println("处理异常");
		UZException exception = new UZException();
		exception.setType(e.getClass().getSimpleName());
		exception.setDetail(e.getMessage());
		dao.save(exception);
		e.printStackTrace();
	}
}
