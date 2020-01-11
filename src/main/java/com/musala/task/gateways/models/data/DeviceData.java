package com.musala.task.gateways.models.data;

import java.util.Map;

public class DeviceData {
	protected Long id;
	protected Long uid;
	protected String vendor;
	protected String status;
	
	public DeviceData() {
		
	}
	
	public DeviceData(Map<String, Object> model) {
		if (model.get("id") != null) {
			this.id = ((Integer) model.get("id")).longValue();
		}
		Object tmpUid = model.getOrDefault("uid", null);
		if (tmpUid == null) {
			this.uid = null;
		} else {
			if (tmpUid.getClass() == Integer.class) {
				this.uid = ((Integer) tmpUid).longValue();
			} else if (tmpUid.getClass() == Long.class) {
				this.uid = (Long) tmpUid;
			} else {
				this.uid = null;
			}
		}
		this.vendor = (String) model.getOrDefault("vendor", "");
		this.status = (String) model.getOrDefault("status", "");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
}
