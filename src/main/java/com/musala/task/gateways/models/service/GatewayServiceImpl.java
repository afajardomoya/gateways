package com.musala.task.gateways.models.service;

import com.musala.task.gateways.models.data.request.DeviceRequest;
import com.musala.task.gateways.models.data.request.GatewayRequest;
import com.musala.task.gateways.models.data.response.DeviceResponse;
import com.musala.task.gateways.models.data.response.GatewayResponse;
import com.musala.task.gateways.models.entity.DeviceEntity;
import com.musala.task.gateways.models.entity.GatewayEntity;
import com.musala.task.gateways.models.repository.IDeviceRepository;
import com.musala.task.gateways.models.repository.IGatewayRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GatewayServiceImpl implements IGatewayService{
	
	private final int allowedDevices = 10;

    @Autowired
    private IGatewayRepository gatewayRepository;

    @Autowired
    private IDeviceRepository deviceRepository;

	@Override
	@Transactional(readOnly = true)
	public Page<GatewayResponse> getAllGateways(Pageable pageable) {
		Page<GatewayEntity> gatewaysList =  this.gatewayRepository.findAll(pageable);
		
		return gatewaysList.map(GatewayResponse.fromEntity());
	}

	@Override
	@Transactional(readOnly = true)
	public GatewayResponse getGateway(Long gatewayId) {
		Optional<GatewayEntity> optional = this.gatewayRepository.findById(gatewayId);
		if (!optional.isPresent()) {
			return null;
		}
		
		return GatewayResponse.convertFromEntity(optional.get());
	}

	@Override
	@Transactional
	public GatewayResponse createGateway(GatewayRequest gatewayRequest) {
		GatewayEntity gatewayEntity = new GatewayEntity();
		gatewayEntity = gatewayRequest.toEntity(gatewayEntity);		
		if (gatewayEntity == null) {
			return null;
		}		
		gatewayEntity = this.gatewayRepository.save(gatewayEntity);
		
		return GatewayResponse.convertFromEntity(gatewayEntity);
	}

	@Override
	@Transactional
	public GatewayResponse updateGateway(Long gatewayId, GatewayRequest gatewayRequest) {
		Optional<GatewayEntity> gatewayOptional = this.gatewayRepository.findById(gatewayId);
		if (!gatewayOptional.isPresent())
			return null;
		GatewayEntity gatewayEntity = gatewayOptional.get();
		gatewayEntity = gatewayRequest.toEntity(gatewayEntity);
		if (gatewayEntity == null)
			return null;
		gatewayEntity = this.gatewayRepository.save(gatewayEntity);
		
		return GatewayResponse.convertFromEntity(gatewayEntity);
	}

	@Override
	@Transactional
	public GatewayResponse deleteGateway(Long gatewayId) {
		Optional<GatewayEntity> gatewayOptional = this.gatewayRepository.findById(gatewayId);
		if (!gatewayOptional.isPresent())
			return null;
		GatewayEntity gatewayEntity = gatewayOptional.get();
		this.gatewayRepository.delete(gatewayEntity);
		
		return GatewayResponse.convertFromEntity(gatewayEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<DeviceResponse> getAllGatewayDevices(Long gatewayId, Pageable pageable) {
		Page<DeviceEntity> devicesList = this.deviceRepository.findAllByGatewayId(gatewayId, pageable);
		
		return devicesList.map(DeviceResponse.fromEntity());
	}

	@Override
	@Transactional(readOnly = true)
	public DeviceResponse getGatewayDevice(Long gatewayId, Long deviceId) {
		Optional<DeviceEntity> optional = this.deviceRepository.findByIdAndGatewayId(deviceId, gatewayId);
		if (!optional.isPresent())
			return null;
		
		return DeviceResponse.convertFromEntity(optional.get());
	}

	@Override
	@Transactional
	public DeviceResponse createGatewayDevice(Long gatewayId, DeviceRequest deviceRequest) {
		long numberOfDevices = this.deviceRepository.countAllByGatewayId(gatewayId);
		if (numberOfDevices >= allowedDevices) {
			throw new RuntimeException("The gateway has reached the maximun number of allowed devices (" + allowedDevices + ").");
		}
		Optional<GatewayEntity> gatewayOptional = this.gatewayRepository.findById(gatewayId);
		if (!gatewayOptional.isPresent())
			return null;
		DeviceEntity deviceEntity = new DeviceEntity();
		deviceEntity = deviceRequest.toEntity(deviceEntity);
		if (deviceEntity == null)
			return null;
		deviceEntity.setGateway(gatewayOptional.get());
		deviceEntity = this.deviceRepository.save(deviceEntity);
		
		return DeviceResponse.convertFromEntity(deviceEntity);
	}

	@Override
	@Transactional
	public DeviceResponse updateGatewayDevice(Long gatewayId, Long deviceId, DeviceRequest deviceRequest) {
		Optional<GatewayEntity> gatewayOptional = this.gatewayRepository.findById(gatewayId);
		if (!gatewayOptional.isPresent())
			return null;		
		Optional<DeviceEntity> deviceOptional = this.deviceRepository.findById(deviceId);
		if (!deviceOptional.isPresent())
			return null;
		DeviceEntity deviceEntity = deviceRequest.toEntity(deviceOptional.get());
		deviceEntity = this.deviceRepository.save(deviceEntity);
		
		return DeviceResponse.convertFromEntity(deviceEntity);
	}

	@Override
	@Transactional
	public DeviceResponse deleteGatewayDevice(Long gatewayId, Long deviceId) {
		Optional<DeviceEntity> optional = this.deviceRepository.findById(deviceId);
		if (!optional.isPresent()) {
			return null;
		}
		DeviceEntity deviceEntity = optional.get();
		if (deviceEntity.getGateway().getId() != gatewayId) {
			return null;
		}
		this.deviceRepository.delete(deviceEntity);
		
		return DeviceResponse.convertFromEntity(deviceEntity);
	}
	
	
}
