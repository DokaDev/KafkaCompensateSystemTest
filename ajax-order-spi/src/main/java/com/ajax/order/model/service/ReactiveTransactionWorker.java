package com.ajax.order.model.service;

import com.ajax.order.model.dto.ShippingTransactionInteractionEventDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReactiveTransactionWorker {
    private final OrderService shippingService;

    // 배송 처리 성공 이벤트 수신
    @KafkaListener(topics = "shipping_success_notify")
    public void receiveShippingSuccessEvent(ConsumerRecord<String, String> record) {
        System.out.println("Received shipping success event: " + record.value());
        // pass
    }

    // 배송 처리 실패 이벤트 수신
    @KafkaListener(topics = "shipping_failed_order_cancel")
    public void receiveShippingFailedEvent(ConsumerRecord<String, String> record) {
        System.out.println("Received shipping failed event: " + record.value());
        // pass
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ShippingTransactionInteractionEventDto shippingTransactionInteractionEventDto = objectMapper.readValue(record.value(), ShippingTransactionInteractionEventDto.class);
            System.out.println("Parsed shipping failed event: " + shippingTransactionInteractionEventDto);

            // 배송이 실패하여 주문을 취소한다.
            shippingService.cancelOrder(shippingTransactionInteractionEventDto.getOrderId());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
