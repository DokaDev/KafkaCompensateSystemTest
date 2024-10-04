package com.ajax.order.model.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReactiveTransactionWorker {
    private final OrderService shippingService;

    @KafkaListener(topics = "shipping_success_notify")
    public void receiveShippingSuccessEvent(ConsumerRecord<String, String> record) {
        System.out.println("Received shipping success event: " + record.value());
        // pass
    }
}
