package com.altinsoy.bookstore.controller;

import com.altinsoy.bookstore.dto.BookDto;
import com.altinsoy.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookController {
    private final BookService bookService;

    @GetMapping("/books")
    public List<BookDto> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping("/book")
    public ResponseEntity<?> addBook(@RequestBody BookDto bookDto) {
        bookService.addBook(bookDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/book/{bookName}")
    public ResponseEntity<?> deleteBook(@PathVariable(name = "bookName") String bookName) {
        bookService.deleteBook(bookName);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "/book", params = "bookName")
    public ResponseEntity<BookDto> getBook(
            @RequestParam(name = "bookName") String bookName) {
        BookDto bookDto = bookService.findByBookName(bookName);
        return ResponseEntity.ok(bookDto);
    }
}
