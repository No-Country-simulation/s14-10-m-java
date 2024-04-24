package com.s1410.calme.Domain.Dtos.request;

import com.s1410.calme.Domain.Utils.Specialty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RequestEditDoctor(

        @NotNull(message = "Id cannot be null")
        Long id,
        String firstName,
        String secondName,
        String lastName,
        @Pattern(regexp = "\\d+", message = "DNI must be a positive number.")
        @Size(max = 9, min = 7)
        String DNI,
        @Past
        LocalDate dateOfBirth,
        Specialty specialty,
        Long phoneNumber,
        Boolean morning,
        Boolean afternoon,
        Boolean night,
        Integer postalCode,
        Long licenseNumber,
        String address
) {
}

