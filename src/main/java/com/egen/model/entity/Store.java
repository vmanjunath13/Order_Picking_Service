package com.egen.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long storeId;

    @Column(name = "storeAddress")
    private String storeAddress;

    public Store() {
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
}
