package com.s1410.calme.Domain.Dtos.response;

import java.time.LocalDate;

public record ResponseAssisted(

        String DNI,
        LocalDate dateOfBirth
        // List<RelationAA> relationsAA,
        // List<Appointment> appointmentList
) {
}
