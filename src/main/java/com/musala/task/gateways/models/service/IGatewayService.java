package com.musala.task.gateways.models.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.musala.task.gateways.models.data.request.DeviceRequest;
import com.musala.task.gateways.models.data.request.GatewayRequest;
import com.musala.task.gateways.models.data.response.DeviceResponse;
import com.musala.task.gateways.models.data.response.GatewayResponse;

public interface IGatewayService {
	
	public Page<GatewayResponse> getAllGateways(Pageable pageable);
	
	public GatewayResponse getGateway(Long gatewayId);
	
	public GatewayResponse createGateway(GatewayRequest gatewayRequest);
	
	public GatewayResponse updateGateway(Long gatewayId, GatewayRequest gatewayRequest);
	
	public GatewayResponse deleteGateway(Long gatewayId);
	
	public Page<DeviceResponse> getAllGatewayDevices(Long gatewayId, Pageable pageable);
	
	public DeviceResponse getGatewayDevice(Long gatewayId, Long deviceId);
	
	public DeviceResponse createGatewayDevice(Long gatewayId, DeviceRequest deviceRequest);
	
	public DeviceResponse updateGatewayDevice(Long gatewayId, Long deviceId, DeviceRequest deviceRequest);
	
	public DeviceResponse deleteGatewayDevice(Long gatewayId, Long deviceId);
}
