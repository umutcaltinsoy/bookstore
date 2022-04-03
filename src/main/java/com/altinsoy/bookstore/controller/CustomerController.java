package com.altinsoy.bookstore.controller;

import com.altinsoy.bookstore.dto.CustomerDto;
import com.altinsoy.bookstore.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public List<CustomerDto> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping("/customer")
    public ResponseEntity<Boolean> saveCustomer(@RequestBody CustomerDto customerDto) {
        Boolean isSaved = customerService.saveCustomer(customerDto);
        return ResponseEntity.ok(isSaved);
    }

    @PutMapping("/customer/{identityNumber}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable(name = "identityNumber") String identityNumber,
            @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomer = customerService.updateCustomer(identityNumber, customerDto);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/customer/{identityNumber}")
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "identityNumber") String identityNumber) {
        customerService.deleteCustomer(identityNumber);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "/customer", params = "identityNumber")
    public ResponseEntity<CustomerDto> getCustomerByIdentityNumber(
            @RequestParam(name = "identityNumber") String identityNumber) {
        CustomerDto customerDto = customerService.getCustomerByIdentityNumber(identityNumber);
        return ResponseEntity.ok(customerDto);
    }
}
