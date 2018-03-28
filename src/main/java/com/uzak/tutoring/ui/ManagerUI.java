package com.uzak.tutoring.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uzak.tutoring.common.dao.CommonDao;
import com.uzak.tutoring.entity.Manager;

/**
 * 管理员接口
 * 
 * @author Liang Xiudou
 * @date 2018/3/28
 */
@Controller
@RequestMapping("/manager")
public class ManagerUI {
	@Autowired
	CommonDao dao;
	
	@RequestMapping("getUserByID")
	@ResponseBody
	public Manager getUserByID(Long id) {
		Manager manager = new Manager();
		manager.setID(1L);
		System.out.println(manager.getName());
		return manager;
	}

}
