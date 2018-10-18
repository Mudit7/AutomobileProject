package com.training.entity;

import java.util.ArrayList;

public class Customer {

	private String custName;
	private String phoneNo;
	private ArrayList<Vehicle> vehicleList;
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public ArrayList<Vehicle> getVehicleList() {
		return vehicleList;
	}
	public void setVehicleList(ArrayList<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}
	public Customer() {
		super();
	}
	public Customer(String custName, String phoneNo, ArrayList<Vehicle> vehicleList) {
		super();
		this.custName = custName;
		this.phoneNo = phoneNo;
		this.vehicleList = vehicleList;
	}
	@Override
	public String toString() {
		return "Customer [custName=" + custName + ", phoneNo=" + phoneNo + ", vehicleList=" + vehicleList + "]";
	}
	

}
