package com.uzak.tutoring.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uzak.tutoring.entity.Manager;
import com.uzak.tutoring.repository.ManagerRepository;

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
	private ManagerRepository managerRepository;

	@RequestMapping("getUserByID")
	@ResponseBody
	public Manager getUserByID(Long id) {
		Manager manager = managerRepository.getUserByID(id);
		System.out.println(manager.getName());
		return manager;
	}

	@RequestMapping("/save")
	@ResponseBody
	public void saveUser(Manager m) {
		managerRepository.save(m);
	}
}
