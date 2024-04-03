package com.AppTurnos.s1410.Domain.Dtos.request;

import java.time.LocalDate;

public record RequestCreateAssisted(

     //   String email,
     //   String password,
        String DNI,
        LocalDate dateOfBirth

) {
}
