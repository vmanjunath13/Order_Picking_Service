package com.egen.test;

import com.egen.mapper.EmployeeMapper;
import com.egen.mapper.OrderPickUpMapper;
import com.egen.model.dto.EmployeeDto;
import com.egen.model.dto.OrderPickUpDto;
import com.egen.model.entity.*;
import com.egen.model.enums.OrderPickingMethod;
import com.egen.model.enums.PickUpStatus;
import com.egen.repository.OrderPickUpRepository;
import com.egen.service.EmployeeService;
import com.egen.service.OrderPickUpService;
import com.egen.service.implementation.OrderPickUpServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class OrderPickingServiceTests {

    @MockBean
    private OrderPickUpRepository pickUpRepository;

    @Autowired
    OrderPickUpService pickUpService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    OrderPickUpMapper pickUpMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Bean
    public OrderPickUpService getPickupService(){
        return new OrderPickUpServiceImpl();
    }

    OrderPickUp singlePickup = new OrderPickUp();
    OrderPickUp batchPickup = new OrderPickUp();
    OrderPickUpDto singlePickupDto = new OrderPickUpDto();
    OrderPickUpDto batchPickupDto = new OrderPickUpDto();
    EmployeeDto employeeDto = new EmployeeDto();

    boolean singlePicking = true;
    @BeforeEach
    public void setUp() {
        //SINGLE PICK
        singlePickup.setPickUpId(2L);
        singlePickup.setPickUpStatus(PickUpStatus.PICKED_UP);
        singlePickup.setPickingMethod(OrderPickingMethod.PICK_SINGLE);
        singlePickup.setStoreId(1L);
        singlePickup.setCartToteId(3L);
        Date date = new Date();
        singlePickup.setPickUpCreated(new Timestamp(date.getTime()));

        WareHouse wareHouse = new WareHouse();
        wareHouse.setWarehouseId(1L);
        wareHouse.setZone("North");
        wareHouse.setWarehouseAddress("Chicago, IL, 60616");
        singlePickup.setWareHouse(wareHouse);

        Employee employee = new Employee();
        employee.setEmpId(1L);
        employee.setEmpName("John Michael");
        employee.setEmpEmail("john.michael@gmail.com");
        singlePickup.setEmployee(employee);

        List<Order> orderList = new ArrayList<>();
        Order orders = new Order();
        orders.setOrderId(2L);
        orders.setOrderItems(null);
        orderList.add(orders);
        singlePickup.setOrderList(orderList);

        singlePickup.setToteList(null);

        EmployeePerformance employeePerformance = new EmployeePerformance();
        employeePerformance.setEmpPerfId(1L);
        singlePickup.setEmployeePerformance(employeePerformance);

        singlePickupDto = pickUpMapper.mapToDto(Optional.ofNullable(singlePickup));


        //BATCH PICK
        batchPickup.setPickUpId(3L);
        batchPickup.setPickUpStatus(PickUpStatus.PICKED_UP);
        batchPickup.setPickingMethod(OrderPickingMethod.PICK_SINGLE);
        batchPickup.setStoreId(2L);
        batchPickup.setCartToteId(4L);
        batchPickup.setPickUpCreated(new Timestamp(date.getTime()));

        batchPickup.setWareHouse(wareHouse);
        batchPickup.setEmployee(employee);

        List<Order> orderList1 = new ArrayList<>();
        Order orders1 = new Order();
        orders1.setOrderId(3L);
        orders1.setOrderItems(null);
        orderList1.add(orders1);

        Order orders2 = new Order();
        orders2.setOrderId(4L);
        orders1.setOrderItems(null);
        orderList1.add(orders1);
        batchPickup.setOrderList(orderList1);

        batchPickup.setToteList(null);

        EmployeePerformance employeePerformance1 = new EmployeePerformance();
        employeePerformance1.setEmpPerfId(4L);
        batchPickup.setEmployeePerformance(employeePerformance1);

        batchPickupDto = pickUpMapper.mapToDto(Optional.ofNullable(batchPickup));


        if(singlePicking) {
            Mockito.when(pickUpRepository.findAll())
                    .thenReturn(Collections.singletonList(singlePickup));
            Mockito.when(pickUpRepository.save(singlePickup))
                    .thenReturn(singlePickup);
            Mockito.when(pickUpRepository.findById(String.valueOf(singlePickup.getPickUpId())))
                    .thenReturn(Optional.of(singlePickup));
        }
        else {
            Mockito.when(pickUpRepository.findAll())
                    .thenReturn(Collections.singletonList(batchPickup));
            Mockito.when(pickUpRepository.save(batchPickup))
                    .thenReturn(batchPickup);
            Mockito.when(pickUpRepository.findById(String.valueOf(singlePickup.getPickUpId())))
                    .thenReturn(Optional.of(batchPickup));
        }

        // Employee set up
        employeeDto = employeeMapper.mapToDto(Optional.of(employee));

    }

    @AfterEach
    public void cleanUp(){
        System.out.println("Cleaning completed");
    }

    @Test
    void createSinglePicking() {
        OrderPickUpDto pickupDto = pickUpService.createSingleOrder(singlePickupDto);
        Assertions.assertEquals(pickupDto, singlePickupDto);
    }

    @Test
    void cancelSinglePicking() {
        OrderPickUpDto pickupDto = pickUpService.cancelSingleOrder(singlePickup.getPickUpId());
        Assertions.assertNotEquals(pickupDto.getPickUpStatus(), singlePickupDto.getPickUpStatus());
    }

    @Test
    void createBatchPicking() {
        OrderPickUpDto pickupDto = pickUpService.createBatchOrder(batchPickupDto);
        Assertions.assertEquals(pickupDto, batchPickupDto);
    }

    @Test
    void cancelBatchPicking() {
        OrderPickUpDto pickupDto = pickUpService.cancelBatchOrder(batchPickup.getPickUpId());
        Assertions.assertNotEquals(pickupDto.getPickUpStatus(), batchPickupDto.getPickUpStatus());
    }

    @Test
    void findAllOrders() {
        List<OrderPickUpDto> pickUpDtoList = pickUpService.findAllOrders();
        Assertions.assertEquals(2, pickUpDtoList.size());
        Assertions.assertEquals(3, pickUpDtoList.size());
    }

    @Test
    void findSingleOrderById() {
        OrderPickUpDto pickUpDto = pickUpService.findOrderById(singlePickup.getPickUpId());
        Assertions.assertEquals(pickUpDto.getPickUpId(), singlePickupDto.getPickUpId());
    }

    @Test
    void findBatchOrderById() {
        OrderPickUpDto pickUpDto = pickUpService.findOrderById(batchPickup.getPickUpId());
        Assertions.assertEquals(pickUpDto.getPickUpId(), batchPickupDto.getPickUpId());
    }

    @Test
    void createEmployee() {
        Assertions.assertEquals(true, employeeService.createEmployee(employeeDto));
    }

    @Test
    void findEmployeeById() {
        Assertions.assertEquals(true, employeeService.findEmployeeById(1L));
    }

}
