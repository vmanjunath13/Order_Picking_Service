package com.egen.service.implementation;

import com.egen.exception.OrderPickUpException;
import com.egen.mapper.OrderPickUpMapper;
import com.egen.model.dto.OrderPickUpDto;
import com.egen.model.entity.OrderPickUp;
import com.egen.model.enums.PickUpStatus;
import com.egen.repository.OrderPickUpRepository;
import com.egen.service.OrderPickUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class OrderPickUpServiceImpl implements OrderPickUpService {

    @Autowired
    OrderPickUpRepository pickUpRepository;

    @Autowired
    OrderPickUpMapper pickUpMapper;

    @Override
    public List<OrderPickUpDto> findAllOrders() {
        List<OrderPickUpDto> list = pickUpRepository.findAll().stream()
                .map(pickUp -> pickUpMapper.mapToDto(Optional.ofNullable(pickUp)))
                .collect(Collectors.toList());
        return Optional.of(list)
                .orElseThrow(() -> new OrderPickUpException("No orders found to pickup!"));
    }

    @Override
    public OrderPickUpDto findOrderById(long OrderId) {
         return pickUpMapper.mapToDto(Optional.ofNullable(pickUpRepository.findById(String.valueOf(OrderId))
                .orElseThrow(() -> new OrderPickUpException("No order found for the given Order Id"))));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public OrderPickUpDto createSingleOrder(OrderPickUpDto orderDto) {
        if (orderDto.getOrderList().size() > 1)
            throw new OrderPickUpException("Single Picking requires only one single order, please try batch picking!");
        OrderPickUp orderPickUp = pickUpMapper.mapToEntity(orderDto);
        return pickUpMapper.mapToDto(Optional.of(pickUpRepository.save(orderPickUp)));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public OrderPickUpDto createBatchOrder(OrderPickUpDto orderDto) {
        if (orderDto.getOrderList().size() == 1)
            throw new OrderPickUpException("Batch Picking requires more than one single order, please try single picking!");
        OrderPickUp orderPickUp = pickUpMapper.mapToEntity(orderDto);
        return pickUpMapper.mapToDto(Optional.of(pickUpRepository.save(orderPickUp)));
    }

    @Override
    public OrderPickUpDto cancelSingleOrder(long id) {
        Optional<OrderPickUp> pickUp = pickUpRepository.findById(String.valueOf(id));
        if(!pickUp.isPresent())
            throw new OrderPickUpException("Cancel to this single order pickup with id " + id + " is not found!");
        pickUp.get().setPickUpStatus(PickUpStatus.PICKUP_CANCELLED);
        return pickUpMapper.mapToDto(Optional.of(pickUpRepository.save(pickUp.get())));
    }

    @Override
    public OrderPickUpDto cancelBatchOrder(long id) {
        Optional<OrderPickUp> pickUp = pickUpRepository.findById(String.valueOf(id));
        if(!pickUp.isPresent())
            throw new OrderPickUpException("Cancel to this batch order pickup with id " + id + " is not found!");
        pickUp.get().setPickUpStatus(PickUpStatus.PICKUP_CANCELLED);
        return pickUpMapper.mapToDto(Optional.of(pickUpRepository.save(pickUp.get())));
    }

    @Override
    public String getNumberOfPicksEachDay(long empId, Timestamp timestamp) {
        Date date = new Date(timestamp.getTime() + (1000 * 3600 * 24));
        Timestamp endTime = new Timestamp(date.getTime());
        Optional<List<OrderPickUp>> totalOrdersPickup = Optional.ofNullable(Optional.ofNullable(pickUpRepository.findAllByEmployee_EmpIdAndPickUpOrderEndTime(empId, timestamp, endTime))
                .orElseThrow(() -> new OrderPickUpException("Orders pickedup between these time range not found")));
        return "Total Number of orders pickup during the given time frame from the employee " + empId + " is: " + totalOrdersPickup.get().size();
    }

}
