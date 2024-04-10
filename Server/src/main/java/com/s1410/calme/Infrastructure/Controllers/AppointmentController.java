package com.s1410.calme.Infrastructure.Controllers;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAppointment;
import com.s1410.calme.Domain.Dtos.request.RequestDateAppointment;
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
    public ResponseEntity<List<ResponseAppointment>> getAllAppointments(@RequestParam Integer page, @RequestParam Boolean active){
        return appointmentService.getAllAppointments(page, active);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAppointment> getAppointmentsById(@PathVariable Long id){
        return appointmentService.getAppointmentById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAppointment> changeAppointmentActiveValue(@PathVariable Long id){
        return appointmentService.changeAppointmentActiveValue(id);
    }

    @GetMapping("/date")
    public ResponseEntity<List<ResponseAppointment>> getAppointmentsBetweenDates(@RequestBody RequestDateAppointment dates, @RequestParam Integer page){
        return appointmentService.getAppointmentsBetweenDates(dates, page);
    }




    /*
    TODO Endpoints:
        - Change Appointment Date (must check if date is available aswell)
        - Filter All GETs by DoctorID, AssistentID, ¿AssistedID?
        - Get All Appointments by Date
        - Get All Appointments between 2 Dates
     */

}
