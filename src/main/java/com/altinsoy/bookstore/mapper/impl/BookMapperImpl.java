package com.altinsoy.bookstore.mapper.impl;

import com.altinsoy.bookstore.dto.BookDto;
import com.altinsoy.bookstore.mapper.BookMapper;
import com.altinsoy.bookstore.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book mapBookDtoToBook(BookDto bookDto) {
        return Book.builder()
                .bookName(bookDto.getBookName())
                .author(bookDto.getAuthor())
                .price(bookDto.getPrice())
                .unitsInStock(bookDto.getUnitsInStock())
                .build();
    }

    @Override
    public BookDto mapBookToBookDto(Book book) {
        return BookDto.builder()
                .bookName(book.getBookName())
                .author(book.getAuthor())
                .price(book.getPrice())
                .unitsInStock(book.getUnitsInStock())
                .build();
    }
}
