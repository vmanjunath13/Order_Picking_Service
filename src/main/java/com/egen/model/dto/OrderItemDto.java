package com.egen.model.dto;

import com.egen.model.enums.ItemSubstitutionAllowed;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties
public class OrderItemDto implements Serializable {

    private long orderItemId;

    @JsonProperty(value = "orderItemName")
    private String orderItemName;

    @JsonProperty(value = "orderItemQtyPicked")
    private long orderItemQtyPicked;

    @JsonProperty(value = "orderItemQtyReq")
    private long orderItemQtyReq;

    @JsonProperty(value = "orderItemHeight")
    private double orderItemHeight;

    @JsonProperty(value = "orderItemWeight")
    private double orderItemWeight;

    @JsonProperty(value = "orderItemLength")
    private double orderItemLength;

    @JsonProperty(value = "substitutionAllowed")
    private ItemSubstitutionAllowed substitutionAllowed;

    @JsonProperty(value = "ItemSubstitution")
    private String ItemSubstitution;

    public OrderItemDto() {
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
