package com.altinsoy.bookstore.service.impl;

import com.altinsoy.bookstore.dto.CustomerDto;
import com.altinsoy.bookstore.exceptions.CustomerNotFoundException;
import com.altinsoy.bookstore.mapper.CustomerMapper;
import com.altinsoy.bookstore.model.Customer;
import com.altinsoy.bookstore.repository.CustomerRepository;
import com.altinsoy.bookstore.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDto> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::mapCustomerToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean saveCustomer(CustomerDto customerDto) {
        Boolean isSaved = false;
        Customer customer = customerMapper.mapCustomerDtoToCustomer(customerDto);
        Optional<Customer> isCustomerExist = customerRepository.findByIdentityNumber(customerDto.getIdentityNumber());
        if (isCustomerExist.isEmpty()) {
            customerRepository.save(customer);
            isSaved = true;
        }
        return isSaved;
    }

    @Override
    public CustomerDto updateCustomer(String identityNumber, CustomerDto customerDto) {
        Customer customer = customerRepository.findByIdentityNumber(identityNumber).orElseThrow(
                () -> new CustomerNotFoundException("Customer couldn't found with given id " + identityNumber));
        setCustomerDetails(customerDto, customer);
        Customer updatedCustomer = customerRepository.save(customer);

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
        return customerDto;
    }

    @Override
    public void deleteCustomer(String identityNumber) {
        customerRepository.deleteByIdentityNumber(identityNumber);
    }

    private void setCustomerDetails(CustomerDto customerDto, Customer customer) {
        customer.setIdentityNumber(customerDto.getIdentityNumber());
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setCustomerSurname(customerDto.getCustomerSurname());
        customer.setEmail(customerDto.getEmail());
    }

}
