package com.s1410.calme.Domain.Dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RequestEditAssisted(

        @NotNull
        Long id,
        @Size(min = 2, max = 20, message = "firstName must be between 2 and 20 characters in length")
        String firstName,
        @Size(min = 2, max = 20, message = "secondName must be between 2 and 20 characters in length")
        String secondName,
        @Size(min = 2, max = 40, message = "lastName must be between 2 and 40 characters in length")
        String lastName,
        @Pattern(regexp = "\\d+", message = "DNI must be a positive number.")
        @Size(min = 7, max = 9, message = "DNI must be between 7 and 9 numbers in length")
        String DNI,
        @Past(message = "LocalDate must be a date before the current date")
        LocalDate dateOfBirth
) {
}
