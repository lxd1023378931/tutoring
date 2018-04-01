package com.uzak.tutoring.exception.handler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uzak.tutoring.common.util.IDaoUtil;
import com.uzak.tutoring.entity.common.UZException;
import com.uzak.tutoring.util.AjaxInfo;
import com.uzak.tutoring.util.StatusCode;

/**
 * 捕获程序中遗漏的未处理异常
 * 
 * @author 梁秀斗
 * @mail 1023378931@qq.com
 * @date 2018年3月31日
 */
@ControllerAdvice
public class ExceptionHandle {
	@Autowired
	private IDaoUtil<UZException> dao;

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public AjaxInfo exception(Exception e) {
		UZException exception = new UZException();
		exception.setType(e.getClass().getSimpleName());
		exception.setDetail(e.getMessage());
		exception.setAddTime(new Date());
		dao.save(exception);
		return new AjaxInfo(StatusCode.UNCATCH_EXCEPTION, e.getMessage());
	}
}
