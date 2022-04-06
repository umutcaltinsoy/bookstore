package com.altinsoy.bookstore.service.impl;

import com.altinsoy.bookstore.dto.BookOrderDto;
import com.altinsoy.bookstore.dto.Delete;
import com.altinsoy.bookstore.dto.OrderListDto;
import com.altinsoy.bookstore.dto.OrderRequestDto;
import com.altinsoy.bookstore.exceptions.CustomerNotFoundException;
import com.altinsoy.bookstore.model.Book;
import com.altinsoy.bookstore.model.Customer;
import com.altinsoy.bookstore.model.Order;
import com.altinsoy.bookstore.repository.BookRepository;
import com.altinsoy.bookstore.repository.CustomerRepository;
import com.altinsoy.bookstore.repository.OrderRepository;
import com.altinsoy.bookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public Order addOrder(OrderRequestDto orderRequestDto) {

        List<Book> bookList = new ArrayList<>();
        List<Long> bookIds = orderRequestDto.getBookOrderDto().stream()
                .map(BookOrderDto::getBookId).collect(Collectors.toList());

        for (Long id : bookIds) {
            Optional<Book> book = bookRepository.findById(id);
            book.ifPresent(bookList::add);
        }

        Optional<Customer> customer = customerRepository.findById(orderRequestDto.getCustomerId());
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException("Customer not exist with given ID");
        }
        Order order = createOrder(customer.get(), bookList, orderRequestDto.getBookOrderDto());
        updateStock(orderRequestDto.getBookOrderDto());
        return orderRepository.save(order);
    }

    @Override
    public List<OrderListDto> listOrdersOfCustomers(Long id) {
        return orderRepository.findOrderByCustomerId(id).stream().map(order -> modelMapper.map(order, OrderListDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Delete delete) {
        getIncreaseStock(delete.getBookOrderDto());
        orderRepository.deleteOrderById(delete.getId());
    }


    private Order createOrder(Customer customer, List<Book> bookList, List<BookOrderDto> bookOrderDtoList) {
        return Order.builder()
                .id(0L)
                .books(bookList)
                .customer(customer)
                .createdDate(new Date())
                .build();
    }

    private void getIncreaseStock(List<BookOrderDto> bookOrderDtos) {
        for (BookOrderDto item : bookOrderDtos) {
            Optional<Book> book = bookRepository.findById(item.getBookId());
            Book book1 = book.get();
            book1.setUnitsInStock(book1.getUnitsInStock() + item.getQuantity());
            bookRepository.save(book1);
        }

    }


    private void updateStock(List<BookOrderDto> bookOrderDto) {
        for (BookOrderDto item : bookOrderDto) {
            Optional<Book> book = bookRepository.findById(item.getBookId());

            if (book.isPresent()) {
                Book book1 = book.get();
                book1.setUnitsInStock(book1.getUnitsInStock() - item.getQuantity());
                bookRepository.save(book1);
            }

        }
    }
}
