package com.s1410.calme.Domain.Dtos.request;

import java.time.LocalDateTime;

public record RequestEditAppointmentDate(
        LocalDateTime newDate
) {
}
