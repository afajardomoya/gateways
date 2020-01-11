package com.musala.task.gateways.models.data.converters;

import org.springframework.core.convert.converter.Converter;

import com.musala.task.gateways.models.data.response.DeviceResponse;
import com.musala.task.gateways.models.entity.DeviceEntity;

public class DeviceEntityToResponse implements Converter<DeviceEntity, DeviceResponse>{

	@Override
	public DeviceResponse convert(DeviceEntity source) {
		return DeviceResponse.convertFromEntity(source);
	}

}
