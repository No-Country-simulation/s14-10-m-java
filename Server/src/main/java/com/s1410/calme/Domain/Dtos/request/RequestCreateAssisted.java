package com.s1410.calme.Domain.Dtos.request;

import com.s1410.calme.Domain.Utils.RelationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RequestCreateAssisted(
        @NotNull(message = "DNI cannot be null")
        @Pattern(regexp = "\\d+", message = "DNI must be a positive number")
        @Size(min = 7, max = 9, message = "DNI must be between 7 and 9 numbers in length")
        String DNI,
        @NotNull(message = "firstName cannot be null")
        @Size(min = 2, max = 20, message = "firstName must be between 2 and 20 characters in length")
        String firstName,
        @Size(min = 2, max = 20, message = "secondName must be between 2 and 20 characters in length")
        String secondName,
        @NotNull(message = "lastName cannot be null")
        @Size(min = 2, max = 40, message = "secondName must be between 2 and 40 characters in length")
        String lastName,
        @NotNull(message = "dateOfBirth cannot be null")
        @Past(message = "LocalDate must be a date before the current date")
        LocalDate dateOfBirth,
        Long AssistantID,
        String relationTypeWithAssistant
){}
