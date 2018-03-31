package com.uzak.tutoring.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uzak.tutoring.annocation.LoggedIn;
import com.uzak.tutoring.common.util.DaoUtil;
import com.uzak.tutoring.entity.UZManager;

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
	DaoUtil<UZManager> dao;

	@RequestMapping("do")
	@ResponseBody
	@LoggedIn("token")
	public String doIt(String a) {
		return "12442141";
	}

	@RequestMapping("save")
	@ResponseBody
	public UZManager save(UZManager manager) {
		if (dao.save(manager)) {
			return manager;
		}
		return null;
	}

	@RequestMapping("get")
	@ResponseBody
	public UZManager get(UZManager manager) {
		if (dao.fill(manager)) {
			return manager;
		} else {
			return null;
		}

	}

	@RequestMapping("update")
	@ResponseBody
	public UZManager update(UZManager manager) {
		dao.update(manager);
		return manager;
	}

	@RequestMapping("delete")
	@ResponseBody
	public UZManager delete(UZManager manager) {
		dao.delete(manager);
		return manager;
	}

	@RequestMapping("list")
	@ResponseBody
	public List<UZManager> list() {
		return dao.query("from Manager", true);
	}

	@RequestMapping("set")
	@ResponseBody
	public Object set() {
		return dao.querySql("select Name from Manager");
	}
}
