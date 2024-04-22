package com.s1410.calme.Infrastructure.Controllers;

import com.s1410.calme.Domain.Dtos.request.RequestAppointmentDate;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAppointment;
import com.s1410.calme.Domain.Dtos.request.RequestAppointmentBetweenDates;
import com.s1410.calme.Domain.Dtos.request.RequestEditAppointmentDate;
import com.s1410.calme.Domain.Dtos.response.ResponseAppointment;
import com.s1410.calme.Domain.Services.AppointmentService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/betweenDates")
    public ResponseEntity<List<ResponseAppointment>> getAppointmentsBetweenDates(@RequestBody RequestAppointmentBetweenDates dates,
                                                                                 @RequestParam Integer page,
                                                                                 @RequestParam Boolean active){
        return appointmentService.getAppointmentsBetweenDates(dates, page, active);
    }

    /*@PutMapping("/{id}/date")
    public ResponseEntity<ResponseAppointment> updateAppointmentDate(@RequestBody RequestEditAppointmentDate updatedDate,
                                                                     @PathVariable Long id){
        return appointmentService.updateAppointmentDate(updatedDate, id);
    }*/

    @GetMapping("/doctor/{id}")
    public  ResponseEntity<List<ResponseAppointment>> getDoctorAppointments(@RequestParam Boolean active, @PathVariable Long id){
        return appointmentService.getAppointmentByDoctorID(id, active);
    }

    @GetMapping("/assistent/{id}")
    public  ResponseEntity<List<ResponseAppointment>> getAssistentAppointments(@RequestParam Integer page, @RequestParam Boolean active, @PathVariable Long id){
        return appointmentService.getAppointmentByAssistentID(id, active, page);
    }

    @GetMapping("/assisted/{id}")
    public  ResponseEntity<List<ResponseAppointment>> getAssistedApponintments(@RequestParam Integer page, @RequestParam Boolean active, @PathVariable Long id){
        return appointmentService.getAppointmentByAssistedId(id, active, page);
    }

    @GetMapping("/date/{active}")
    public ResponseEntity<List<ResponseAppointment>> getAppointmentByDate(
            @RequestBody RequestAppointmentDate date,
            @PathVariable Boolean active){
        return appointmentService.getAppointmentsByDate(date, active);
    }
}
