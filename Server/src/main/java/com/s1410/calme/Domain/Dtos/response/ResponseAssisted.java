package com.s1410.calme.Domain.Dtos.response;

import lombok.NonNull;

import java.time.LocalDate;

public record ResponseAssisted(


        String DNI,
        String firstName,
        String secondName,
        String lastName,
        LocalDate dateOfBirth
        // List<RelationAA> relationsAA,
) {
}
