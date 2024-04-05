package com.s1410.calme.Domain.Dtos.request;

import com.s1410.calme.Domain.Utils.Specialty;

public record RequestEditDoctor(

        Long id,
        String email,
        String password,
        Specialty specialty

) {
}

