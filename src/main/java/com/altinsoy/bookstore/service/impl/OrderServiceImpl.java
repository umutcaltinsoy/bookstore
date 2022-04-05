package com.altinsoy.bookstore.service.impl;

import com.altinsoy.bookstore.dto.BookOrderDto;
import com.altinsoy.bookstore.dto.OrderRequestDto;
import com.altinsoy.bookstore.model.Book;
import com.altinsoy.bookstore.model.Customer;
import com.altinsoy.bookstore.model.Order;
import com.altinsoy.bookstore.repository.BookRepository;
import com.altinsoy.bookstore.repository.CustomerRepository;
import com.altinsoy.bookstore.repository.OrderRepository;
import com.altinsoy.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;


    @Override
    public Order addOrder(OrderRequestDto orderRequestDto) {

        Customer customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        List<Book> bookList = new ArrayList<>();
        List<Long> bookIds = orderRequestDto.getBookOrderDto().stream()
                .map(BookOrderDto::getBookId).collect(Collectors.toList());

        for (Long id : bookIds) {
            Book book = bookRepository.findById(id).get();
            bookList.add(book);
        }

        Order order = Order.builder()
                .id(0L)
                .books(bookList)
                .customer(customer)
                .createdDate(new Date())
                .build();
        updateStock(orderRequestDto.getBookOrderDto());
        return orderRepository.save(order);
    }

    @Override
    public List<Order> listOrdersOfCustomers(Long id) {
        return orderRepository.findByCustomer_Id(id);
    }


    private void updateStock(List<BookOrderDto> bookOrderDto) {
        for (BookOrderDto item : bookOrderDto) {
            Book book = bookRepository.findById(item.getBookId()).get();
            book.setUnitsInStock(book.getUnitsInStock() - item.getCount());
            bookRepository.save(book);
        }
    }
}
