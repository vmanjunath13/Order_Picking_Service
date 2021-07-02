package com.egen.model.dto;

import com.egen.model.enums.OrderPickingMethod;
import com.egen.model.enums.PickUpStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties
public class OrderPickUpDto implements Serializable {

    private long pickUpId;

    @JsonProperty(value = "pickUpCreated")
    private Timestamp pickUpCreated;

    @JsonProperty(value = "pickUpStatus")
    private PickUpStatus pickUpStatus;

    public WareHouseDto wareHouse;

    public StoreDto store;

    public EmployeeDto employee;

    public EmployeePerformanceDto employeePerformance;

    public List<OrderDto> orderList;

    public List<CartToteDto> cartToteList;

    @JsonProperty(value = "pickingMethod")
    private OrderPickingMethod pickingMethod;

    public OrderPickUpDto() {
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

    public WareHouseDto getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(WareHouseDto wareHouse) {
        this.wareHouse = wareHouse;
    }

    public StoreDto getStore() {
        return store;
    }

    public void setStore(StoreDto store) {
        this.store = store;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public EmployeePerformanceDto getEmployeePerformance() {
        return employeePerformance;
    }

    public void setEmployeePerformance(EmployeePerformanceDto employeePerformance) {
        this.employeePerformance = employeePerformance;
    }

    public List<OrderDto> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDto> orderList) {
        this.orderList = orderList;
    }

    public List<CartToteDto> getCartToteList() {
        return cartToteList;
    }

    public void setCartToteList(List<CartToteDto> cartToteList) {
        this.cartToteList = cartToteList;
    }

    public OrderPickingMethod getPickingMethod() {
        return pickingMethod;
    }

    public void setPickingMethod(OrderPickingMethod pickingMethod) {
        this.pickingMethod = pickingMethod;
    }
}
