package com.s1410.calme.Domain.Dtos.response;

import com.s1410.calme.Domain.Entities.RelationAA;

import java.time.LocalDate;
import java.util.List;

public record ResponseAssistent(
        String email,
        String DNI,
        LocalDate dateOfBirth,
        List<RelationAA> relationsAA
) {
}
