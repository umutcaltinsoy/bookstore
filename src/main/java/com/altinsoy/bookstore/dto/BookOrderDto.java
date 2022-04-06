package com.altinsoy.bookstore.dto;

import lombok.Data;

@Data
public class BookOrderDto {
    private Long bookId;
    private int quantity;
}
