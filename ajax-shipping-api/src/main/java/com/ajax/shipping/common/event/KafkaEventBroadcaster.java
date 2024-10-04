package com.ajax.shipping.common.event;

import com.ajax.shipping.model.dto.ShippingDto;
import com.ajax.shipping.model.vo.ShippingTransactionInteractionEventVo;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaEventBroadcaster {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendShippingFailedEvent(ShippingDto shippingDto) {
        ShippingTransactionInteractionEventVo eventVo = new ShippingTransactionInteractionEventVo(shippingDto.getOrderId());

        String serializedEvent = new Gson().toJson(eventVo);
        kafkaTemplate.send("shipping_failed_order_cancel", serializedEvent);
    }

    public void sendShippingCompletedEvent(ShippingDto shippingDto) {
        ShippingTransactionInteractionEventVo eventVo = new ShippingTransactionInteractionEventVo(shippingDto.getOrderId());

        String serializedEvent = new Gson().toJson(eventVo);
        kafkaTemplate.send("shipping_completed_notify", serializedEvent);
    }
}
