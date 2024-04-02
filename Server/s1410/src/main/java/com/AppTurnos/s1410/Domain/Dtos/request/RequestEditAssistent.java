package com.AppTurnos.s1410.Domain.Dtos.request;

import java.time.LocalDate;

public record RequestEditAssistent(

        Long id,
        String email,
        String password,
        String DNI,
        LocalDate dateOfBirth

) {

}
