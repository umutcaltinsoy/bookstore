package com.altinsoy.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    @NotEmpty
    @Size(min = 3, message = "Name should be at least 3 characters")
    private String bookName;
    @NotEmpty
    @Size(min = 3, message = "Author is mandatory")
    private String author;
    @NotEmpty
    private Double price;
    @NotEmpty
    private Integer unitsInStock;

}
