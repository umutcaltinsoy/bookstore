package com.altinsoy.bookstore.service;


import com.altinsoy.bookstore.dto.OrderRequestDto;
import com.altinsoy.bookstore.model.Order;

import java.util.List;

public interface OrderService {
    Order addOrder(OrderRequestDto orderRequestDto);

    List<Order> listOrdersOfCustomers(Long id);
}
