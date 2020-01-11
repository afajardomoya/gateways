package com.musala.task.gateways.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import com.musala.task.gateways.models.data.converters.DeviceEntityToResponse;
import com.musala.task.gateways.models.data.converters.GatewayEntityToResponse;

@Configuration
public class ConversionConfig {
	
	@SuppressWarnings("rawtypes")
	private Set<Converter> getConverters() {
		Set<Converter> converters = new HashSet<Converter>();
		
		// Gateway converter
		converters.add(new GatewayEntityToResponse());
		
		// Device converter
		converters.add(new DeviceEntityToResponse());
		
		return converters;
	}
	
	@Bean
	public ConversionService conversionService() {
		ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
		bean.setConverters(getConverters());
		bean.afterPropertiesSet();
		
		return bean.getObject();
	}

}
