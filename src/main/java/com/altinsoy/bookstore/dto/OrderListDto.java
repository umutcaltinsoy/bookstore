package com.altinsoy.bookstore.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderListDto {
    private Long id;
    private List<BookResponseDto> books;
    private Date createdDate;
}