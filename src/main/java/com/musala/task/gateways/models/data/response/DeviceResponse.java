package com.musala.task.gateways.models.data.response;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import com.musala.task.gateways.models.data.DeviceData;
import com.musala.task.gateways.models.entity.DeviceEntity;

public class DeviceResponse extends DeviceData{
	private Date createdAt;
	
	public DeviceResponse() {
		super();
	}
	
	public DeviceResponse(Map<String, Object> model) {
		super(model);
	}
			
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date date) {
		this.createdAt = date;
	}

	public static Function<DeviceEntity, DeviceResponse> fromEntity() {
		return new Function<DeviceEntity, DeviceResponse>() {

			@Override
			public DeviceResponse apply(DeviceEntity t) {
				return DeviceResponse.convertFromEntity(t);
			}
		};
	}
	
	public static DeviceResponse convertFromEntity(DeviceEntity source) {
		DeviceResponse deviceResponse = new DeviceResponse();
		deviceResponse.setId(source.getId());
		deviceResponse.setStatus(source.getStatus().toString());
		deviceResponse.setUid(source.getUid());
		deviceResponse.setVendor(source.getVendor());
		deviceResponse.setCreatedAt(source.getCreatedAt());
		
		return deviceResponse;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (getClass() != o.getClass()) return false;
		DeviceResponse deviceResponse = (DeviceResponse) o;
		
		return Objects.equals(this.id, deviceResponse.getId()) &&
				Objects.equals(this.status, deviceResponse.getStatus()) &&
				Objects.equals(this.uid, deviceResponse.getUid()) &&
				Objects.equals(this.vendor, deviceResponse.getVendor());		
	}
}
