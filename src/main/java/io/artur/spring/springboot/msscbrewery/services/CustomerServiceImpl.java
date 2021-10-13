package io.artur.spring.springboot.msscbrewery.services;

import io.artur.spring.springboot.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 *
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Customer Name001")
                .build();
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name(customerDto.getName())
                .build();
    }

    @Override
    public void updateById(UUID customerId, CustomerDto customerDto) {
        log.debug("Updating customer with id: " + customerId.toString());
    }

    @Override
    public void deleteById(UUID customerId) {
        log.debug("Deleting customer with id: " + customerId);
    }
}
