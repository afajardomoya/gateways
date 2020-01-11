package com.musala.task.gateways.controllers;

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

import com.musala.task.gateways.models.data.request.GatewayRequest;
import com.musala.task.gateways.models.data.response.GatewayResponse;
import com.musala.task.gateways.models.service.IGatewayService;

@RestController
@RequestMapping(path = "/api/v1/gateways")
public class GatewayController {

	@Autowired
	private IGatewayService gatewayService;

	@GetMapping
	public Page<GatewayResponse> getAllGateways(Pageable pageable) {
		return this.gatewayService.getAllGateways(pageable);
	}

	@GetMapping(path = "/{gatewayId}")
	public ResponseEntity<GatewayResponse> getGateway(@PathVariable Long gatewayId) {
		GatewayResponse response = this.gatewayService.getGateway(gatewayId);
		if (response == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<GatewayResponse> createGateway(@RequestBody Map<String, Object> requestBody) {
		GatewayRequest gatewayRequest = new GatewayRequest(requestBody);
		GatewayResponse gatewayResponse = this.gatewayService.createGateway(gatewayRequest);
		if (gatewayResponse == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(gatewayResponse, HttpStatus.OK);
	}

	@PutMapping(path = "/{gatewayId}")
	public ResponseEntity<GatewayResponse> updateGateway(@PathVariable Long gatewayId, @RequestBody Map<String, Object> requestBody) {
		GatewayRequest gatewayRequest = new GatewayRequest(requestBody);
		GatewayResponse gatewayResponse = this.gatewayService.updateGateway(gatewayId, gatewayRequest);
		if (gatewayResponse == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(gatewayResponse, HttpStatus.OK);
	}
	
	@PatchMapping(path = "/{gatewayId}")
	public ResponseEntity<GatewayResponse> patchGateway(@PathVariable Long gatewayId, @RequestBody Map<String, Object> requestBody) {
		GatewayRequest gatewayRequest = new GatewayRequest(requestBody);
		GatewayResponse gatewayResponse = this.gatewayService.updateGateway(gatewayId, gatewayRequest);
		if (gatewayResponse == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(gatewayResponse, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{gatewayId}")
	public ResponseEntity<GatewayResponse> deleteGateway(@PathVariable Long gatewayId) {
		GatewayResponse gatewayResponse = this.gatewayService.deleteGateway(gatewayId);
		if (gatewayResponse == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(gatewayResponse, HttpStatus.OK);
	}
}
