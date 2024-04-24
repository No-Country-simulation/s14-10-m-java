package com.s1410.calme.Domain.Dtos.request;

import java.time.LocalDateTime;

public record RequestAppointmentBetweenDates(
        LocalDateTime startDate,
        LocalDateTime finishDate
) {
}
