package com.brightkut.ecommerce.customer.repository;

import com.brightkut.ecommerce.customer.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
