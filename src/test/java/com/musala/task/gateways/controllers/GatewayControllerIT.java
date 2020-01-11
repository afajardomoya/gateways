package com.musala.task.gateways.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.task.gateways.models.data.response.GatewayResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class GatewayControllerIT {

	String body3 = "{\"serialNumber\": \"sn-gateway3\", \"name\": \"gateway 3\", \"ipAddress\": \"10.12.16.173\"}";
	String body4 = "{\"serialNumber\": \"sn-gateway4\", \"name\": \"gateway 4\", \"ipAddress\": \"10.12.16.174\"}";
	String body5 = "{\"serialNumber\": \"sn-gateway5\", \"name\": \"gateway 5\", \"ipAddress\": \"10.12.16.175\"}";
	String body6 = "{\"serialNumber\": \"sn-gateway6\", \"name\": \"gateway 6\", \"ipAddress\": \"10.12.16.176\"}";

	private MockMvc mockMvc;
	
//	@Autowired
//	GatewayController controller;
	
	@Autowired
	private WebApplicationContext context;
	
	@BeforeAll
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	void injectedComponentsAreNotNull() {
		assertThat(mockMvc).isNotNull();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testGetGatewayCreated() {
		try {
			Map<String, Object> requestBody3 = new ObjectMapper().readValue(body3, Map.class);
			Map<String, Object> requestBody4 = new ObjectMapper().readValue(body4, Map.class);
			Map<String, Object> requestBody5 = new ObjectMapper().readValue(body5, Map.class);
			Map<String, Object> requestBody6 = new ObjectMapper().readValue(body6, Map.class);
			
			System.out.println(requestBody3.get("serialNumber").getClass());
			
			GatewayResponse response3 = new GatewayResponse(requestBody3);
			response3.setId(Long.valueOf(3));			
			GatewayResponse response4 = new GatewayResponse(requestBody4);
			response4.setId(Long.valueOf(4));			
			GatewayResponse response5 = new GatewayResponse(requestBody5);
			response5.setId(Long.valueOf(5));			
			GatewayResponse response6 = new GatewayResponse(requestBody6);
			response6.setId(Long.valueOf(6));
		
			testCreateDate(body3, response3);
			testCreateDate(body4, response4);
			testCreateDate(body5, response5);
			testCreateDate(body6, response6);
			
		} catch (JsonProcessingException e) {
			fail(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	private void testCreateDate(String requestBody, GatewayResponse expectedResponse) {
		try {
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/gateways")
					.content(requestBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andReturn();
			String responseContent = result.getResponse().getContentAsString();
			System.out.println(responseContent);
			assertNotNull(responseContent);
			Map<String, Object> mapResponseContent = new ObjectMapper().readValue(responseContent, Map.class);
			GatewayResponse gatewayResponse = new GatewayResponse(mapResponseContent);
			assertEquals(expectedResponse, gatewayResponse);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
