package com.s1410.calme.Domain.Dtos.response;

import java.time.LocalDate;

public record ResponseAssisted(
        String id,
        String DNI,
        String firstName,
        String secondName,
        String lastName,
        LocalDate dateOfBirth,
        String imageUrl
) {
}
