package com.musala.task.gateways.models.service;

import static org.mockito.Mockito.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.musala.task.gateways.enums.DeviceStatus;
import com.musala.task.gateways.models.data.request.DeviceRequest;
import com.musala.task.gateways.models.data.request.GatewayRequest;
import com.musala.task.gateways.models.data.response.DeviceResponse;
import com.musala.task.gateways.models.data.response.GatewayResponse;
import com.musala.task.gateways.models.entity.DeviceEntity;
import com.musala.task.gateways.models.entity.GatewayEntity;
import com.musala.task.gateways.models.repository.IDeviceRepository;
import com.musala.task.gateways.models.repository.IGatewayRepository;

@ExtendWith(MockitoExtension.class)
class GatewayServiceImplTest {
	
	@InjectMocks
	GatewayServiceImpl gatewayService;
	
	@Mock
	IGatewayRepository gatewayRepository;
	
	@Mock
	IDeviceRepository deviceRepository;
	
	@Test
	void testCreateGateway() {
		// gateway data
		String serialNumber = "test-123456789";
		String name = "gateway 1";
		String ipAddress = "192.168.100.1";
		Long id = Long.valueOf(1);
		
		// and arbitrary gateway
		GatewayEntity savedEntity = new GatewayEntity();
		savedEntity.setId(id);
		savedEntity.setIpAddress(ipAddress);
		savedEntity.setName(name);
		savedEntity.setSerialNumber(serialNumber);
		
		// mock the GatewayRepository save function
		when(gatewayRepository.save(any(GatewayEntity.class))).thenReturn(savedEntity);
		
		// define GatewayRequest
		GatewayRequest request = new GatewayRequest();
		request.setIpAddress(ipAddress);
		request.setName(name);
		request.setSerialNumber(serialNumber);
		
		GatewayResponse response = gatewayService.createGateway(request);
		
		// define expected response
		GatewayResponse expectedRespose = new GatewayResponse();
		expectedRespose.setId(id);
		expectedRespose.setIpAddress(ipAddress);
		expectedRespose.setName(name);
		expectedRespose.setSerialNumber(serialNumber);
		
		assertEquals(expectedRespose, response);
	}
	
	@Test
	void testCreateGatewayDevice() {
		// gateway data
		String serialNumber = "test-123456789";
		String name = "gateway 1";
		String ipAddress = "192.168.100.1";
		Long id = Long.valueOf(1);
		long simulatedNumberOfRelatedDevices = 5; // allow insertion
		
		// an arbitrary gateway
		GatewayEntity gateway = new GatewayEntity(serialNumber, name, ipAddress);
		gateway.setId(id);
		Optional<GatewayEntity> optional = Optional.of(gateway);
		
		// device data
		Long uid = Long.valueOf(999999999);
		String vendor = "MUSALA SOFT";
		DeviceStatus status = DeviceStatus.ONLINE;
		Long deviceId = Long.valueOf(1);
		
		// an arbitrary device related to the previously defined gateway;
		DeviceEntity savedDevice = new DeviceEntity(uid, vendor, status);
		savedDevice.setId(deviceId);
		savedDevice.setGateway(gateway);
		
		// define mocks for all invoked repositories's methods
		when(deviceRepository.countAllByGatewayId(any(Long.class))).thenReturn(simulatedNumberOfRelatedDevices);
		when(gatewayRepository.findById(any(Long.class))).thenReturn(optional);
		when(deviceRepository.save(any(DeviceEntity.class))).thenReturn(savedDevice);
		
		// define DeviceRequest
		DeviceRequest request = new DeviceRequest();
		request.setStatus(status.toString());
		request.setUid(uid);
		request.setVendor(vendor);
		
		DeviceResponse response = gatewayService.createGatewayDevice(id, request);
		
		// define expected response
		DeviceResponse expectedResponse = new DeviceResponse();
		expectedResponse.setId(deviceId);
		expectedResponse.setStatus(status.toString());
		expectedResponse.setUid(uid);
		expectedResponse.setVendor(vendor);
		
		assertEquals(expectedResponse, response);
	}
	
	@Test
	void testCreateGatewayDeviceRejectedMaxNumDevicesReached() {
		// gateway data
		String serialNumber = "test-123456789";
		String name = "gateway 1";
		String ipAddress = "192.168.100.1";
		Long id = Long.valueOf(1);
		long simulatedNumberOfRelatedDevices = 10; // do not allow insertion
		
		// an arbitrary gateway
		GatewayEntity gateway = new GatewayEntity(serialNumber, name, ipAddress);
		gateway.setId(id);
		
		// device data
		Long uid = Long.valueOf(999999999);
		String vendor = "MUSALA SOFT";
		DeviceStatus status = DeviceStatus.ONLINE;
		
		// define mocks for just the invoked method
		when(deviceRepository.countAllByGatewayId(any(Long.class))).thenReturn(simulatedNumberOfRelatedDevices);

		// define DeviceRequest
		DeviceRequest request = new DeviceRequest();
		request.setStatus(status.toString());
		request.setUid(uid);
		request.setVendor(vendor);
		
		try {
			gatewayService.createGatewayDevice(id, request);
			fail("Must capture a RuntimeException because max-number-of-devices is reached!");
		} catch (RuntimeException e) {
			assertTrue(true);
		}	
	}

}
