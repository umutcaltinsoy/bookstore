package com.altinsoy.bookstore.service.impl;

import com.altinsoy.bookstore.dto.CustomerDto;
import com.altinsoy.bookstore.exceptions.CustomerNotFoundException;
import com.altinsoy.bookstore.mapper.CustomerMapper;
import com.altinsoy.bookstore.model.Customer;
import com.altinsoy.bookstore.repository.CustomerRepository;
import com.altinsoy.bookstore.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDto> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        log.info("List customers...");
        return customers.stream()
                .map(customerMapper::mapCustomerToCustomerDto)
                .collect(Collectors.toList());
    }


    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.mapCustomerDtoToCustomer(customerDto);
        Optional<Customer> customer1 = customerRepository.findByIdentityNumber(customerDto.getIdentityNumber());
        if (customer1.isEmpty()) {
            customerRepository.save(customer);
        }
        log.info("Saving customer..." + customer);
        return customerMapper.mapCustomerToCustomerDto(customer);
    }

    @Override
    public CustomerDto updateCustomer(String identityNumber, CustomerDto customerDto) {
        Customer customer = customerRepository.findByIdentityNumber(identityNumber).orElseThrow(
                () -> new CustomerNotFoundException("Customer couldn't found with given id " + identityNumber));
        setCustomerDetails(customerDto, customer);
        Customer updatedCustomer = customerRepository.save(customer);
        log.info("Updating customer : " + customerDto);
        return customerMapper.mapCustomerToCustomerDto(updatedCustomer);
    }

    @Override
    public CustomerDto getCustomerByIdentityNumber(String identityNumber) {
        CustomerDto customerDto = new CustomerDto();
        Optional<Customer> customer = customerRepository.findByIdentityNumber(identityNumber);
        if (customer.isPresent()) {
            customerDto = customerMapper.mapCustomerToCustomerDto(customer.get());
            return customerDto;
        }
        log.info("Get customer with identity number : " + customerDto.getIdentityNumber());
        return customerDto;
    }


    @Override
    public void deleteCustomer(String identityNumber) {
        log.info("Deleting customer with ID: " + identityNumber);
        customerRepository.deleteByIdentityNumber(identityNumber);
    }

    private void setCustomerDetails(CustomerDto customerDto, Customer customer) {
        customer.setIdentityNumber(customerDto.getIdentityNumber());
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setCustomerSurname(customerDto.getCustomerSurname());
        customer.setEmail(customerDto.getEmail());
    }

}
