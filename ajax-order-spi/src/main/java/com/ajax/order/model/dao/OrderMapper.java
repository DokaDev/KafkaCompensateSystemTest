package com.ajax.order.model.dao;

import com.ajax.order.model.dto.OrderDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    int saveOrder(OrderDto orderDto);
    OrderDto getOrderById(Long orderId);
}
