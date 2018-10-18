package com.training.entity;

public class Vehicle {
	private String vehicleId;
	private boolean polishing;
	private boolean wheelBalancing;
	private boolean decors;
	public Vehicle(String vehicleId, boolean polishing, boolean wheelBalancing, boolean decors) {
		super();
		this.vehicleId = vehicleId;
		this.polishing = polishing;
		this.wheelBalancing = wheelBalancing;
		this.decors = decors;
	}
	
	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public boolean isPolishing() {
		return polishing;
	}
	public void setPolishing(boolean polishing) {
		this.polishing = polishing;
	}
	public boolean isWheelBalancing() {
		return wheelBalancing;
	}
	public void setWheelBalancing(boolean wheelBalancing) {
		this.wheelBalancing = wheelBalancing;
	}
	public boolean isDecors() {
		return decors;
	}
	public void setDecors(boolean decors) {
		this.decors = decors;
	}
	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Vehicle [VehicleId=" + vehicleId + ", polishing=" + polishing + ", wheelBalancing=" + wheelBalancing
				+ ", decors=" + decors + "]";
	}
	
}
