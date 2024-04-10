package com.s1410.calme.Domain.Dtos.response;
import com.s1410.calme.Domain.Utils.Availability;
import com.s1410.calme.Domain.Utils.Specialty;

public record ResponseDoctor(

        String email,
        String firstName,
        String secondName,
        String lastName,
        String DNI,
        Specialty specialty,
        Availability availability

) {
}

