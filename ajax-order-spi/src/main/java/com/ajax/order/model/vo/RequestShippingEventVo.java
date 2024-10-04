package com.ajax.order.model.vo;

import lombok.*;

@AllArgsConstructor
@Getter
@ToString
public class RequestShippingEventVo {
    private Long orderId;
    private Long userId;
    private String shippingAddress;
}
