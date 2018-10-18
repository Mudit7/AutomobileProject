package com.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.training.dao.DAO;
import com.training.entity.*;




public class DAOImpl implements DAO {

private Connection con;
	
	public DAOImpl() {
		super();

		try {
			Context ctx = new InitialContext();
			
			DataSource dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/ds1");
			
			try {
				 con = dataSource.getConnection();
			} catch (SQLException e) {
		
				e.printStackTrace();
			}
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public int addNewCustomer(Customer cust) {
		
		int rowsAdded = 0;
		ArrayList<Vehicle>vehicleList = new ArrayList<>();
		String sql = "insert into customersm values("+"seqm_customerId.nextval"+",?,?)";
		PreparedStatement ps = null;
		PreparedStatement pstm = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, cust.getCustName());
			ps.setString(2, cust.getPhoneNo());
			rowsAdded = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql1 = "insert into vehiclem values("+"seqm_customerId.currval"+",?)";
		try {
			ps = con.prepareStatement(sql1);
			vehicleList = cust.getVehicleList();
			for (Vehicle vehicle : vehicleList) {
				ps.setString(1, vehicle.getVehicleId());
				rowsAdded = ps.executeUpdate();
				String sql2 = "insert into vehicleServicesm values("+"seqm_serviceId.nextval"+",?,?)";
				try{
				pstm = con.prepareStatement(sql2);
				pstm.setString(1, vehicle.getVehicleId());
				if(vehicle.isPolishing()){
					pstm.setString(2, "polishing");
					rowsAdded = pstm.executeUpdate();
				}
				if(vehicle.isWheelBalancing()){
					pstm.setString(2, "wheelBalancing");
					rowsAdded = pstm.executeUpdate();
				}
				if(vehicle.isDecors()){
					pstm.setString(2, "decors");
					rowsAdded = pstm.executeUpdate();
				}
				System.out.println("***********Added****************");
				}catch(SQLException e){
					e.printStackTrace();
				}
				
				finally{
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
									
			}
			
		}
	} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		
		return rowsAdded;	
	}

	@Override
	public Customer retrieveCustomerDetails(String mobileNumber) {
		
		Customer cust = new Customer();
		String sql = "select * from customersm cust natural join vehiclem where mobileNumber = ?";
		PreparedStatement ps  = null;
		String vehicleNo = "";
		Set<String>vehicleNoList = null;
		 vehicleNoList = new HashSet<>();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mobileNumber);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String customerName = rs.getString("customerName");
				
				cust.setCustName(customerName);
				cust.setPhoneNo(mobileNumber);
				vehicleNo = rs.getString("vehicleNo");
				vehicleNoList.add(vehicleNo);				
			}
				ArrayList<Vehicle>vehicleList = retrieveServiceDetails(vehicleNoList);
				cust.setVehicleList(vehicleList);		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cust;
	}

	private ArrayList<Vehicle> retrieveServiceDetails(Set<String> vehicleNoList) {
		ArrayList<Vehicle>vehicleList ;
		vehicleList = new ArrayList<>();
		PreparedStatement pstm = null;
		String sql1 = "Select * from vehicleServicesm where vehicleNo = ?";
		try {
			pstm = con.prepareStatement(sql1);
			for (String vehicleNumber : vehicleNoList) {
				Vehicle vehicle = new Vehicle();
				pstm.setString(1, vehicleNumber);
				ResultSet rs = pstm.executeQuery();
				while(rs.next()){
					
					String serviceName = rs.getString("serviceName");
					//System.out.println(serviceName);
					if(serviceName.equals("polishing")){
						vehicle.setPolishing(true);
					}
					
					if(serviceName.equals("wheelBalancing")){
						vehicle.setWheelBalancing(true);
					}
					
					if(serviceName.equals("decors")){
						vehicle.setDecors(true);
					}	
				}
				vehicle.setVehicleId(vehicleNumber);
				vehicleList.add(vehicle);
			}
			System.out.println(vehicleList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vehicleList;
	}

	@Override
	public int deleteCustomer(String mobileNumber) {
		PreparedStatement pstm = null;
		int rowsDeleted = 0;
		String sql = "delete from customersm where mobileNumber = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, mobileNumber);
			System.out.println(mobileNumber);
			rowsDeleted = pstm.executeUpdate();
			System.out.println("***********Deleted****************");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowsDeleted;
	}

	@Override
	public int updateCustDetails(Customer cust) {
		
	
		System.out.println("***updating******");
		System.out.println(cust);
		int rowsUpdated = addNewCustomer(cust);
		return rowsUpdated;
	}

}

