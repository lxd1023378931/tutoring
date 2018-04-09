package com.uzak.tutoring.component.teacher.bl;
/**
 * 
 * @author 梁秀斗
 * @mail liangxiudou@zving.com
 * @date 2018年4月4日
 */

import java.util.Map;

import com.uzak.tutoring.pojo.AjaxInfo;

/**
 * 教师操作类
 * 
 * @author 梁秀斗
 * @mail liangxiudou@zving.com
 * @date 2018年4月4日
 */
public interface TeacherBL {
	/**
	 * 获取教师列表
	 * 
	 * @param params
	 * @return
	 */
	public AjaxInfo getTeachers(Map<String, Object> params, int pageIndex, int pageSize);

	/**
	 * 设置教师禁用或启用
	 * 
	 * @param teacherID
	 * @return
	 */
	public AjaxInfo setStatus(long teacherID);
}
