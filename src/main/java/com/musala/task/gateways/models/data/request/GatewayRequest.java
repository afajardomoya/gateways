package com.musala.task.gateways.models.data.request;

import java.util.Map;

import com.musala.task.gateways.models.data.GatewayData;
import com.musala.task.gateways.models.entity.GatewayEntity;

public class GatewayRequest extends GatewayData {

	public GatewayRequest() {
		super();
	}

	public GatewayRequest(Map<String, Object> model) {
		super(model);
	}

	public GatewayEntity toEntity(GatewayEntity gatewayEntity) {
		GatewayEntity entity = gatewayEntity;
		if (this.getId() != null) {
			if (gatewayEntity.getId() != null) {
				if (this.getId() != gatewayEntity.getId()) {
					return null;
				}
			} else {
				entity.setId(this.getId());
			}
		}

		if (this.getIpAddress() != "")
			entity.setIpAddress(this.getIpAddress());

		if (this.getName() != "")
			entity.setName(this.getName());

		if (this.getSerialNumber() != "")
			entity.setSerialNumber(this.getSerialNumber());

		return entity;
	}
}
