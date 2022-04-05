package com.altinsoy.bookstore.controller;

import com.altinsoy.bookstore.dto.OrderRequestDto;
import com.altinsoy.bookstore.model.Order;
import com.altinsoy.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> addOrder(@RequestBody OrderRequestDto orderRequestDto) {
        Order order = orderService.addOrder(orderRequestDto);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/listOfOrders")
    public ResponseEntity<List<Order>> listOrdersOfCustomers(@RequestParam Long id) {
        List<Order> orders = orderService.listOrdersOfCustomers(id);
        return ResponseEntity.ok(orders);
    }

}
