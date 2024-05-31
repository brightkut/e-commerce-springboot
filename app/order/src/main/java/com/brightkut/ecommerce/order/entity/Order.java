package com.brightkut.ecommerce.order.entity;

import com.brightkut.ecommerce.order.model.PaymentMethod;
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
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String reference;
    @Column
    private BigDecimal totalAmount;
    @Column
    @Enumerated(EnumType.STRING)// add this to allow using enum
    private PaymentMethod paymentMethod;
    @Column
    private String customerId;
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false, nullable = false)
    private LocalDateTime lastModifiedDate;
}
