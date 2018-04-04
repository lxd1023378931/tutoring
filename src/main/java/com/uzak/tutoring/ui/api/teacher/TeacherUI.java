package com.uzak.tutoring.ui.api.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uzak.tutoring.bl.teacher.TeacherBL;
import com.uzak.tutoring.util.AjaxInfo;

@RestController
@RequestMapping("api/t/teacher/")
public class TeacherUI {
	@Autowired
	TeacherBL teacherBL;
	/**
	 * 获取教师列表
	 * 
	 * @param manager
	 * @return
	 */
	@GetMapping(value="/list")
	public AjaxInfo logout() {
		AjaxInfo info = new AjaxInfo();
		info = teacherBL.getTeachers(null, 0, 1);
		return info;
	}
}
