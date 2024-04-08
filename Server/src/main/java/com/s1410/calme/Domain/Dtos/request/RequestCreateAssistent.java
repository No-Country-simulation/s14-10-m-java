package com.s1410.calme.Domain.Dtos.request;
import jakarta.validation.constraints.Email;
import java.time.LocalDate;

public record RequestCreateAssistent(
        @Email
        String email,
        String password,
        String DNI,
        LocalDate dateOfBirth

) {


}
