package com.s1410.calme.Domain.Dtos.request;
import com.s1410.calme.Domain.Utils.Specialty;

import java.time.LocalDate;

public record RequestCreateDoctor(

        String email,
        String password,
        String firstName,
        String secondName,
        String firstLastName,
        String secondLastName,
        String DNI,
        Specialty specialty,
        LocalDate dateOfBirth

) {
}

