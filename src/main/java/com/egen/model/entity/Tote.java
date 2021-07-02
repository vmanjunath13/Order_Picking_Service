package com.egen.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Tote")
public class Tote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long toteId;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "orderItemId")
    public List<OrderItem> orderItems;

    public Tote() {
    }

    public long getToteId() {
        return toteId;
    }

    public void setToteId(long toteId) {
        this.toteId = toteId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
