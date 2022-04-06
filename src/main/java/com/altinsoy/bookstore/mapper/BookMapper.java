package com.altinsoy.bookstore.mapper;

import com.altinsoy.bookstore.dto.BookDto;
import com.altinsoy.bookstore.model.Book;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
    Book mapBookDtoToBook(BookDto bookDto);

    BookDto mapBookToBookDto(Book book);
}
