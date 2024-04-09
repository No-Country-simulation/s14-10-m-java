package com.s1410.calme.Application.SImplementations;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAppointment;
import com.s1410.calme.Domain.Dtos.response.ResponseAppointment;
import com.s1410.calme.Domain.Entities.Appointment;
import com.s1410.calme.Domain.Entities.Assisted;
import com.s1410.calme.Domain.Entities.Assistent;
import com.s1410.calme.Domain.Entities.Doctor;
import com.s1410.calme.Domain.Mapper.AppointmentMapper;
import com.s1410.calme.Domain.Repositories.AppointmentRepository;
import com.s1410.calme.Domain.Repositories.AssistedRepository;
import com.s1410.calme.Domain.Repositories.AssistentRepository;
import com.s1410.calme.Domain.Repositories.DoctorRepository;
import com.s1410.calme.Domain.Services.AppointmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final AssistedRepository assistedRepository;
    private final AssistentRepository assistentRepository;
    private final DoctorRepository doctorRepository;


    //TODO: Hacer el código más lindo si es posible
    //TODO: Chequear que la fecha del appointment sea mayor a la actual
    @Override
    public ResponseEntity<ResponseAppointment> createAppointment(RequestCreateAppointment requestCreateAppointment) {
        Long doctorId = requestCreateAppointment.doctorId();
        Long assistentId = requestCreateAppointment.assistentId();
        Long assistedId = requestCreateAppointment.assistedId();

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(
                        () -> new EntityNotFoundException("No Doctor found with id: " + doctorId)
                );

        Assistent assistent = assistentRepository.findById(assistentId)
                .orElseThrow(
                        () -> new EntityNotFoundException("No Assistent found with id: " + assistentId)
                );

        Assisted assisted = assistedRepository.findById(assistedId)
                .orElseThrow(
                        () -> new EntityNotFoundException("No Assisted found with id: " + assistedId)
                );

        Appointment appointment = new Appointment();
        appointment.setActive(true);
        appointment.setDate(requestCreateAppointment.date());
        appointment.setObservations(requestCreateAppointment.observations());
        appointment.setDoctor(doctor);
        appointment.setAssistent(assistent);
        appointment.setAssisted(assisted);

        var appointmentSaved = appointmentRepository.save(appointment);
        ResponseAppointment responseAppointment = appointmentMapper.appointmentToResponse(appointmentSaved);
        return new ResponseEntity<>(responseAppointment, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ResponseAppointment>> getAllAppointments(Integer page) {

        Integer size = 2;

        //Default Page Number for wrong inputs
        if (page <= 0) page = 1;

        Pageable pageable = PageRequest.of(page-1, size);
        Page<Appointment> pageAppointment = appointmentRepository.findAll(pageable);
        return new ResponseEntity<>(pageAppointment.getContent()
                .stream().map(appointmentMapper::appointmentToResponse).collect(Collectors.toList()),
                HttpStatus.OK);
    }
}
