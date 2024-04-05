package com.s1410.calme.Domain.Dtos.response;

import com.s1410.calme.Domain.Entities.Appointment;
import com.s1410.calme.Domain.Entities.Assistent;
import com.s1410.calme.Domain.Entities.RelationAA;

import java.time.LocalDate;
import java.util.List;

public record ResponseAssisted(

     //   String email,
        String DNI,
        LocalDate dateOfBirth,
     List<Assistent> assistantList,
     // List<RelationAA> relationsAA,
     List<Appointment> appointmentList
) {
}
