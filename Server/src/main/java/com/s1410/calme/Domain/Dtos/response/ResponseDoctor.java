package com.s1410.calme.Domain.Dtos.response;
import com.s1410.calme.Domain.Utils.Specialty;

public record ResponseDoctor(

        String email,
        String firstName,
        String secondName,
        String firstLastName,
        String secondLastName,
        String DNI,
        Specialty specialty

) {
}

