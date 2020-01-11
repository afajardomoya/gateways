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

import com.musala.task.gateways.models.entity.GatewayEntity;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class IGatewayRepositoryTest {

	@Autowired
	private IGatewayRepository gatewayRepository;

	@Test
	void injectedComponentsAreNotNull() {
		assertThat(gatewayRepository).isNotNull();
	}

	@Test
	void shouldSaveGateway() {
		String serialNumber = "test-123456789";
		String name = "gateway 3";
		String ipAddress = "192.168.100.1";

		GatewayEntity gateway = new GatewayEntity(serialNumber, name, ipAddress);

		GatewayEntity savedGateway = gatewayRepository.save(gateway);
		GatewayEntity controlGateway = gatewayRepository.findBySerialNumber(serialNumber);

		assertEquals(controlGateway.getId(), savedGateway.getId());
	}

	@Test
	void shouldNotSaveGatewayBecauseDuplicatedSerialNumber() {
		String serialNumber = "test-123456789";
		String name = "gateway 3";
		String ipAddress = "192.168.100.1";

		GatewayEntity gateway1 = new GatewayEntity(serialNumber, name, ipAddress);
		GatewayEntity gateway2 = new GatewayEntity(serialNumber, name, ipAddress);

		gatewayRepository.save(gateway1);

		try {
			gatewayRepository.save(gateway2);
			fail("An entity was created with duplicated serial number!");
		} catch (DataIntegrityViolationException e) {
			assertTrue(true);
		}
	}

	@Test
	void shouldNotSaveGatewayBecauseBadIpAddress() {
		String serialNumber = "test-123456789";
		String name = "gateway 3";
		String ipAddresses [] = {
				"", 
				"256.100.2.1", 
				".25.32.6", 
				"aaa.aaa.aaa.aaa", 
				"125.125.125.", 
				"125..125.", 
				"026.12.25.63",
				"..."
				};
		
		GatewayEntity gateway1; 
		
		for (int i = 0; i < ipAddresses.length; ++i) {
			gateway1 = new GatewayEntity(serialNumber, name, ipAddresses[i]);
			try {
				gatewayRepository.save(gateway1);
				fail("An entity was created with incorrect ip address!");
			} catch (ConstraintViolationException e) {
				assertTrue(true);
			}
		}
	}
	
	@Test
	void fieldsShouldNotBeNull() {
		String serialNumber = "test-123456789";
		String name = "gateway 3";
		String ipAddress = "192.168.100.1";

		GatewayEntity gateway;
		
		gateway= new GatewayEntity(null, name, ipAddress);
		try {
			gatewayRepository.save(gateway);
			fail("Gateway serial number can't be null!!");
		} catch (ConstraintViolationException e) {
			assertTrue(true);
		}
		
		gateway= new GatewayEntity(serialNumber, null, ipAddress);
		try {
			gatewayRepository.save(gateway);
			fail("Gateway name can't be null!!");
		} catch (ConstraintViolationException e) {
			assertTrue(true);
		}
		
		gateway= new GatewayEntity(serialNumber, name, null);
		try {
			gatewayRepository.save(gateway);
			fail("Gateway ip address can't be null!!");
		} catch (ConstraintViolationException e) {
			assertTrue(true);
		}
	}

}
