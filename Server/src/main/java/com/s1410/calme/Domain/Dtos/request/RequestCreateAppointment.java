package com.s1410.calme.Domain.Dtos.request;

import java.time.LocalDateTime;

public record RequestCreateAppointment(
        LocalDateTime date,
        Long doctorId,
        Long assistedId,
        Long assistentId
) {
}
