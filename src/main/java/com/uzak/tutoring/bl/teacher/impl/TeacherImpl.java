package com.uzak.tutoring.bl.teacher.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.uzak.tutoring.bl.teacher.TeacherBL;
import com.uzak.tutoring.common.util.IDaoUtil;
import com.uzak.tutoring.entity.UZTeacher;
import com.uzak.tutoring.entity.UZTeacherIntroduce;
import com.uzak.tutoring.util.AjaxInfo;
import com.uzak.tutoring.util.JSONUtil;
import com.uzak.tutoring.util.StatusCode;

@Service
public class TeacherImpl implements TeacherBL {
	@Autowired
	private IDaoUtil dao;

	@Override
	public AjaxInfo getTeachers(Map<String, Object> params, int pageIndex, int pageSize) {
		StringBuilder sb = new StringBuilder("from UZTeacher t,UZTeacherIntroduce ti where t.id = ti.tid");
		if (params != null) {
			Object[] param = new Object[params.size()];
			int i = 0;

			for (Entry<String, Object> entry : params.entrySet()) {
				param[i] = entry.getValue();
				i++;
				// 待实现
			}
		}

		List teachers = dao.queryByPage(sb.toString(), pageIndex, pageSize);
		Iterator it = teachers.iterator();
		JSONArray jsa = new JSONArray();
		while (it.hasNext()) {
			Object[] tuple = (Object[]) it.next();
			UZTeacher teacher = (UZTeacher) tuple[0];
			UZTeacherIntroduce teacherIntroduce = (UZTeacherIntroduce) tuple[1];
			jsa.add(JSONUtil.merge(teacher.toJSONObject(), teacherIntroduce.toJSONObject()));
		}
		return new AjaxInfo(StatusCode.SUCCESS, "获取成功", jsa);
	}

}
