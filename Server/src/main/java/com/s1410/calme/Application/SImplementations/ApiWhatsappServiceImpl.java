package com.s1410.calme.Application.SImplementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s1410.calme.Domain.Dtos.whatsapp.*;
import com.s1410.calme.Domain.Services.ApiWhatsappService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ApiWhatsappServiceImpl implements ApiWhatsappService {

    private final RestClient restClient;

    public ApiWhatsappServiceImpl(
            @Value("${whatsapp.identificador}") String identificador,
            @Value("${whatsapp.token}") String token
    ) {
        restClient = RestClient.builder()
                .baseUrl("https://graph.facebook.com/v18.0/" + identificador + "/messages")
                .defaultHeader("Authorization", "Bearer " + token)
                .build();
    }

    public ResponseWhatsapp sendMessage(MessageBodyDTO payload) throws JsonProcessingException {
        RequestMessage request = new RequestMessage
                ("whatsapp",payload.number(),
                        new RequestMessageText(payload.message()));

        String response = restClient.post()
                .uri("")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(String.class);

        ObjectMapper obj = new ObjectMapper();
        return obj.readValue(response, ResponseWhatsapp.class);
    }
}
