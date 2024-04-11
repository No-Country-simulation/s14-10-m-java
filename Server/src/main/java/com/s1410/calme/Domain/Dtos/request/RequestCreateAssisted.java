package com.s1410.calme.Domain.Dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RequestCreateAssisted(
        @NotNull(message = "DNI cannot be null")
        @Pattern(regexp = "\\d+", message = "DNI must be a positive number.")
        @Size(max = 9, min = 7, message = "DNI must be between 7 and 9 numbers in length")
        String DNI,
        @NotNull(message = "firstName cannot be null")
        String firstName,
        String secondName,
        @NotNull(message = "lastName cannot be null")
        String lastName,
        @NotNull(message = "dateOfBirth cannot be null")
        LocalDate dateOfBirth,
        Long AssistantID,
        String relationTypeWithAssistant
){}
