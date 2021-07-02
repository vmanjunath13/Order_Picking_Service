package com.egen.mapper;

import com.egen.model.dto.OrderPickUpDto;
import com.egen.model.entity.OrderPickUp;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderPickUpMapper {

    ModelMapper modelMapper = new ModelMapper();

    public OrderPickUp mapToEntity(OrderPickUpDto pickUpDto) {
        return modelMapper.map(pickUpDto, OrderPickUp.class);
    }

    public OrderPickUpDto mapToDto(Optional<OrderPickUp> pickUp) {
        return modelMapper.map(pickUp, OrderPickUpDto.class);
    }
}
