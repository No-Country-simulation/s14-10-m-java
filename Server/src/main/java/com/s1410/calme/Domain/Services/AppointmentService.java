package com.s1410.calme.Domain.Services;

import com.s1410.calme.Domain.Dtos.request.RequestAppointmentDate;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAppointment;
import com.s1410.calme.Domain.Dtos.request.RequestAppointmentBetweenDates;
import com.s1410.calme.Domain.Dtos.request.RequestEditAppointmentDate;
import com.s1410.calme.Domain.Dtos.response.ResponseAppointment;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;


public interface AppointmentService {

    ResponseEntity<ResponseAppointment> createAppointment(RequestCreateAppointment requestCreateAppointment);
    //TODO: pasar getAllAppointments a un Pageable
    ResponseEntity<List<ResponseAppointment>> getAllAppointments(Integer page, Boolean active);
    ResponseEntity<ResponseAppointment> getAppointmentById(Long id);

    ResponseEntity<ResponseAppointment> changeAppointmentActiveValue(Long id);

    ResponseEntity<List<ResponseAppointment>> getAppointmentsBetweenDates(RequestAppointmentBetweenDates dates, Integer page, Boolean active);

    ResponseEntity<ResponseAppointment> updateAppointmentDate(RequestEditAppointmentDate updatedDate, Long id);

    ResponseEntity<List<ResponseAppointment>> getAppointmentByDoctorID(Long id, Boolean active);

    ResponseEntity<List<ResponseAppointment>> getAppointmentByAssistentID(Long id, Boolean active, Integer page);

    ResponseEntity<List<ResponseAppointment>> getAppointmentsByDate(RequestAppointmentDate date, Boolean active);

    ResponseEntity<List<ResponseAppointment>> getAppointmentByAssistedId(Long id, Boolean active, Integer page);

    boolean isDoctorBusyAssistent(Long doctorID, Long assistentId, LocalDateTime date);

    boolean isDoctorBusyAssisted(Long doctorId, Long assistedId, LocalDateTime date);

    //TODO: Implement Methods
    //Delete Appointment
    //DeActivate Appointment

}
