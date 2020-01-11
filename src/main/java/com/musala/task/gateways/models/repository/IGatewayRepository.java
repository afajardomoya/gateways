package com.musala.task.gateways.models.repository;

import com.musala.task.gateways.models.entity.GatewayEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGatewayRepository extends JpaRepository<GatewayEntity, Long> {
	
	public GatewayEntity findBySerialNumber(String serialNumber);
}
