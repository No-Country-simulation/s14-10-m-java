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

    public ResponseWhatsapp sendMessageTest(String message) throws JsonProcessingException {


        RequestMessage request = new RequestMessage(
                "whatsapp","543517707973 ",
                new RequestMessageText(message.toString()));


        String response = clientBuilder().post()
                .uri("")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(String.class);

        ObjectMapper obj = new ObjectMapper();
        return obj.readValue(response,ResponseWhatsapp.class);
    }

    public List<ResponseWhatsapp> createMessageTest() throws JsonProcessingException {

        LocalDateTime tomorrow = LocalDateTime.now().plusDays(2);
        int day = tomorrow.getDayOfMonth();
        int month = tomorrow.getMonthValue();
        int year = tomorrow.getYear();
        List<ResponseWhatsapp> message = new ArrayList<>();
        List<Appointment> appointmentsForTomorrow = appointmentRepository.
                findAppointmentsByDateByActive(true,day,month,year);

        for (Appointment appointment : appointmentsForTomorrow) {

            String doctorName = appointment.getDoctor().getFirstName();
            String doctorLastName = appointment.getDoctor().getLastName();
            String doctorSpecialty = appointment.getDoctor().getSpecialty().toString();
            int apDay = appointment.getDate().getDayOfMonth();
            int apMonth = appointment.getDate().getMonthValue();
            int apYear = appointment.getDate().getYear();
            int apHour = appointment.getDate().getHour();
            int apMinutes = appointment.getDate().getMinute();

            String firstName;
            String lastName;
            Long phone;
            if (appointment.getAssistent() != null) {
                firstName = appointment.getAssistent().getFirstName();
                lastName = appointment.getAssistent().getFirstName();
                phone = appointment.getAssistent().getPhoneNumber();}
            else { firstName = appointment.getAssisted().getFirstName();
                    lastName = appointment.getAssisted().getFirstName();
                phone = appointment.getAssisted().getRelationsAA().get(0)
                        .getAssistent().getPhoneNumber(); }

            String messagePure =
                    "¡Buenos días! Nos comunicamos desde Calme para recordarte que " + firstName + " "
                    + lastName + " tiene un turno el día " + apDay + "/" + apMonth + "/" + apYear +
                    " a las " + apHour + ":" + apMinutes +  "hs con el/la dr/a " + doctorName + " " +
                    doctorLastName + ", " + doctorSpecialty + ".";

            ResponseWhatsapp response = sendMessageTest(messagePure);
            message.add(response);


        }

        return message;
    }
}

