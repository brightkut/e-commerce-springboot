package com.brightkut.ecommerce.order.repository;

import com.brightkut.ecommerce.order.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
