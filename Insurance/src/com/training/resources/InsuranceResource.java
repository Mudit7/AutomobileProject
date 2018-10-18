package com.training.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.training.dao.impl.DAOImpl;

@Path("/insurance")
public class InsuranceResource {
	
	DAOImpl dao;
	
	public InsuranceResource() {
		 dao=new DAOImpl();
	}

	@GET
	@Path("/getInsurance")
	@Produces(MediaType.TEXT_PLAIN)
	public double getInsurance(@QueryParam("vehicleNum") String vehicleNum,@QueryParam("service") String service) {
		System.out.println("Someone's Claiming something");
		double amt= dao.getInsuranceAmt(vehicleNum, service);
		dao.logInsurance(vehicleNum, service);
		return amt;
	}
	
}
