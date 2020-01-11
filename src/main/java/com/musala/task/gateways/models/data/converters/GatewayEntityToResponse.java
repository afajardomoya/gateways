package com.musala.task.gateways.models.data.converters;

import org.springframework.core.convert.converter.Converter;

import com.musala.task.gateways.models.data.response.GatewayResponse;
import com.musala.task.gateways.models.entity.GatewayEntity;

public class GatewayEntityToResponse implements Converter<GatewayEntity, GatewayResponse>{

	@Override
	public GatewayResponse convert(GatewayEntity source) {
		return GatewayResponse.convertFromEntity(source);
	}
	
}
