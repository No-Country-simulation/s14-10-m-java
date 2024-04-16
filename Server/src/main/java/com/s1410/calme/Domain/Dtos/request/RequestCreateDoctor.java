package com.s1410.calme.Domain.Dtos.request;
import com.s1410.calme.Domain.Utils.RolesEnum;
import com.s1410.calme.Domain.Utils.Specialty;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RequestCreateDoctor(

        @Email
        @NotNull(message = "Email cannot be null")
        String email,
        @NotNull(message = "Password cannot be null")
        @Size(min = 7, message = "Password must have at least 8 characters.")
        String password,
        @NotNull(message = "firstName cannot be null")
        String firstName,
        String secondName,
        @NotNull(message = "lastName cannot be null")
        String lastName,
        @NotNull(message = "DNI cannot be null")
        @Pattern(regexp = "\\d+", message = "DNI must be a positive number.")
        @Size(max = 9, min = 7, message = "DNI must be between 7 and 9 numbers in length")
        String DNI,
        Specialty specialty,
        @Past
        @NotNull(message = "dateOfBirth cannot be null")
        LocalDate dateOfBirth,
        @NotNull
        Long phoneNumber,
        Boolean morning,
        Boolean afternoon,
        Boolean night,
        Integer postalCode,
        Long licenseNumber,
        String address

) {
}

