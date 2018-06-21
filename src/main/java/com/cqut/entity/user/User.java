package com.cqut.entity.user;

import com.cqut.entity.base.Entity;

public class User extends Entity{
	private String id;
	private String user_name;
	private String telephone;
	private String department_id;
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", user_name=" + user_name + ", telephone="
				+ telephone + ", department_id=" + department_id + ", remark="
				+ remark + "]";
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "User";
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return "id";
	}
}
