package com.brightkut.ecommerce.kafka;

import com.brightkut.ecommerce.email.EmailService;
import com.brightkut.ecommerce.entity.Notification;
import com.brightkut.ecommerce.model.OrderConfirmation;
import com.brightkut.ecommerce.model.PaymentNotification;
import com.brightkut.ecommerce.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.brightkut.ecommerce.model.NotificationType.ORDER_CONFIRMATION;
import static com.brightkut.ecommerce.model.NotificationType.PAYMENT_CONFIRMATION;

@Service
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    public NotificationConsumer(NotificationRepository notificationRepository, EmailService emailService) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
    }

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentNotification paymentNotification) throws MessagingException {
        log.info("Consumed payment notification: {}", paymentNotification);

        notificationRepository.save(
                new Notification()
                        .setNotificationDate(LocalDateTime.now())
                        .setType(PAYMENT_CONFIRMATION)
                        .setPaymentNotification(paymentNotification)
        );

        // send email
        var customerName = paymentNotification.customerFirstname() + " " + paymentNotification.customerLastname();

        emailService.sendPaymentSuccessEmail(
                paymentNotification.customerEmail(),
                customerName,
                paymentNotification.amount(),
                paymentNotification.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info("Consumed order notification: {}", orderConfirmation);

        notificationRepository.save(
                new Notification()
                        .setNotificationDate(LocalDateTime.now())
                        .setType(ORDER_CONFIRMATION)
                        .setOrderConfirmation(orderConfirmation)
        );

        // send email
        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();

        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }
}
