package com.ajax.order.model.service;

import com.ajax.order.model.dao.OrderMapper;
import com.ajax.order.model.dto.OrderDto;
import com.ajax.order.model.vo.RequestShippingEventVo;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public void saveOrder(OrderDto orderDto) {
        int result = orderMapper.saveOrder(orderDto);

        if (result != 1) {
            throw new RuntimeException("Failed to save order");
        }

        System.out.println("Order saved: " + orderDto);
    }

    /**
     * 주문 생성 이벤트를 Kafka로 전송한다.
     * topic: order_created_delivery_request
     * @param event 주문 생성 이벤트
     */
    public void sendOrderCreatedEvent(RequestShippingEventVo event) {
//        String serializedOrder = new Gson().toJson(orderDto);
        // refactor: 추후 Adapter를 통해 빌드한 Gson 객체를 사용하도록 모듈화 구성 변경
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
//                .create();
        Gson gson = new Gson();
        String serializedOrder = gson.toJson(event);

        CompletableFuture<SendResult<String, String>> send = kafkaTemplate.send("order_created_delivery_request", serializedOrder);

        send.whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println("Failed to send order created event: " + ex.getMessage());
            } else {
                System.out.println("Order created event sent: " + result);
            }
        });
    }

    public OrderDto selectOrderById(Long orderId) {
        return orderMapper.getOrderById(orderId);
    }
}
