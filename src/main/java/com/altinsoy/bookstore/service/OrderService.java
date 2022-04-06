package com.altinsoy.bookstore.service;


import com.altinsoy.bookstore.dto.DeleteOrderDto;
import com.altinsoy.bookstore.dto.OrderListDto;
import com.altinsoy.bookstore.dto.OrderRequestDto;
import com.altinsoy.bookstore.model.Order;

import java.util.List;

public interface OrderService {
    Order addOrder(OrderRequestDto orderRequestDto);

    List<OrderListDto> listOrdersOfCustomers(Long id);

    void deleteOrder(DeleteOrderDto deleteOrderDto);

    Order findOrderById(Long id);

}
