package io.artur.spring.springboot.msscbrewery.web.controller;

import io.artur.spring.springboot.msscbrewery.services.CustomerService;
import io.artur.spring.springboot.msscbrewery.web.model.CustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 *
 */
@AllArgsConstructor
@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping({"/{customerId"})
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable UUID customerId){
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpHeaders> handlePostRequest(@RequestBody CustomerDto customerDto) {
        CustomerDto saved = customerService.saveNewCustomer(customerDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer" + saved.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{customerId"})
    public ResponseEntity<HttpStatus> handlePutRequest(@PathVariable UUID customerId,
                                           @RequestBody CustomerDto customerDto) {
        customerService.updateById(customerId, customerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{customerId"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDeleteRequest(@PathVariable UUID customerId) {
        customerService.deleteById(customerId);
    }
}
