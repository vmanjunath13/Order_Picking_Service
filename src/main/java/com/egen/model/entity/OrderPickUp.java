package com.egen.model.entity;

import com.egen.model.enums.OrderPickingMethod;
import com.egen.model.enums.PickUpStatus;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "OrderPickUp")
public class OrderPickUp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pickUpId;

    @Column(name = "pickUpCreated")
    private Timestamp pickUpCreated;

    @Column(name = "pickUpStatus")
    private PickUpStatus pickUpStatus;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "warehouseId")
    public WareHouse wareHouse;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "storeId")
    public Store store;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "employeeId")
    public Employee employee;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "empPerfId")
    public EmployeePerformance employeePerformance;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "OrderPickUp",
            joinColumns = @JoinColumn(name = "pickUpId"),
            inverseJoinColumns = @JoinColumn(name = "orderId")
    )
    public List<Order> orderList;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "cartToteId")
    public List<CartTote> cartToteList;

    @Column(name = "pickingMethod")
    @Enumerated(EnumType.STRING)
    private OrderPickingMethod pickingMethod;

    public OrderPickUp() {
    }

    public long getPickUpId() {
        return pickUpId;
    }

    public void setPickUpId(long pickUpId) {
        this.pickUpId = pickUpId;
    }

    public Timestamp getPickUpCreated() {
        return pickUpCreated;
    }

    public void setPickUpCreated(Timestamp pickUpCreated) {
        this.pickUpCreated = pickUpCreated;
    }

    public PickUpStatus getPickUpStatus() {
        return pickUpStatus;
    }

    public void setPickUpStatus(PickUpStatus pickUpStatus) {
        this.pickUpStatus = pickUpStatus;
    }

    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeePerformance getEmployeePerformance() {
        return employeePerformance;
    }

    public void setEmployeePerformance(EmployeePerformance employeePerformance) {
        this.employeePerformance = employeePerformance;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<CartTote> getCartToteList() {
        return cartToteList;
    }

    public void setCartToteList(List<CartTote> cartToteList) {
        this.cartToteList = cartToteList;
    }

    public OrderPickingMethod getPickingMethod() {
        return pickingMethod;
    }

    public void setPickingMethod(OrderPickingMethod pickingMethod) {
        this.pickingMethod = pickingMethod;
    }
}
