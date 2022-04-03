package com.altinsoy.bookstore.service;

import com.altinsoy.bookstore.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getCustomers();

    Boolean saveCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(String identityNumber, CustomerDto customerDto);

    CustomerDto getCustomerByIdentityNumber(String identityNumber);

    void deleteCustomer(String identityNumber);
}
