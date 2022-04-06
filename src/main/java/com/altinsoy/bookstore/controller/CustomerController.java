package com.altinsoy.bookstore.controller;

import com.altinsoy.bookstore.dto.CustomerDto;
import com.altinsoy.bookstore.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerDto> getCustomers() {
        log.info("Getting all customers...");
        return customerService.getCustomers();
    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerDto> saveCustomer(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto saveCustomer = customerService.saveCustomer(customerDto);
        log.info("Saving customer...");
        return ResponseEntity.ok(saveCustomer);
    }

    @PutMapping("/customer/{identityNumber}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable(name = "identityNumber") String identityNumber,
            @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomer = customerService.updateCustomer(identityNumber, customerDto);
        log.info("Updating customer...");
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/customer/{identityNumber}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "identityNumber") String identityNumber) {
        customerService.deleteCustomer(identityNumber);
        log.info("Getting all customers...");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "/customer", params = "identityNumber")
    public ResponseEntity<CustomerDto> getCustomerByIdentityNumber(
            @RequestParam(name = "identityNumber") String identityNumber) {
        CustomerDto customerDto = customerService.getCustomerByIdentityNumber(identityNumber);
        log.info("Getting customer...");
        return ResponseEntity.ok(customerDto);
    }
}
