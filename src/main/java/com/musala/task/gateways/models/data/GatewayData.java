package com.musala.task.gateways.models.data;

import java.util.Map;

public class GatewayData {
	protected Long id;
	protected String serialNumber;
	protected String name;
	protected String ipAddress;
	
	public GatewayData() {
		
	}
	
	public GatewayData(Map<String, Object> model) {
		if (model.get("id") != null) {
			this.id = ((Integer) model.get("id")).longValue();
		}
		this.serialNumber = (String) model.getOrDefault("serialNumber", "");
		this.name = (String) model.getOrDefault("name", "");
		this.ipAddress = (String) model.getOrDefault("ipAddress", "");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}	
}
