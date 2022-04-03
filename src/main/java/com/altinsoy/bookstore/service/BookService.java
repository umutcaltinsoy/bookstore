package com.altinsoy.bookstore.service;

import com.altinsoy.bookstore.dto.BookDto;
import com.altinsoy.bookstore.model.Book;

import java.util.List;

public interface BookService {
    List<BookDto> getBooks();

    void addBook(BookDto bookDto);

    void deleteBook(String bookName);

    BookDto findByBookName(String bookName);

}
