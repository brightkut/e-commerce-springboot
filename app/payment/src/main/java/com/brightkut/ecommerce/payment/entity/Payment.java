package com.brightkut.ecommerce.payment.entity;

import com.brightkut.ecommerce.payment.model.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private BigDecimal amount;
    @Column
    @Enumerated(EnumType.STRING)// add this to allow using enum
    private PaymentMethod paymentMethod;
    @Column
    private Integer orderId;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false, nullable = false)
    private LocalDateTime lastModifiedDate;
}
