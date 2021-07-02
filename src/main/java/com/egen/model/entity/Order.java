package com.egen.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "Order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;

    @Column(name = "OrderPickedAt")
    private Timestamp OrderPickedAt;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    public List<OrderItem> orderItems;

    public Order() {
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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
