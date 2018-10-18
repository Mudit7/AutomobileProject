package com.training.entity;

public class Employee {
	String empName;
	String userId;
	String password;
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Employee(String empName, String userId, String password) {
		super();
		this.empName = empName;
		this.userId = userId;
		this.password = password;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
