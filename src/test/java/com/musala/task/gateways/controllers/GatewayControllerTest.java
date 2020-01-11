package com.musala.task.gateways.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.musala.task.gateways.models.data.request.GatewayRequest;
import com.musala.task.gateways.models.data.response.GatewayResponse;
import com.musala.task.gateways.models.service.IGatewayService;

@ExtendWith(MockitoExtension.class)
class GatewayControllerTest {
	
	@InjectMocks
	GatewayController gatewayController;

	@Mock
	IGatewayService gatewaysService;
	
	@Test
	void testCreateGateway() {
		String serialNumber = "test-123456789";
		String name = "gateway 1";
		String ipAddress = "192.168.100.1";
		Long id = Long.valueOf(1);
		
		MockHttpServletRequest request = new MockHttpServletRequest();		
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		GatewayResponse gatewayResponse = new GatewayResponse();
		gatewayResponse.setSerialNumber(serialNumber);
		gatewayResponse.setName(name);
		gatewayResponse.setIpAddress(ipAddress);
		gatewayResponse.setId(id);		
		
		when(gatewaysService.createGateway(any(GatewayRequest.class))).thenReturn(gatewayResponse);
		
		Map<String, Object> requestBody = new HashMap<String, Object>();
		requestBody.put("serialNumber", serialNumber);
		requestBody.put("name", name);
		requestBody.put("ipAddress", ipAddress);
		
		ResponseEntity<GatewayResponse> responseEntity = gatewayController.createGateway(requestBody);
		
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}
}
