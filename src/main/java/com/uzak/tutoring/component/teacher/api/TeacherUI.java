package com.uzak.tutoring.component.teacher.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uzak.tutoring.code.StatusCode;
import com.uzak.tutoring.component.teacher.bl.TeacherBL;
import com.uzak.tutoring.pojo.AjaxInfo;

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
	@GetMapping(value = "/list")
	public AjaxInfo list(@RequestParam int pageSize, @RequestParam int pageIndex) {
		return teacherBL.getTeachers(null, pageIndex, pageSize);
	}

	@PutMapping(value = "/status")
	public AjaxInfo setStatus(@RequestParam long id) {
		if (id == 0) {
			return new AjaxInfo(StatusCode.FAIL, "教师编号为空");
		}
		return teacherBL.setStatus(id);
	}
}
