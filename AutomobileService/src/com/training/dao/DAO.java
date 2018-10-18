package com.training.dao;

import com.training.entity.Customer;

public interface DAO {
	public int addNewCustomer(Customer cust);
	public Customer retrieveCustomerDetails(String mobileNumber);
	public int deleteCustomer(String mobileNumber);
	public int updateCustDetails(Customer cust);
}
