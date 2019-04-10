package com.reportinformationsystem;

public class Pojo {

	private String vesselName;
	private String url;
	private String port;
	
	
	public String getPort() {
		return port;
	}
	public String getVesselName() {
		return vesselName;
	}
	public String getUrl() {
		return url;
	}
	
	public Pojo() {
		// TODO Auto-generated constructor stub
	}
	
	public Pojo(String port) {
		super();
		this.port = port;
	}
	
	public Pojo(String vesselName, String url) {
		super();
		this.vesselName = vesselName;
		this.url = url;
	}
	
	
	
	
}
