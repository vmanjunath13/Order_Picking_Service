package com.egen.service;

import com.egen.model.dto.OrderPickUpDto;

import java.sql.Timestamp;
import java.util.List;

public interface OrderPickUpService {

    List<OrderPickUpDto> findAllOrders();
    OrderPickUpDto findOrderById(long OrderId);
    OrderPickUpDto createSingleOrder(OrderPickUpDto orderDto);
    OrderPickUpDto createBatchOrder(OrderPickUpDto orderDto);

    OrderPickUpDto cancelSingleOrder(long id);
    OrderPickUpDto cancelBatchOrder(long id);

//    String getNumberOfPicksEachDay(long empId, Timestamp startTime, Timestamp endTime);

}
