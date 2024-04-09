package com.s1410.calme.Domain.Dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RequestEditAssisted(

        @NotNull
        Long id,
        String firstName,
        String secondName,
        String lastName,
        String DNI,
        LocalDate dateOfBirth
) {
}
