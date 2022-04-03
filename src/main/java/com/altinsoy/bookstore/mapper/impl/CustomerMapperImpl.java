package com.altinsoy.bookstore.mapper.impl;

import com.altinsoy.bookstore.dto.CustomerDto;
import com.altinsoy.bookstore.mapper.CustomerMapper;
import com.altinsoy.bookstore.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper {
    @Override
    public Customer mapCustomerDtoToCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .identityNumber(customerDto.getIdentityNumber())
                .customerName(customerDto.getCustomerName())
                .customerSurname(customerDto.getCustomerSurname())
                .email(customerDto.getEmail())
                .build();
    }

    @Override
    public CustomerDto mapCustomerToCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .identityNumber(customer.getIdentityNumber())
                .customerName(customer.getCustomerName())
                .customerSurname(customer.getCustomerSurname())
                .email(customer.getEmail())
                .build();
    }
}
