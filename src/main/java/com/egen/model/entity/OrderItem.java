package com.egen.model.entity;

import com.egen.model.enums.ItemSubstitutionAllowed;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "OrderItem")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderItemId;

    @Column(name = "orderItemName")
    private String orderItemName;

    @Column(name = "orderItemQtyPicked")
    private long orderItemQtyPicked;

    @Column(name = "orderItemQtyReq")
    private long orderItemQtyReq;

    @Column(name = "orderItemHeight")
    private double orderItemHeight;

    @Column(name = "orderItemWeight")
    private double orderItemWeight;

    @Column(name = "orderItemLength")
    private double orderItemLength;

    @Column(name = "substitutionAllowed")
    @Enumerated(EnumType.STRING)
    private ItemSubstitutionAllowed substitutionAllowed;

    @Column(name = "ItemSubstitution")
    private String ItemSubstitution;

    public OrderItem() {
    }

    public long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getOrderItemName() {
        return orderItemName;
    }

    public void setOrderItemName(String orderItemName) {
        this.orderItemName = orderItemName;
    }

    public long getOrderItemQtyPicked() {
        return orderItemQtyPicked;
    }

    public void setOrderItemQtyPicked(long orderItemQtyPicked) {
        this.orderItemQtyPicked = orderItemQtyPicked;
    }

    public long getOrderItemQtyReq() {
        return orderItemQtyReq;
    }

    public void setOrderItemQtyReq(long orderItemQtyReq) {
        this.orderItemQtyReq = orderItemQtyReq;
    }

    public double getOrderItemHeight() {
        return orderItemHeight;
    }

    public void setOrderItemHeight(double orderItemHeight) {
        this.orderItemHeight = orderItemHeight;
    }

    public double getOrderItemWeight() {
        return orderItemWeight;
    }

    public void setOrderItemWeight(double orderItemWeight) {
        this.orderItemWeight = orderItemWeight;
    }

    public double getOrderItemLength() {
        return orderItemLength;
    }

    public void setOrderItemLength(double orderItemLength) {
        this.orderItemLength = orderItemLength;
    }

    public ItemSubstitutionAllowed getSubstitutionAllowed() {
        return substitutionAllowed;
    }

    public void setSubstitutionAllowed(ItemSubstitutionAllowed substitutionAllowed) {
        this.substitutionAllowed = substitutionAllowed;
    }

    public String getItemSubstitution() {
        return ItemSubstitution;
    }

    public void setItemSubstitution(String itemSubstitution) {
        ItemSubstitution = itemSubstitution;
    }
}
