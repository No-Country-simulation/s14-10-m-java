package com.s1410.calme.Infrastructure.Controllers;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAppointment;
import com.s1410.calme.Domain.Dtos.response.ResponseAppointment;
import com.s1410.calme.Domain.Services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getAllAppointments(){
        return null;
    }
}
