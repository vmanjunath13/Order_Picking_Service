package com.egen.service.kafka;

import com.egen.model.dto.OrderPickUpDto;
import com.egen.model.enums.OrderPickingMethod;
import com.egen.service.OrderPickUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    @Autowired
    OrderPickUpService pickUpService;

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.name}",
            groupId = "${kafka.topic.groupId}")
    public void consumerOrderPickUpDto(OrderPickUpDto pickUpDto,
                                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID)String partitionId,
                                       @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY)String key,
                                       @Header(KafkaHeaders.RECEIVED_TOPIC)String topicName,
                                       @Header(KafkaHeaders.OFFSET)String offsetValue) {
        log.info("Consumed Message: Order Id: {}, Customer Name: {} from Partition ID: {} with Key : {} , topic : {} , offset : {}",
                pickUpDto.getPickUpId(), pickUpDto.getEmployee().getEmpEmail(),
                partitionId, key, topicName, offsetValue);

        if(pickUpDto.getPickingMethod() == OrderPickingMethod.PICK_SINGLE)
            pickUpService.createSingleOrder(pickUpDto);
        else
            pickUpService.createBatchOrder(pickUpDto);
    }


}
