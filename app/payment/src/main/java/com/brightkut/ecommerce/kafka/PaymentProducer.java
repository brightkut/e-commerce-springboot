package com.brightkut.ecommerce.kafka;

import com.brightkut.ecommerce.kafka.model.PaymentNotification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentProducer {
    private final KafkaTemplate<String, PaymentNotification> kafkaTemplate;

    public PaymentProducer(KafkaTemplate<String, PaymentNotification> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentNotification(PaymentNotification request) {
        log.info("Sending payment notification: {}", request);

        Message<PaymentNotification>message = MessageBuilder
                .withPayload(request)
                .setHeader(KafkaHeaders.TOPIC, "payment-topic")
                .build();

        kafkaTemplate.send(message);
    }
}
