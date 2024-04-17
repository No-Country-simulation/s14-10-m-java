package com.s1410.calme.Domain.Dtos.response;
import com.s1410.calme.Domain.Utils.Specialty;

public record ResponseDoctor(

        Long id,
        String email,
        String firstName,
        String secondName,
        String lastName,
        String DNI,
        Specialty specialty,
        Long phoneNumber,
        Boolean morning,
        Boolean afternoon,
        Boolean night,
        Integer postalCode,
        Long licenseNumber,
        String address
) {
}

