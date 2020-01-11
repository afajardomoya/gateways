package com.musala.task.gateways.models.repository;

import com.musala.task.gateways.models.entity.DeviceEntity;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeviceRepository extends JpaRepository<DeviceEntity, Long> {
	
	public Page<DeviceEntity> findAllByGatewayId(Long gatewayId, Pageable pageable);
	
	public Optional<DeviceEntity> findByIdAndGatewayId(Long id, Long gatewayId);
	
	public long countAllByGatewayId(Long gatewayId);
	
	public DeviceEntity findByUid(Long uid);
}
