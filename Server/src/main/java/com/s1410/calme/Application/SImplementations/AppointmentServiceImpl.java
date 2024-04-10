package com.s1410.calme.Application.SImplementations;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAppointment;
import com.s1410.calme.Domain.Dtos.request.RequestDateAppointment;
import com.s1410.calme.Domain.Dtos.request.RequestEditAppointmentDate;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private final Integer DEFAULT_PAGE_SIZE = 5;


    //TODO: Hacer el código más lindo si es posible y necesario
    //TODO: Chequear que la fecha del appointment sea mayor a la actual
    //TODO: Assisted puede ser nulo
    //TODO: Validaciones de turnos para assistent y doctor:
    //  - El turno empieza 30 minutos después del último
    //  - El turno empieza 30 minutos antes del siguiente
    //TODO: Chequear que las fechas correspondan con los horarios laborales de los doctores
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
    public ResponseEntity<List<ResponseAppointment>> getAllAppointments(Integer page, Boolean active) {


        //Default Page Number for wrong inputs
        if (page <= 0) page = 1;

        Pageable pageable = PageRequest.of(page-1, DEFAULT_PAGE_SIZE);

        Page<Appointment> pageAppointment = appointmentRepository.findAppointmentsByActivePageable(active, pageable);

        return new ResponseEntity<>(pageAppointment.getContent()
                .stream().map(appointmentMapper::appointmentToResponse).
                collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAppointment> getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Appointment found with id: " + id));
        ResponseAppointment response = appointmentMapper.appointmentToResponse(appointment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAppointment> changeAppointmentActiveValue(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Appointment found with id: " + id));

        //Change active value to opposite
        appointment.setActive(!appointment.getActive());

        Appointment stored = appointmentRepository.save(appointment);

        ResponseAppointment response = appointmentMapper.appointmentToResponse(stored);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ResponseAppointment>> getAppointmentsBetweenDates(RequestDateAppointment dates, Integer page, Boolean active) {

        if (page <= 0) page = 1;

        Pageable pageable = PageRequest.of(page-1, DEFAULT_PAGE_SIZE);

        LocalDateTime startDate = dates.startDate();
        LocalDateTime finishDate = dates.finishDate();

        Page<Appointment> pageAppointment = appointmentRepository.findAppointmentsBetweenDates(active, startDate, finishDate, pageable);

        return new ResponseEntity<>(pageAppointment.getContent()
                .stream().map(appointmentMapper::appointmentToResponse).
                collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseAppointment> updateAppointmentDate(RequestEditAppointmentDate updatedDate, Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No Appointment found with id: " + id));

        appointment.setDate(updatedDate.newDate());

        Appointment stored = appointmentRepository.save(appointment);

        ResponseAppointment response = appointmentMapper.appointmentToResponse(stored);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
