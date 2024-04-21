package com.s1410.calme.Application.SImplementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s1410.calme.Application.Config.Validations.RoleValidation;
import com.s1410.calme.Domain.Dtos.whatsapp.*;
import com.s1410.calme.Domain.Entities.Appointment;
import com.s1410.calme.Domain.Repositories.AppointmentRepository;
import com.s1410.calme.Domain.Services.ApiWhatsappService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApiWhatsappServiceImpl implements ApiWhatsappService {

    private final AppointmentRepository appointmentRepository;
    private final RoleValidation roleValidation;

    @Value("${whatsapp.identificador}") String identificador;
    @Value("${whatsapp.token}") String token;

    private RestClient clientBuilder() {
        return RestClient.builder()
                .baseUrl("https://graph.facebook.com/v18.0/" + identificador + "/messages")
                .defaultHeader("Authorization","Bearer " + token)
                .build();
    }

    public List<ResponseWhatsapp> sendAllReminders() throws JsonProcessingException {

        LocalDateTime dayAfterTomorrow = LocalDateTime.now().plusDays(8);
        List<Appointment> appointmentsForTomorrow = appointmentRepository.
                findAppointmentsByDateByActive(true,
                        dayAfterTomorrow.getDayOfMonth(),
                        dayAfterTomorrow.getMonthValue(),
                        dayAfterTomorrow.getYear());
        List<ResponseWhatsapp> clientResponses = new ArrayList<>();

        for (Appointment appointment : appointmentsForTomorrow) {
            ResponseWhatsapp response = sendMessageToApi(setMessage(appointment));
            clientResponses.add(response);
        }
        return clientResponses;
    }

    public ResponseWhatsapp sendMessageToApi(String message) throws JsonProcessingException {

        RequestMessage request = new RequestMessage(
                "whatsapp", "543513849396",
                new RequestMessageText(message));

        String response = clientBuilder().post()
                .uri("")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(String.class);

        ObjectMapper obj = new ObjectMapper();
        return obj.readValue(response,ResponseWhatsapp.class);
    }

    public String setMessage(Appointment appointment){

        String firstName;
        String lastName;
        Long phone;
        if (appointment.getAssistent() != null) {
            firstName = appointment.getAssistent().getFirstName();
            lastName = appointment.getAssistent().getLastName();
            phone = appointment.getAssistent().getPhoneNumber();
        }
        else {
            firstName = appointment.getAssisted().getFirstName();
            lastName = appointment.getAssisted().getLastName();
            phone = appointment.getAssisted().getRelationsAA().get(0).getAssistent().getPhoneNumber();
        }

        String doctorName = appointment.getDoctor().getFirstName();
        String doctorLastName = appointment.getDoctor().getLastName();
        String doctorSpecialty = appointment.getDoctor().getSpecialty().toString();
        int apDay = appointment.getDate().getDayOfMonth();
        int apMonth = appointment.getDate().getMonthValue();
        int apYear = appointment.getDate().getYear();
        int apHour = appointment.getDate().getHour();
        int apMinutes = appointment.getDate().getMinute();

        String reminderMessage =
                "¡Buenos días! Nos comunicamos desde Calme para recordarte que " + firstName + " "
                + lastName + " tiene un turno el día " + apDay + "/" + apMonth + "/" + apYear +
                " a las " + apHour + ":" + apMinutes +  "hs con el/la dr/a " + doctorName + " " +
                doctorLastName + ", " + doctorSpecialty + ".";

        return reminderMessage;
    }

   /*@PostConstruct
    void sendMessageToApi() throws JsonProcessingException {
        sendAllReminders();
    }*/


}

