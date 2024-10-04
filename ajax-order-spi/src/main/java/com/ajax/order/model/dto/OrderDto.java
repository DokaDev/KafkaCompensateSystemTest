package com.ajax.order.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDto {
    private Long orderId;
    private Long userId;
    private Long totalPrice;
    private LocalDateTime createdAt;
}
