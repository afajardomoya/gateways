package com.musala.task.gateways.models.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.musala.task.gateways.enums.DeviceStatus;
import com.musala.task.gateways.models.entity.DeviceEntity;
import com.musala.task.gateways.models.entity.GatewayEntity;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class IDeviceRepositoryTest {

	@Autowired
	private IDeviceRepository deviceRepository;
	
	@Autowired
	private IGatewayRepository gatewayRepository;
	
	@Test
	void injectedComponentsAreNotNull() {
		assertThat(deviceRepository).isNotNull();
		assertThat(gatewayRepository).isNotNull();
	}
	
	@Test
	void shouldSaveDevice() {
		if (gatewayRepository.count() == 0) {
			fail("There not exist any gateway in database!");
		}
		GatewayEntity gateway = gatewayRepository.findAll().get(0);
		
		Long uid = Long.valueOf(999999999);
		String vendor = "MUSALA SOFT";
		DeviceStatus status = DeviceStatus.ONLINE;
		
		DeviceEntity device = new DeviceEntity(uid, vendor, status);
		device.setGateway(gateway);

		DeviceEntity savedDevice = deviceRepository.save(device);
		DeviceEntity controlDevice = deviceRepository.findByUid(uid);

		assertEquals(controlDevice.getId(), savedDevice.getId());
	}
	
	@Test
	void shouldNotSaveDeviceBecauseDuplicatedUid() {
		if (gatewayRepository.count() == 0) {
			fail("There not exist any gateway in database!");
		}
		GatewayEntity gateway = gatewayRepository.findAll().get(0);
		
		Long uid = Long.valueOf(999999999);
		String vendor = "MUSALA SOFT";
		DeviceStatus status = DeviceStatus.ONLINE;
		
		DeviceEntity device1 = new DeviceEntity(uid, vendor, status);
		device1.setGateway(gateway);
		DeviceEntity device2 = new DeviceEntity(uid, vendor, status);
		device2.setGateway(gateway);

		deviceRepository.save(device1);

		try {
			deviceRepository.save(device2);
			fail("An entity was created with duplicated Uid!");
		} catch (DataIntegrityViolationException e) {
			assertTrue(true);
		}
	}
	
	@Test
	void fieldsShouldNotBeNull() {
		if (gatewayRepository.count() == 0) {
			fail("There not exist any gateway in database!");
		}
		GatewayEntity gateway = gatewayRepository.findAll().get(0);
		
		Long uid = Long.valueOf(999999999);
		String vendor = "MUSALA SOFT";
		DeviceStatus status = DeviceStatus.ONLINE;
		
		DeviceEntity device;
		
		device = new DeviceEntity(null, vendor, status);
		device.setGateway(gateway);
		try {
			deviceRepository.save(device);
			fail("Device uid can't be null!");
		} catch (ConstraintViolationException e) {
			assertTrue(true);
		}
		
		device = new DeviceEntity(uid, null, status);
		device.setGateway(gateway);
		try {
			deviceRepository.save(device);
			fail("Device vendor can't be null!!");
		} catch (ConstraintViolationException e) {
			assertTrue(true);
		}
		
		device = new DeviceEntity(uid, vendor, null);
		device.setGateway(gateway);
		try {
			deviceRepository.save(device);
			fail("Device status can't be null!!");
		} catch (ConstraintViolationException e) {
			assertTrue(true);
		}
	}
}
