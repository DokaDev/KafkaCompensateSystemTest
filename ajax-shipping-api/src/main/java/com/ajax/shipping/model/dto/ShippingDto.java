package com.ajax.shipping.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ShippingDto {
    private Long shippingId;
    private Long orderId;
    private ShippingStatus status;
    private LocalDateTime createdAt;
    private String shippingAddress;


}

