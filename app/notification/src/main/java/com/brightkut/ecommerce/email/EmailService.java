package com.brightkut.ecommerce.email;

import com.brightkut.ecommerce.model.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.brightkut.ecommerce.email.EmailTemplate.ORDER_CONFIRMATION;
import static com.brightkut.ecommerce.email.EmailTemplate.PAYMENT_NOTIFICATION;

@Service
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public EmailService(SpringTemplateEngine templateEngine) {
        this.mailSender = new JavaMailSenderImpl();
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendPaymentSuccessEmail(
         String destinationEmail,
         String customerName,
         BigDecimal amount,
         String orderReference
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());

        mimeMessageHelper.setFrom("contact@brightkut.com");
        mimeMessageHelper.setTo(destinationEmail);
        mimeMessageHelper.setSubject(PAYMENT_NOTIFICATION.getSubject());

        final String templateName = PAYMENT_NOTIFICATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(variables);

        try {
            String html = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(html, true);

            mailSender.send(mimeMessage);

            log.info("Email sent successfully sent to {} with template {}", destinationEmail, templateName);
        }catch (MessagingException ex){
            log.warn("Email sent failed to {}", destinationEmail);
        }
    }

    @Async
    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());

        mimeMessageHelper.setFrom("contact@brightkut.com");
        mimeMessageHelper.setTo(destinationEmail);
        mimeMessageHelper.setSubject(ORDER_CONFIRMATION.getSubject());

        final String templateName = ORDER_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", amount);
        variables.put("orderReference", orderReference);
        variables.put("products", products);

        Context context = new Context();
        context.setVariables(variables);

        try {
            String html = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(html, true);

            mailSender.send(mimeMessage);

            log.info("Email sent successfully sent to {} with template {}", destinationEmail, templateName);
        }catch (MessagingException ex){
            log.warn("Email sent failed to {}", destinationEmail);
        }
    }
}
