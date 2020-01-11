package com.musala.task.gateways.models.data.response;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import com.musala.task.gateways.models.data.GatewayData;
import com.musala.task.gateways.models.entity.GatewayEntity;

public class GatewayResponse extends GatewayData {
	
	public GatewayResponse() {
		super();
	}

	public GatewayResponse(Map<String, Object> model) {
		super(model);
	}
	
	public static Function<GatewayEntity, GatewayResponse> fromEntity() {
		return new Function<GatewayEntity, GatewayResponse>() {

			@Override
			public GatewayResponse apply(GatewayEntity t) {
				return GatewayResponse.convertFromEntity(t);
			}
		};
	}
	
	public static GatewayResponse convertFromEntity(GatewayEntity source) {
		GatewayResponse gatewayResponse = new GatewayResponse();
		gatewayResponse.setId(source.getId());
		gatewayResponse.setIpAddress(source.getIpAddress());
		gatewayResponse.setName(source.getName());
		gatewayResponse.setSerialNumber(source.getSerialNumber());
		return gatewayResponse;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (getClass() != o.getClass()) return false;
		GatewayResponse gatewayResponse = (GatewayResponse) o;
		
		return Objects.equals(this.id, gatewayResponse.getId()) &&
				Objects.equals(this.ipAddress, gatewayResponse.getIpAddress()) &&
				Objects.equals(this.name, gatewayResponse.getName()) &&
				Objects.equals(this.serialNumber, gatewayResponse.getSerialNumber());
	}
}
