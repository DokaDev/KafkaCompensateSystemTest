package com.ajax.order.router;

import com.ajax.order.model.dto.OrderDto;
import com.ajax.order.model.service.OrderService;
import com.ajax.order.model.vo.RequestShippingEventVo;
import com.ajax.order.router.interaction.UserRequest;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ApiMethodRouter {
    private final OrderService orderService;

    @PostMapping("/api/save")
    public ResponseEntity<String> save(@RequestBody UserRequest body) {
        // todo. save to db
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(body.getUserId());
        orderDto.setTotalPrice(body.getTotalPrice());
        orderDto.setCreatedAt(LocalDateTime.now());
//        orderService.saveOrder()
        orderService.saveOrder(orderDto);

        // todo. send msg to kafka
        RequestShippingEventVo requestShippingEventVo = new RequestShippingEventVo(
                orderDto.getOrderId(),
                orderDto.getUserId(),
                body.getShippingAddress()
        );
        Gson gson = new Gson();
        String serializedOrder = gson.toJson(requestShippingEventVo);

        orderService.sendOrderCreatedEvent(requestShippingEventVo);

        return ResponseEntity.ok("save");
    }
}
