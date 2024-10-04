package com.ajax.shipping.model.service;

import com.ajax.shipping.model.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReactiveTransactionWorker {
    private final ShippingService shippingService;

    @KafkaListener(topics = "order_created_delivery_request")
    public void receiveOrderCreatedEvent(ConsumerRecord<String, String> record) {
        System.out.println("Received order created event: " + record.value());
        // Received order created event: {"orderId":3,"userId":3,"shippingAddress":"Incheon Si..."}

        // json 문자열을 객체로 변환하기 위한 Jackson ObjectMapper 라이브러리
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            OrderCreatedEventDto eventDto = objectMapper.readValue(record.value(), OrderCreatedEventDto.class);
            System.out.println("Parsed order created event: " + eventDto);

            ShippingDto shippingDto = new ShippingDto();
            shippingDto.setOrderId(eventDto.getOrderId());
            shippingDto.setShippingAddress(eventDto.getShippingAddress());
            shippingDto.setStatus(ShippingStatus.PENDING);
            shippingDto.setCreatedAt(LocalDateTime.now());

            shippingService.insertShippingInfo(shippingDto);
        } catch (JsonProcessingException e) {
            System.out.println("Failed to parse order created event: " + record.value());
            throw new RuntimeException(e);
        }
    }
}