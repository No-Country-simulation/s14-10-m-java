package com.s1410.calme.Domain.Dtos.response;

import com.s1410.calme.Domain.Entities.RelationAA;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;

public record ResponseAssistent(
        Long id,
        String email,
        String firstName,
        String secondName,
        String lastName,
        String DNI,
        Long phoneNumber,
        LocalDate dateOfBirth,
        List<RelationAA> relationsAA
) {
}
