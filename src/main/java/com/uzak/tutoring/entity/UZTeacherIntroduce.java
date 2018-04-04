package com.uzak.tutoring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.uzak.tutoring.common.dao.Dao;

/**
 * 老师综合信息
 * 
 * @author 梁秀斗
 * @mail liangxiudou@zving.com
 * @date 2018年4月4日
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UZTeacherIntroduce")
public class UZTeacherIntroduce extends Dao {
	@Id
	private Long tid;
	/**
	 * 可教科目
	 */
	@Column
	private String subject;
	/**
	 * 个人介绍
	 */
	@Column(length = 3000)
	private String describution;
	/**
	 * 特长
	 */
	@Column(length = 2000)
	private String talent;
	/**
	 * 爱好
	 */
	@Column(length = 1000)
	private String hobby;
	/**
	 * 可教区域
	 */
	@Column
	private String area;
	/**
	 * 授课方式
	 */
	@Column
	private String teachingMethod;
	/**
	 * 证书
	 */
	@Column
	private String credential;
	/**
	 * 工资
	 */
	@Column
	private String salary;
	/**
	 * 工资单位
	 */
	@Column
	private String salaryUnit;
	/**
	 * 本站评分（1-10分，初始6分）
	 */
	private Integer grade;

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescribution() {
		return describution;
	}

	public void setDescribution(String describution) {
		this.describution = describution;
	}

	public String getTalent() {
		return talent;
	}

	public void setTalent(String talent) {
		this.talent = talent;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTeachingMethod() {
		return teachingMethod;
	}

	public void setTeachingMethod(String teachingMethod) {
		this.teachingMethod = teachingMethod;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getSalaryUnit() {
		return salaryUnit;
	}

	public void setSalaryUnit(String salaryUnit) {
		this.salaryUnit = salaryUnit;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

}
