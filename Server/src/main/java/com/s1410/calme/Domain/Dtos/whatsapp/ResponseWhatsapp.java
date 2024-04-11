package com.s1410.calme.Domain.Dtos.whatsapp;

import java.util.List;

public record ResponseWhatsapp(
        String messaging_product,
        List<ResponseWhatsappContact> contacts,
        List<ResponseWhatsappMessage> messages
) {
}
