package com.altinsoy.bookstore.repository;

import com.altinsoy.bookstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrderByCustomerId(Long id);

    Order findOrderById(Long id);

    void deleteOrderById(Long id);


}
