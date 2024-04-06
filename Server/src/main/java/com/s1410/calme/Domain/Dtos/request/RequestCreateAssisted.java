package com.s1410.calme.Domain.Dtos.request;

import java.time.LocalDate;

public record RequestCreateAssisted(
        String DNI,
        LocalDate dateOfBirth,
        Long AssistantID,
        String relationTypeWithAssistant
){}
