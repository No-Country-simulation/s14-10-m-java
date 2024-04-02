package com.AppTurnos.s1410.Domain.Dtos.response;

import com.AppTurnos.s1410.Domain.Entities.RelationAA;

import java.time.LocalDate;
import java.util.List;

public record ResponseAssistent(
        String email,
        String DNI,
        LocalDate dateOfBirth,
        List<RelationAA> relationsAA
) {
}
