package com.uzak.tutoring.component.teacher.bl.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uzak.tutoring.code.StatusCode;
import com.uzak.tutoring.code.YesOrNo;
import com.uzak.tutoring.common.util.IDaoUtil;
import com.uzak.tutoring.component.teacher.bl.TeacherBL;
import com.uzak.tutoring.entity.UZTeacher;
import com.uzak.tutoring.entity.UZTeacherIntroduce;
import com.uzak.tutoring.pojo.AjaxInfo;
import com.uzak.tutoring.util.DateUtil;
import com.uzak.tutoring.util.JSONUtil;

@Service
public class TeacherImpl implements TeacherBL {
	@SuppressWarnings("rawtypes")
	@Autowired
	private IDaoUtil dao;

	@SuppressWarnings("rawtypes")
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
		int total = dao.count(sb.toString());
		Iterator it = teachers.iterator();
		JSONObject result = new JSONObject();
		JSONArray jsa = new JSONArray();
		result.put("total", total);
		while (it.hasNext()) {
			Object[] tuple = (Object[]) it.next();
			UZTeacher teacher = (UZTeacher) tuple[0];
			teacher.setPassword("");
			UZTeacherIntroduce teacherIntroduce = (UZTeacherIntroduce) tuple[1];
			JSONObject jo = JSONUtil.merge(teacher.toJSONObject(), teacherIntroduce.toJSONObject());
			jo.put("birthday", DateUtil.format(teacher.getBirthday(), DateUtil.CHINESE_SIMPLE_FORMAT));
			jsa.add(jo);
		}
		result.put("data", jsa);
		return new AjaxInfo(StatusCode.SUCCESS, "获取成功", result);
	}

	@SuppressWarnings("unchecked")
	@Override
	public AjaxInfo setStatus(long teacherID) {
		UZTeacher teacher = new UZTeacher();
		teacher.setId(teacherID);
		if (dao.fill(teacher)) {
			teacher.setDisable(YesOrNo.YES.equals(teacher.getDisable()) ? YesOrNo.NO : YesOrNo.YES);
			dao.update(teacher);
			return new AjaxInfo(StatusCode.SUCCESS,"操作成功");
		} else {
			return new AjaxInfo(StatusCode.FAIL, "教师不存在");
		}
	}
}
