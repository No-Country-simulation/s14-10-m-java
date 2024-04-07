package com.s1410.calme.Domain.Dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record RequestCreateAssisted(
        @NotNull(message = "DNI cannot be null")
        @Pattern(regexp = "\\d+", message = "DNI must be a positive number.")
        String DNI,
        @NotNull(message = "dateOfBirth cannot be null")
        LocalDate dateOfBirth,
        Long AssistantID,
        String relationTypeWithAssistant
){}
