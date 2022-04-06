package com.altinsoy.bookstore.dto;

import lombok.Data;

@Data
public class BookResponseDto {
    private Long id;
    private String bookName;
    private String author;
    private Double price;
}
