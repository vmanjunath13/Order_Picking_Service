package com.egen.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "WareHouse")
public class WareHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long warehouseId;

    @Column(name = "warehouseAddress")
    private String warehouseAddress;

    @Column(name = "zone")
    private String zone;

    public WareHouse() {
    }

    public long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
