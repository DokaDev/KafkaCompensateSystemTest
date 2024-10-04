package com.ajax.shipping.model.dao;

import com.ajax.shipping.model.dto.ShippingDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShippingMapper {
    int insertShippingInfo(ShippingDto shippingDto);
}
