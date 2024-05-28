package com.brightkut.ecommerce.customer.service;

import com.brightkut.ecommerce.customer.factory.CustomerFactory;
import com.brightkut.ecommerce.customer.model.dto.CustomerResponse;
import com.brightkut.ecommerce.customer.model.entity.Customer;
import com.brightkut.ecommerce.exception.CustomerNotFoundException;
import com.brightkut.ecommerce.customer.model.dto.CustomerRequest;
import com.brightkut.ecommerce.customer.repository.CustomerRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerFactory customerFactory;

    public CustomerService(CustomerRepository customerRepository, CustomerFactory customerFactory) {
        this.customerRepository = customerRepository;
        this.customerFactory = customerFactory;
    }

    public String createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(customerFactory.buildCustomer(request));

        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = customerRepository.findById(request.id())
                .orElseThrow(()->new CustomerNotFoundException(
                        format("Cannot update customer:: No customer found with id %s", request.id())
                ));

        mergeCustomer(request,customer);

        customerRepository.save(customer);
    }

    private void mergeCustomer(CustomerRequest request, Customer customer) {
        if(StringUtils.isNotBlank(request.firstname())){
          customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerFactory::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean isCustomerExist(String customerId) {
        return customerRepository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse findCustomerById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerFactory::fromCustomer)
                .orElseThrow(()->new CustomerNotFoundException(
                        format("Cannot get customer by id:: No customer found with id %s", customerId)
                ));
    }

    public void removeCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
