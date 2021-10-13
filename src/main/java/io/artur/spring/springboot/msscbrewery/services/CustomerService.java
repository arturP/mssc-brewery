package io.artur.spring.springboot.msscbrewery.services;

import io.artur.spring.springboot.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

/**
 *
 */
public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);
    CustomerDto saveNewCustomer(CustomerDto customerDto);

    void updateById(UUID customerId, CustomerDto customerDto);

    void deleteById(UUID customerId);
}
