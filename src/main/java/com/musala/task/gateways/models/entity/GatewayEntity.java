package com.musala.task.gateways.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "gateway")
public class GatewayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "serial_number", unique = true)
    private String serialNumber;

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$", message = "Ip address should complaint with IPv4 format (eg. 127.0.0.1)")
    @Column(name = "ip_address")
    private String ipAddress;
    
    public GatewayEntity() {
    	
    }
    
    public GatewayEntity(String serialNumber, String name, String ipAddress) {
    	this.serialNumber = serialNumber;
    	this.name = name;
    	this.ipAddress = ipAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
