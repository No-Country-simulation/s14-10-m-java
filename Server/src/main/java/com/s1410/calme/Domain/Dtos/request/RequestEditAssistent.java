package com.s1410.calme.Domain.Dtos.request;

import java.time.LocalDate;

public record RequestEditAssistent(

        Long id,
        String email,
        String password,
        String DNI,
        LocalDate dateOfBirth

) {

}