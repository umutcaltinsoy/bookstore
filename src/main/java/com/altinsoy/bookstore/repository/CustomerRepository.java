package com.altinsoy.bookstore.repository;

import com.altinsoy.bookstore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByIdentityNumber(String identityNumber);

    void deleteByIdentityNumber(String identityNumber);
}
