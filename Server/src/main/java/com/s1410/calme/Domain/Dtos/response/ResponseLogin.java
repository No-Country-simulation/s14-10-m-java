package com.s1410.calme.Domain.Dtos.response;

import jakarta.persistence.EnumType;

public record ResponseLogin(
        String jwt,
        String role,
        Long id

) {
}
