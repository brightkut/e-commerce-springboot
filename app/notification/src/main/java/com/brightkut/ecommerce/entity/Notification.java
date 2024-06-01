package com.brightkut.ecommerce.entity;

import com.brightkut.ecommerce.model.NotificationType;
import com.brightkut.ecommerce.model.OrderConfirmation;
import com.brightkut.ecommerce.model.PaymentNotification;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Document
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentNotification paymentNotification;
}
