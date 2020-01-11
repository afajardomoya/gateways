package com.musala.task.gateways.models.data.request;

import java.util.Map;

import com.musala.task.gateways.enums.DeviceStatus;
import com.musala.task.gateways.models.data.DeviceData;
import com.musala.task.gateways.models.entity.DeviceEntity;

public class DeviceRequest extends DeviceData{
	
	public DeviceRequest() {
		super();
	}
	
	public DeviceRequest(Map<String, Object> model) {
		super(model);
	}

	public DeviceEntity toEntity(DeviceEntity deviceEntity) {
		DeviceEntity entity = deviceEntity;
		if (this.getId() != null) {
			if (deviceEntity.getId() != null) {
				if (this.getId() != deviceEntity.getId()) {
					return null;
				}
			} else {
				entity.setId(this.getId());
			}
		}
		
		if (this.getUid() != null)
			entity.setUid(this.getUid());
		
		if (this.getVendor() != "")
			entity.setVendor(this.getVendor());
		
		if (this.getStatus() != "") 
			entity.setStatus(DeviceStatus.valueOf(this.getStatus()));
			
		return entity;
	}
}
