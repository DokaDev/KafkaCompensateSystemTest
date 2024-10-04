package com.ajax.shipping.model.service;

import com.ajax.shipping.common.event.KafkaEventBroadcaster;
import com.ajax.shipping.model.dao.ShippingMapper;
import com.ajax.shipping.model.dto.ShippingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShippingService {
    private final ShippingMapper shippingMapper;
    private final KafkaEventBroadcaster kafkaEventBroadcaster;

    /**
     * 배송 정보를 DB에 저장한다.
     * @param shippingDto 배송 정보
     * @return 저장된 배송 정보 수
     */
    @Transactional
    public int insertShippingInfo(ShippingDto shippingDto) {
        int result = shippingMapper.insertShippingInfo(shippingDto);

        if (result != 1) {
            System.out.println("Failed to insert shipping info");

            kafkaEventBroadcaster.sendShippingFailedEvent(shippingDto);

            throw new RuntimeException("Failed to insert shipping info");
        }

        System.out.println("Shipping info inserted: " + shippingDto);
        // todo. 배송 정보 저장 이벤트를 Kafka로 전송한다.
        kafkaEventBroadcaster.sendShippingCompletedEvent(shippingDto);

        return result;
    }
}
