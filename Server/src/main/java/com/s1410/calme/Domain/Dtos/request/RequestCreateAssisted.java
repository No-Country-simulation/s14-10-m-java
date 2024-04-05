package com.s1410.calme.Domain.Dtos.request;

import java.time.LocalDate;

public record RequestCreateAssisted(

     //   String email,
     //   String password,
        String DNI,
        LocalDate dateOfBirth

) {
}
