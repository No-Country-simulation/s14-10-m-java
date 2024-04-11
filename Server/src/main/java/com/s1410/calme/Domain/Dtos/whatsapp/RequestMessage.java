package com.s1410.calme.Domain.Dtos.whatsapp;

public record RequestMessage(
        String messaging_product,
        String to,
        RequestMessageText text
) {
}
