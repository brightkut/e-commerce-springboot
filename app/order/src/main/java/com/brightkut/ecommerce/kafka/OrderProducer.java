package com.brightkut.ecommerce.kafka;

import com.brightkut.ecommerce.kafka.model.OrderConfirmation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.messaging.Message;

@Service
@Slf4j
public class OrderProducer {

    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderConfirmation> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderConfirmation(OrderConfirmation orderConfirmation){
        log.info("Sending order confirmation: {}", orderConfirmation);

        Message<OrderConfirmation> message = MessageBuilder
                .withPayload(orderConfirmation)
                .setHeader(KafkaHeaders.TOPIC,"order-topic")
                .build();

        kafkaTemplate.send(message);
    }
}
