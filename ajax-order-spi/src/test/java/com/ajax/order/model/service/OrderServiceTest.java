package com.ajax.order.model.service;

import com.ajax.order.model.dto.OrderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OrderServiceTest {

    private final OrderService orderService;

    @Autowired
    public OrderServiceTest(OrderService orderService) {
        this.orderService = orderService;
    }

    @Test
    void selectOrderObjById() {
        // given
        Long orderId = 1L;

        // when
        OrderDto orderDto = orderService.selectOrderById(orderId);

        // then
        assertNotNull(orderDto);
        System.out.println(orderDto);
    }
}