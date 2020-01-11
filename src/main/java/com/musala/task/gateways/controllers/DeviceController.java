package com.musala.task.gateways.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musala.task.gateways.models.data.request.DeviceRequest;
import com.musala.task.gateways.models.data.response.DeviceResponse;
import com.musala.task.gateways.models.service.IGatewayService;

@RestController
@RequestMapping(path = "/api/v1/gateways/{gatewayId}/devices")
public class DeviceController {

	@Autowired
	private IGatewayService gatewayService;

	@GetMapping
	public Page<DeviceResponse> getAllGatewayDevices(@PathVariable Long gatewayId, Pageable pageable) {
		return this.gatewayService.getAllGatewayDevices(gatewayId, pageable);
	}

	@GetMapping(path = "/{deviceId}")
	public ResponseEntity<DeviceResponse> getGatewayDevice(@PathVariable Long gatewayId, @PathVariable Long deviceId) {
		DeviceResponse deviceResponse = this.gatewayService.getGatewayDevice(gatewayId, deviceId);
		if (deviceResponse == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> createGatewayDevice(@PathVariable Long gatewayId,
			@RequestBody Map<String, Object> requestBody) {
		DeviceRequest deviceRequest = new DeviceRequest(requestBody);
		try {
			DeviceResponse deviceResponse = this.gatewayService.createGatewayDevice(gatewayId, deviceRequest);
			if (deviceResponse == null)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

			return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
		} catch (RuntimeException exception) {
			Map<String, Object> error = new HashMap<>();
			error.put("error", exception.getMessage());
			error.put("status", HttpStatus.FORBIDDEN.value());
			return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
		}
	}

	@PutMapping(path = "/{deviceId}")
	public ResponseEntity<DeviceResponse> updateGatewayDevice(@PathVariable Long gatewayId, @PathVariable Long deviceId,
			@RequestBody Map<String, Object> requestBody) {
		DeviceRequest deviceRequest = new DeviceRequest(requestBody);
		DeviceResponse deviceResponse = this.gatewayService.updateGatewayDevice(gatewayId, deviceId, deviceRequest);
		if (deviceResponse == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
	}

	@PatchMapping(path = "/{deviceId}")
	public ResponseEntity<DeviceResponse> patchGatewayDevice(@PathVariable Long gatewayId, @PathVariable Long deviceId,
			@RequestBody Map<String, Object> requestBody) {
		DeviceRequest deviceRequest = new DeviceRequest(requestBody);
		DeviceResponse deviceResponse = this.gatewayService.updateGatewayDevice(gatewayId, deviceId, deviceRequest);
		if (deviceResponse == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{deviceId}")
	public ResponseEntity<DeviceResponse> deleteGatewayDevice(@PathVariable Long gatewayId,
			@PathVariable Long deviceId) {
		DeviceResponse deviceResponse = this.gatewayService.deleteGatewayDevice(gatewayId, deviceId);
		if (deviceResponse == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
	}
}
