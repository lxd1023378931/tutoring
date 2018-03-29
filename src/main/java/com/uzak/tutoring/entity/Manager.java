package com.uzak.tutoring.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.uzak.tutoring.common.dao.Dao;

/**
 * 管理员实体类
 * 
 * @author Liang Xiudou
 * @date 2018/3/28
 */
@Entity
@Table(name = "Manager")
public class Manager extends Dao<Manager> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * 用户名
	 */
	@Id
	@Column
	private String name;
	/**
	 * 真实姓名
	 */
	@Column
	private String realName;
	/**
	 * 登录密码
	 */
	@Column
	private String password;
	/**
	 * 联系方式
	 */
	@Column
	private String tel;
	/**
	 * 角色ID
	 */
	@Column
	private long roleID;
	/**
	 * 部门ID
	 */
	@Column
	private long departmentID;
	/**
	 * 备用字段1
	 */
	@Column
	private String prop1;
	/**
	 * 备用字段2
	 */
	@Column
	private String prop2;
	/**
	 * 备用字段3
	 */
	@Column
	private String prop3;
	/**
	 * 添加人
	 */
	@Column
	private String addUser;
	/**
	 * 添加时间
	 */
	private Date addTime;
	/**
	 * 修改人
	 */
	@Column
	private String modifyUser;
	/**
	 * 修改时间
	 */
	private Date modifyTime;

	/**
	 * 删除标记位
	 */
	@Column
	protected String isDel;

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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public long getRoleID() {
		return roleID;
	}

	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}

	public long getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(long departmentID) {
		this.departmentID = departmentID;
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

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
}
