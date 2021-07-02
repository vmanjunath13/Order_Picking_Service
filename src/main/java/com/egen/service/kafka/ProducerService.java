package com.egen.service.kafka;

import com.egen.model.dto.OrderPickUpDto;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class ProducerService {

    private final KafkaTemplate<String, OrderPickUpDto> dtoKafkaTemplate;

    @Value("${kafka.topic.name}")
    private String JSON_TOPIC;

    public ProducerService(KafkaTemplate<String, OrderPickUpDto> dtoKafkaTemplate) {
        this.dtoKafkaTemplate = dtoKafkaTemplate;
    }

    public void producerSendPickUpCompleteNotification(OrderPickUpDto pickUpDto) {
        log.info(String.format("$$$$ => Producing Order message: %s", pickUpDto));

        dtoKafkaTemplate.executeInTransaction(t -> {
            ListenableFuture<SendResult<String, OrderPickUpDto>> future = t.send(JSON_TOPIC, String.valueOf(pickUpDto.getPickUpId()), pickUpDto);
            future.addCallback(new ListenableFutureCallback<SendResult<String, OrderPickUpDto>>() {
                @Override
                public void onFailure(@NotNull Throwable throwable) {
                    log.info("Unable to produce message = [ {} ] due to : {}", pickUpDto, throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, OrderPickUpDto> pickUpDtoSendResult) {
                    log.info("Sent Order Created Message = [ {} ] with offset= [ {} ]", pickUpDto, pickUpDtoSendResult.getRecordMetadata().offset());
                }
            });
            return true;
        });
    }
}
