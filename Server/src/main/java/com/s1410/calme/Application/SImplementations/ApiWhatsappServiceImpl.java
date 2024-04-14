package com.s1410.calme.Application.SImplementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s1410.calme.Domain.Dtos.whatsapp.*;
import com.s1410.calme.Domain.Entities.Appointment;
import com.s1410.calme.Domain.Repositories.AppointmentRepository;
import com.s1410.calme.Domain.Services.ApiWhatsappService;
import com.s1410.calme.Domain.Services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiWhatsappServiceImpl implements ApiWhatsappService {

    private final AppointmentRepository appointmentRepository;
    @Value("${whatsapp.identificador}") String identificador;
    @Value("${whatsapp.token}") String token;

    private RestClient clientBuilder() {
        return RestClient.builder()
                .baseUrl("https://graph.facebook.com/v18.0/" + identificador + "/messages")
                .defaultHeader("Authorization", "Bearer " + token)
                .build();
    }

    public Boolean sendMessage()
            throws JsonProcessingException {
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        int day = tomorrow.getDayOfMonth();
        int month = tomorrow.getMonthValue();
        int year = tomorrow.getYear();
        List<Appointment> appointmentsForTomorrow = appointmentRepository.
                findAppointmentsByDateByActive(true, day, month, year);
        String response;

        RestClient restClient = clientBuilder();
        for (Appointment appointment : appointmentsForTomorrow){
            String firstName = appointment.getAssistent().getFirstName();
            if (firstName == null) {
                firstName = appointment.getAssisted().getFirstName();
            }

            String lastName = appointment.getAssistent().getLastName();
            if (lastName == null) {
                lastName = appointment.getAssisted().getLastName();
            }

            Long phone = appointment.getAssistent().getPhoneNumber();
            if (phone == null) {
                phone = appointment.getAssisted().getRelationsAA().get(0)
                        .getAssistent().getPhoneNumber();
            }

            String message = "¡Buenos días! Nos comunicamos desde Calme para recordarte que "
                    + firstName + " " + lastName + " tiene un turno el día " +
                    tomorrow.getDayOfMonth() + "/" + tomorrow.getMonthValue() +
                    "/" + tomorrow.getYear() + " a las " + appointment.getDate().getHour() + ":" +
                    appointment.getDate().getMinute() + "hs con el/la dr/a " +
                    appointment.getDoctor().getFirstName() + " " +
                    appointment.getDoctor().getLastName() +
                    ", " + appointment.getDoctor().getSpecialty() + ".";

            RequestMessage request = new RequestMessage
            ("whatsapp", "543517707973",
           new RequestMessageText(message));

            response = restClient.post()
                    .uri("")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(request)
                    .retrieve()
                    .body(String.class);
        }

        return true;
    }
}

/*
public ResponseWhatsapp sendMessage(MessageBodyDTO payload)
throws JsonProcessingException {
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
*/