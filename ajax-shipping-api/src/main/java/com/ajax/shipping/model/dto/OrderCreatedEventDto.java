package com.ajax.shipping.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderCreatedEventDto {
    private Long orderId;
    private Long userId;
    private String shippingAddress;
}
