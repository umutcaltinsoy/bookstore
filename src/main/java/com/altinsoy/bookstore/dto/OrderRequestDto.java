package com.altinsoy.bookstore.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private List<BookOrderDto> bookOrderDto;
    private Long customerId;
}
