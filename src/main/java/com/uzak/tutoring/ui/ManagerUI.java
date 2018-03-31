package com.uzak.tutoring.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uzak.tutoring.annocation.LoggedIn;
import com.uzak.tutoring.common.util.DaoUtil;
import com.uzak.tutoring.entity.Manager;

/**
 * 管理员接口
 * 
 * @author Liang Xiudou
 * @date 2018/3/28
 */
@Controller
@RequestMapping("/api/v/")
public class ManagerUI {
	@Autowired
	DaoUtil<Manager> dao;

	@RequestMapping("do")
	@ResponseBody
	@LoggedIn("token")
	public String doIt(String a) {
		return "12442141";
	}

	@RequestMapping("save")
	@ResponseBody
	public Manager save(Manager manager) {
		if (dao.save(manager)) {
			return manager;
		}
		return null;
	}

	@RequestMapping("get")
	@ResponseBody
	public Manager get(Manager manager) {
		if (dao.fill(manager)) {
			return manager;
		} else {
			return null;
		}

	}

	@RequestMapping("update")
	@ResponseBody
	public Manager update(Manager manager) {
		dao.update(manager);
		return manager;
	}

	@RequestMapping("delete")
	@ResponseBody
	public Manager delete(Manager manager) {
		dao.delete(manager);
		return manager;
	}

	@RequestMapping("list")
	@ResponseBody
	public List<Manager> list() {
		return dao.query("from Manager", true);
	}

	@RequestMapping("set")
	@ResponseBody
	public Object set() {
		return dao.querySql("select Name from Manager");
	}
}
