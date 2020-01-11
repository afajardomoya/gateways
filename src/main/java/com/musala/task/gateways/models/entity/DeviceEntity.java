package com.musala.task.gateways.models.entity;

import com.musala.task.gateways.enums.DeviceStatus;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "device")
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private Long uid;

    @NotNull
    private String vendor;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DeviceStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    private GatewayEntity gateway;
    
    public DeviceEntity() {
    	
    }
    
    public DeviceEntity(Long uid, String vendor, DeviceStatus status) {
    	this.uid = uid;
    	this.vendor = vendor;
    	this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    public GatewayEntity getGateway() {
        return gateway;
    }

    public void setGateway(GatewayEntity gateway) {
        this.gateway = gateway;
    }
}
