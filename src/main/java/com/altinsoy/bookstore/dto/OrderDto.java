package com.altinsoy.bookstore.dto;

import com.altinsoy.bookstore.model.Book;
import com.altinsoy.bookstore.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private Customer customer;
    private List<Book> books;

}
