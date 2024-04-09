package com.s1410.calme.Infrastructure.Controllers;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAppointment;
import com.s1410.calme.Domain.Dtos.response.ResponseAppointment;
import com.s1410.calme.Domain.Services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/")
    public ResponseEntity<ResponseAppointment> createAppointment(@RequestBody RequestCreateAppointment requestCreateAppointment){
        return appointmentService.createAppointment(requestCreateAppointment);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ResponseAppointment>> getAllAppointments(@RequestParam Integer page){
        return appointmentService.getAllAppointments(page);
    }

    @GetMapping("/all/active")
    public ResponseEntity<List<ResponseAppointment>> getAllActiveAppointments(@RequestParam Integer page){
        return null;
    }



    /*
    TODO Endpoints:
        - DeActivate Appointment
        - Activate Appointment
        - Change Appointment Date
        - Filter All GETs by DoctorID, AssistentID, ¿AssistedID?
        - Get All Appointments by Date
        - Get All Appointments between 2 Dates
        -
     */

}
