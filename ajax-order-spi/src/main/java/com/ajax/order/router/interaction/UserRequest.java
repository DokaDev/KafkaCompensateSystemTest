package com.ajax.order.router.interaction;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRequest {
    private Long userId;
    private Long totalPrice;
    private String shippingAddress;
}
