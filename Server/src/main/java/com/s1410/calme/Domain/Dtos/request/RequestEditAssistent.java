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
        @Size(max = 9, min = 7)
        String DNI,
        @Past
        LocalDate dateOfBirth

) {

}
