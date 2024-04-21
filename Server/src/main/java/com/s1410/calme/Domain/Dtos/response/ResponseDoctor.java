package com.s1410.calme.Domain.Dtos.response;
import com.s1410.calme.Domain.Utils.Specialty;

import java.time.LocalDate;

public record ResponseDoctor(

        Long id,
        String email,
        String firstName,
        String secondName,
        String lastName,
        String DNI,
        Specialty specialty,
        Long phoneNumber,
        LocalDate dateOfBirth,
        Boolean morning,
        Boolean afternoon,
        Boolean night,
        Integer postalCode,
        Long licenseNumber,
        String address,
        String imageUrl,
        Boolean validUser
) {
}

