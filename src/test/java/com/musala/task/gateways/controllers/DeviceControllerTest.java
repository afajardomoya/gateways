package com.musala.task.gateways.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.musala.task.gateways.enums.DeviceStatus;
import com.musala.task.gateways.models.data.request.DeviceRequest;
import com.musala.task.gateways.models.data.response.DeviceResponse;
import com.musala.task.gateways.models.service.IGatewayService;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class DeviceControllerTest {
	
	@InjectMocks
	DeviceController controller;
	
	@Mock
	IGatewayService gatewaysService;

	@Test
	void testCreateGatewayDevice() {
		Long gatewayId = Long.valueOf(1);
		
		// device data
		Long deviceId = Long.valueOf(1);
		Long uid = Long.valueOf(999999999);
		String vendor = "MUSALA SOFT";
		DeviceStatus status = DeviceStatus.OFFLINE;
		
		MockHttpServletRequest request = new MockHttpServletRequest();		
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		// define response
		DeviceResponse deviceResponse = new DeviceResponse();
		deviceResponse.setId(deviceId);
		deviceResponse.setStatus(status.toString());
		deviceResponse.setUid(uid);
		deviceResponse.setVendor(vendor);
		
		// mock the createGatewayDevice method
		when(gatewaysService.createGatewayDevice(any(Long.class), any(DeviceRequest.class))).thenReturn(deviceResponse);
		
		// define request body
		Map<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("uid", uid);
		requestBody.put("status", status.toString());
		requestBody.put("vendor", vendor);
		
		ResponseEntity<Object> responseEntity = controller.createGatewayDevice(gatewayId, requestBody);
		
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}

}
