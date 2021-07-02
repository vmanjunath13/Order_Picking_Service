package com.egen.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto implements Serializable {

    private long orderId;

    @JsonProperty(value = "OrderPickedAt")
    private Timestamp OrderPickedAt;

    public List<OrderItemDto> orderItems;

    public OrderDto() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Timestamp getOrderPickedAt() {
        return OrderPickedAt;
    }

    public void setOrderPickedAt(Timestamp orderPickedAt) {
        OrderPickedAt = orderPickedAt;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }
}
