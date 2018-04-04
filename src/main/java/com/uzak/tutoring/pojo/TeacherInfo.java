package com.uzak.tutoring.pojo;

import java.util.Date;

import com.uzak.tutoring.common.dao.Dao;

public class TeacherInfo extends Dao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -463202073872015860L;
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 地区
	 */
	private String address;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 学历
	 */
	private String education;
	/**
	 * 专业
	 */
	private String major;
	/**
	 * 学校
	 */
	private String school;
	/**
	 * 户籍
	 */
	private String navitePlace;
	/**
	 * 上次登录时间
	 */
	private Date lastLoginTime;
	/**
	 * 头像
	 */
	private String logo;
	private String prop1;

	private String prop2;

	private String prop3;

	private String prop4;

	private String addUser;

	private Date addTime;

	private String modifyUser;

	private Date modifyTime;
	private String disable;

	/**
	 * 可教科目
	 */

	private String subject;
	/**
	 * 个人介绍
	 */
	private String describution;
	/**
	 * 特长
	 */
	private String talent;
	/**
	 * 爱好
	 */
	private String hobby;
	/**
	 * 可教区域
	 */

	private String area;
	/**
	 * 授课方式
	 */

	private String teachingMethod;
	/**
	 * 证书
	 */

	private String credential;
	/**
	 * 工资
	 */

	private String salary;
	/**
	 * 工资单位
	 */

	private String salaryUnit;
	/**
	 * 本站评分（1-10分，初始6分）
	 */
	private Integer grade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getNavitePlace() {
		return navitePlace;
	}

	public void setNavitePlace(String navitePlace) {
		this.navitePlace = navitePlace;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getProp1() {
		return prop1;
	}

	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}

	public String getProp2() {
		return prop2;
	}

	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}

	public String getProp3() {
		return prop3;
	}

	public void setProp3(String prop3) {
		this.prop3 = prop3;
	}

	public String getProp4() {
		return prop4;
	}

	public void setProp4(String prop4) {
		this.prop4 = prop4;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getDisable() {
		return disable;
	}

	public void setDisable(String disable) {
		this.disable = disable;
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
