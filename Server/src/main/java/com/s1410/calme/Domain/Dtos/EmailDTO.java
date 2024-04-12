package com.s1410.calme.Domain.Dtos;
public record EmailDTO(
        String toAddress,
        String subject,
        String body
) {
}
