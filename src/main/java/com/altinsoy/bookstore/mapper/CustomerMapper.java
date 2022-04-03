package com.altinsoy.bookstore.mapper;

import com.altinsoy.bookstore.dto.CustomerDto;
import com.altinsoy.bookstore.model.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer mapCustomerDtoToCustomer(CustomerDto customerDto);
    CustomerDto mapCustomerToCustomerDto(Customer customer);
}
