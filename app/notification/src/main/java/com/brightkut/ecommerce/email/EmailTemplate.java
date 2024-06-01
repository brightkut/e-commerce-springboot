package com.brightkut.ecommerce.email;

import lombok.Getter;

public enum EmailTemplate  {
    PAYMENT_NOTIFICATION("payment-notification.html","Payment successfully processed"),
    ORDER_CONFIRMATION("order-confirmation.html","Order confirmation");

    @Getter
    private final String template;
    @Getter
    private final String subject;

    EmailTemplate(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
