package com.training.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.*;




@Path("/employee")
public class EmpResource {
	
	Connection con;
	String empName;
	public EmpResource() {
		super();
		// TODO Auto-generated constructor stub
		Context ctx;
		try {
			ctx = new InitialContext();
			DataSource dataSource=(DataSource) ctx.lookup("java:comp/env/jdbc/ds1");
			con=dataSource.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		empName="";
	}
	
	@POST
	@Path("/verify")
	public String verifyEmp(@FormParam("empId") String empId,@FormParam("empPass") String empPass) {
		String result="invalid";
		try {
		String sql="select * from empm where empId=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, empId);
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			if(empPass.equals(rs.getString("empPass")))
			result=rs.getString("empName");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
			return result;
	}
	@POST
	@Path("/add")
	public String registerEmp(@FormParam("empNameR") String empName,@FormParam("empPassR") String empPass) {
		String empid="";
		try {
			String sql="insert into empm values(seqm_empid.nextval,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, empName);
			ps.setString(2, empPass);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String sql="select empId from empm where empName=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, empName);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				empid=rs.getString("empId");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return empid;
	}
}
