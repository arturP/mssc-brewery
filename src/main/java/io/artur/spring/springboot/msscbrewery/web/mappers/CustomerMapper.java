package io.artur.spring.springboot.msscbrewery.web.mappers;

import io.artur.spring.springboot.msscbrewery.model.Customer;
import io.artur.spring.springboot.msscbrewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

/**
 *
 */
@Mapper
public interface CustomerMapper {
    CustomerDto customerToCustomerDto(Customer customer);
    Customer customerDtoToCustomer(CustomerDto customerDto);
}
