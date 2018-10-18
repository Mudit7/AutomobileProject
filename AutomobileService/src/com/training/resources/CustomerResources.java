package com.training.resources;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.training.dao.DAO;
import com.training.dao.impl.DAOImpl;
import com.training.entity.Customer;

@Path("customer")
public class CustomerResources {
	
	DAO customerDao = null;
	
	public CustomerResources() {
		super();
		customerDao = new DAOImpl();
	}
	@POST
	@Path("addCust")
	@Consumes(MediaType.APPLICATION_JSON)
	public int addCustomer(Customer cust){
		int rowsAdded = customerDao.addNewCustomer(cust);
		return rowsAdded;
	}
	@GET
	@Path("getCust")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getDetails(@QueryParam("phoneNo")String mobileNumber){
		Customer cust = customerDao.retrieveCustomerDetails(mobileNumber);
		return cust;
	}
	
	@GET
	@Path("deleteCust")
	@Produces(MediaType.APPLICATION_JSON)
	public int deleteCustomer(@QueryParam("phoneNo")String mobileNumber){
		int rowsDeleted=0;
		 rowsDeleted = customerDao.deleteCustomer(mobileNumber);
		return rowsDeleted;
	}
	@POST
	@Path("updateCust")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCustomer(Customer cust) {
		customerDao.updateCustDetails(cust);
	}
}
