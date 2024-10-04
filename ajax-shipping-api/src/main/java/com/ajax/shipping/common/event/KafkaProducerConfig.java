package com.ajax.shipping.common.event;

import org.springframework.context.annotation.*;
import org.springframework.kafka.core.*;

@Configuration
public class KafkaProducerConfig {
    private final ProducerFactory<String, String> producerFactory;

    public KafkaProducerConfig(ProducerFactory<String, String> producerFactory) {
        this.producerFactory = producerFactory;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory);
    }
}
