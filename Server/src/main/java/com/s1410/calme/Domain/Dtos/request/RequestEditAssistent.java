package com.s1410.calme.Domain.Dtos.request;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record RequestEditAssistent(

        @NotNull(message = "Id cannot be null")
        Long id,
        String firstName,
        String secondName,
        String lastName,
        @Pattern(regexp = "\\d+", message = "DNI must be a positive number.")
        @Size(max = 9, min = 7, message = "DNI must be between 7 and 9 numbers in length")
        String DNI,
        @Past
        LocalDate dateOfBirth

) {

}
