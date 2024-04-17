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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiWhatsappServiceImpl implements ApiWhatsappService {

    private final AppointmentRepository appointmentRepository;
    @Value("${whatsapp.identificador}")
    String identificador;
    @Value("${whatsapp.token}")
    String token;

    private RestClient clientBuilder() {
        return RestClient.builder()
                .baseUrl("https://graph.facebook.com/v18.0/" + identificador + "/messages")
                .defaultHeader("Authorization","Bearer " + token)
                .build();
    }

    @Override
    public Boolean sendMessage() throws JsonProcessingException {
        return null;
    }

    public ResponseWhatsapp sendMessageTest(MessageBodyDTO payload) throws JsonProcessingException {
        RequestMessage request = new RequestMessage("whatsapp",payload.number(),new RequestMessageText(payload.message()));

        String response = clientBuilder().post()
                .uri("")
                .contentType(MediaType.APPLICATION_JSON)
                .body(createMessageTest())
                .retrieve()
                .body(String.class);

        ObjectMapper obj = new ObjectMapper();
        return obj.readValue(response,ResponseWhatsapp.class);
    }

    public String createMessageTest() {

        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        int day = tomorrow.getDayOfMonth();
        int month = tomorrow.getMonthValue();
        int year = tomorrow.getYear();
        List<String> message = new ArrayList<>();
        List<Appointment> appointmentsForTomorrow = appointmentRepository.
                findAppointmentsByDateByActive(true,day,month,year);

        for (Appointment appointment : appointmentsForTomorrow) {
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

            message.add("¡Buenos días! Nos comunicamos desde Calme para recordarte que "
                    + firstName + " " + lastName + " tiene un turno el día " +
                    tomorrow.getDayOfMonth() + "/" + tomorrow.getMonthValue() +
                    "/" + tomorrow.getYear() + " a las " + appointment.getDate().getHour() + ":" +
                    appointment.getDate().getMinute() + "hs con el/la dr/a " +
                    appointment.getDoctor().getFirstName() + " " +
                    appointment.getDoctor().getLastName() +
                    ", " + appointment.getDoctor().getSpecialty() + ".");
        }

        System.out.println("CREATEMESSAGE : " + message);
        return message.toString();}
    }

