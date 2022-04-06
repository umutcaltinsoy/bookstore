package com.altinsoy.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    @NotEmpty
    @Size(min = 11, max = 11, message = "Identity number should be at least 11 digits")
    private String identityNumber;

    @NotEmpty
    @Size(min = 3, message = "Name should be at least 3 characters")
    private String customerName;

    @NotEmpty
    @Size(min = 2, message = "Surname should be at least 2 characters")
    private String customerSurname;

    @Email
    private String email;
}
