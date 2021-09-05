package com.example.snewapp;

public class ServiceModel {
	
	private String serviceName;
	
	private int serviceImage;
	
	public ServiceModel(String name,int image) {
		// TODO Auto-generated constructor stub
		this.serviceName = name;
		this.serviceImage = image;
		
		
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getServiceImage() {
		return serviceImage;
	}

	public void setServiceImage(int serviceImage) {
		this.serviceImage = serviceImage;
	}
	
	
	

}
