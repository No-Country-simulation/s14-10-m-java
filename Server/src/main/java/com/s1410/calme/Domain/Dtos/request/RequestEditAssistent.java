package com.s1410.calme.Domain.Dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RequestEditAssistent(

        @NotNull(message = "Id cannot be null")
        Long id,
        @Email
        @NotNull(message = "Email cannot be null")
        String email,
        @NotNull(message = "Password cannot be null")
        @Size(min = 7, message = "Password must have at least 8 characters.")
        String password,
        @NotNull(message = "DNI cannot be null")
        @Pattern(regexp = "\\d+", message = "DNI must be a positive number.")
        @Size(max = 9, min = 7, message = "DNI must be between 7 and 9 numbers in length")
        String DNI,
        @NotNull(message = "dateOfBirth cannot be null")
        LocalDate dateOfBirth

) {

}
