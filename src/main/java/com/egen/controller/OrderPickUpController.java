package com.egen.controller;

import com.egen.exception.EmployeeServiceException;
import com.egen.model.dto.OrderPickUpDto;
import com.egen.service.OrderPickUpService;
import com.egen.service.kafka.ProducerService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order-picking")
@Api("Order Picking Service related endpoints")
@Slf4j
public class OrderPickUpController {

    private final OrderPickUpService pickUpService;

    private final ProducerService producerService;

    public OrderPickUpController(OrderPickUpService pickUpService, ProducerService producerService) {
        this.pickUpService = pickUpService;
        this.producerService = producerService;
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Fetches all the orders picked up",
            notes = "Returns all the orders")
    @ApiResponses(value={
            @ApiResponse(code=302,message = "FOUND"),
            @ApiResponse(code=500,message = "Interval Server Error"),
            @ApiResponse(code=200,message = "OK")
    })
    public ResponseEntity<List<OrderPickUpDto>> getAllOrders() {
        return ResponseEntity.ok(pickUpService.findAllOrders());
    }

    @GetMapping(value="/{id}", produces = "application/json")
    @ApiOperation(value = "Fetches a specific order pickup for given id ",
            notes = "Returns a specific order")
    @ApiResponses(value={
            @ApiResponse(code=302,message = "FOUND"),
            @ApiResponse(code=500,message = "Interval Server Error"),
            @ApiResponse(code=200,message = "OK")
    })
    public ResponseEntity<OrderPickUpDto> getOrderById(@ApiParam(value = "Order id of the order",required = true)
                                         @PathVariable("id") Long id) {
        return ResponseEntity.ok(pickUpService.findOrderById(id));
    }

    @ApiOperation(value  = "Create single picking")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    @PostMapping(value = "/single/create")
    public ResponseEntity<OrderPickUpDto> createSingleOrderPicking(@RequestBody OrderPickUpDto pickUpDto){
        try{
            return ResponseEntity.ok(pickUpService.createSingleOrder(pickUpDto));
        } catch(Exception e){
            throw new EmployeeServiceException("There was a conflict while assigning the employee, check whether the employee is already assigned or been assigned");
        }
    }

    @ApiOperation(value  = "Cancel single picking")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    @PostMapping(value = "/single/cancel/{id}")
    public ResponseEntity<OrderPickUpDto> cancelSingleOrderPicking(@PathVariable("id") String id){
        return ResponseEntity.ok(pickUpService.cancelSingleOrder(Long.parseLong(id)));
    }


    @ApiOperation(value  = "Create batch picking")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    @PostMapping(value = "/batch/create")
    public ResponseEntity<OrderPickUpDto> createBatchOrderPicking(@RequestBody OrderPickUpDto pickUpDto){
        try{
            return ResponseEntity.ok(pickUpService.createBatchOrder(pickUpDto));
        } catch(Exception e){
            throw new EmployeeServiceException("There was a conflict while assigning the employee, check whether the employee is already assigned or been assigned");
        }
    }

    @ApiOperation(value  = "Cancel batch picking")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK" ),
            @ApiResponse(code = 400, message = "BAD REQUEST ERROR")
    })
    @PostMapping(value = "/batch/cancel/{id}")
    public ResponseEntity<OrderPickUpDto> cancelBatchOrderPicking(@PathVariable("id") String id){
        return ResponseEntity.ok(pickUpService.cancelBatchOrder(Long.parseLong(id)));
    }

    @PostMapping(value = "/publish/pickup")
    public String publishPickupDone(@RequestBody OrderPickUpDto pickUpDto){
        log.info("Order Message received in KafkaController: {}", pickUpDto);
        producerService.producerSendPickUpCompleteNotification(pickUpDto);
        return "Order Picking Completed";
    }


}
