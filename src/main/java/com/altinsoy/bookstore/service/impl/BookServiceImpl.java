package com.altinsoy.bookstore.service.impl;

import com.altinsoy.bookstore.dto.BookDto;
import com.altinsoy.bookstore.mapper.BookMapper;
import com.altinsoy.bookstore.model.Book;
import com.altinsoy.bookstore.repository.BookRepository;
import com.altinsoy.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> getBooks() {
        List<Book> books = bookRepository.findAll();
        log.info("Get books...");
        return books.stream()
                .map(bookMapper::mapBookToBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        Book book = Book.builder().author(bookDto.getAuthor())
                .price(bookDto.getPrice()).bookName(bookDto.getBookName())
                .unitsInStock(bookDto.getUnitsInStock()).build();
        log.info("Add book..." + book);
        bookRepository.save(book);
        return bookMapper.mapBookToBookDto(book);
    }

    @Override
    public void deleteBook(String bookName) {
        log.info("Delete book : " + bookName);
        bookRepository.deleteByBookName(bookName);
    }

    @Override
    public BookDto findByBookName(String bookName) {
        BookDto bookDto = new BookDto();
        Optional<Book> book = bookRepository.findByBookName(bookName);
        if (book.isPresent()) {
            bookDto = bookMapper.mapBookToBookDto(book.get());
            return bookDto;
        }
        log.info("Find book : " + bookName);
        return bookDto;
    }
}
