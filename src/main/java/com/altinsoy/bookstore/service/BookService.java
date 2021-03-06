package com.altinsoy.bookstore.service;

import com.altinsoy.bookstore.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getBooks();

    BookDto addBook(BookDto bookDto);

    void deleteBook(String bookName);

    BookDto findByBookName(String bookName);

}
