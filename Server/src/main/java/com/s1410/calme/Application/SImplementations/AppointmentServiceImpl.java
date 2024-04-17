package com.s1410.calme.Application.SImplementations;

import com.s1410.calme.Domain.Dtos.request.RequestAppointmentDate;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAppointment;
import com.s1410.calme.Domain.Dtos.request.RequestAppointmentBetweenDates;
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
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final AssistedRepository assistedRepository;
    private final AssistentRepository assistentRepository;
    private final DoctorRepository doctorRepository;

    private final Integer DEFAULT_PAGE_SIZE = 10;


    //TODO: Hacer el código más lindo si es posible y necesario
    //TODO: Chequear que la fecha del appointment sea mayor a la actual
    //TODO: Chequear que las fechas correspondan con los horarios laborales de los doctores
    @Override
    public ResponseEntity<ResponseAppointment> createAppointment(RequestCreateAppointment requestCreateAppointment) {
        Long doctorId = requestCreateAppointment.doctorId();
        LocalDateTime date = requestCreateAppointment.date();
        Long assistentId = 0L;
        Long assistedId = 0L;

        //Saltaba error porque long no podía ser null.
        if (requestCreateAppointment.assistentId() != null) {
            assistentId = requestCreateAppointment.assistentId();
        }
        if (requestCreateAppointment.assistedId() != null) {
            assistedId = requestCreateAppointment.assistedId();
        }

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(
                        () -> new EntityNotFoundException("No Doctor found with id: " + doctorId)
                );

        checkDoctorAvailability(doctorId, date);
        checkAssistentAvailability(assistentId, date);
        checkAssistedAvailability(assistedId, date);

        //TODO: cambiar la respuesta de 404 a badrequest
        //Comprobar si el doctor esta ocupado ese dia
        if(isDoctorBusyAssistent(doctorId, assistentId, date)){
            throw new EntityNotFoundException("The doctor has an appointment already with you on this day. dr id :" + requestCreateAppointment.doctorId());
        } else if (isDoctorBusyAssisted(doctorId, assistedId, date)) {
            throw new EntityNotFoundException("The doctor has an appointment already with you on this day. dr id: " + requestCreateAppointment.doctorId());
        }

        //Corrobora primero cuál de los dos es el que va y sólo da error si no está ninguno.
        Assistent assistent = assistentRepository.findById(assistentId).orElse(null);
        Assisted assisted = assistedRepository.findById(assistedId).orElse(null);
        if (assisted == null && assistent == null) { throw new
                EntityNotFoundException("No se encontró paciente con ese id"); }


        Appointment appointment = new Appointment();
        appointment.setActive(true);
        appointment.setDate(requestCreateAppointment.date());
        appointment.setObservations(requestCreateAppointment.observations());
        appointment.setDoctor(doctor);
        if (assistent != null) {appointment.setAssistent(assistent); }
        if (assisted != null) {appointment.setAssisted(assisted); }

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
    public ResponseEntity<List<ResponseAppointment>> getAppointmentsBetweenDates(RequestAppointmentBetweenDates dates, Integer page, Boolean active) {

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

    @Override
    public ResponseEntity<List<ResponseAppointment>> getAppointmentByDoctorID(Long id, Boolean active, Integer page) {
        if (page <= 0) page = 1;
        Pageable pageable = PageRequest.of(page-1, DEFAULT_PAGE_SIZE);

        Page<Appointment> pageAppointment = appointmentRepository.findAppointmentByDoctorId(id, active, pageable);

        return new ResponseEntity<>(pageAppointment.getContent()
                .stream().map(appointmentMapper::appointmentToResponse).
                collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ResponseAppointment>> getAppointmentByAssistentID(Long id, Boolean active, Integer page) {
        if (page <= 0) page = 1;
        Pageable pageable = PageRequest.of(page-1, DEFAULT_PAGE_SIZE);

        Page<Appointment> pageAppointment = appointmentRepository.findAppointmentByAssistantById(id,active, pageable);
            return new ResponseEntity<>(pageAppointment.getContent()
                    .stream().map(appointmentMapper::appointmentToResponse).
                    collect(Collectors.toList()),
                    HttpStatus.OK);
        }

    @Override
    public ResponseEntity<List<ResponseAppointment>> getAppointmentByAssistedId(Long id, Boolean active, Integer page) {
        if (page <= 0) page = 1;
        Pageable pageable = PageRequest.of(page-1, DEFAULT_PAGE_SIZE);

        Page<Appointment> pageAppointment = appointmentRepository.findAppointmentByAssistedById(id, active ,pageable);
            return new ResponseEntity<>(pageAppointment.getContent()
                    .stream().map(appointmentMapper::appointmentToResponse)
                    .collect(Collectors.toList()),
                    HttpStatus.OK);
    }


    //Metodo para saber si ya el assistent tiene cita con el doctor ese dia
    @Override
    public boolean isDoctorBusyAssistent(Long doctorID, Long assistentId, LocalDateTime date) {
        return appointmentRepository.existsByDoctorAndAssistent(doctorID, assistentId, date);
    }
    //Metodo para saber si ya el assisted tiene cita con el doctor ese dia
    @Override
    public boolean isDoctorBusyAssisted(Long doctorId, Long assistedId, LocalDateTime date) {
        return appointmentRepository.existsByDoctorAndAssisted(doctorId, assistedId, date);
    }


    @Override
    public ResponseEntity<List<ResponseAppointment>> getAppointmentsByDate(
            RequestAppointmentDate date, Boolean active) {
        int day = date.date().getDayOfMonth();
        int month = date.date().getMonthValue();
        int year = date.date().getYear();
        List<Appointment> appointmentList = appointmentRepository
                .findAppointmentsByDateByActive(active, day, month, year);
        List<ResponseAppointment> response = appointmentMapper
                .appointmentListToResponseList(appointmentList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void checkDoctorAvailability(Long doctorId, LocalDateTime dateTime){

        LocalDateTime starDate =  LocalDate.of(dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth()).atStartOfDay();
        LocalDateTime endDate =  LocalDate.of(dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth()).atTime(23,59,59);

        System.out.println("START DATE: " + starDate);
        System.out.println("END DATE: " + endDate);

        Pageable pageable = PageRequest.of(0, 15);

        Page<Appointment> pageApp = appointmentRepository.findAppointmentsByDoctorIdAndDateRange(doctorId, starDate, endDate , true, pageable);

        List<Appointment> appointments = pageApp.getContent();


        for (Appointment appointment: appointments){
            System.out.println(dateTime.until(appointment.getDate(), ChronoUnit.MINUTES));
            long diff = dateTime.until(appointment.getDate(), ChronoUnit.MINUTES);
            if (diff < 30 && diff >= 0 ){
                throw new EntityNotFoundException("The doctor already has this time busy : " + appointment.getObservations());
            }
        }
    }

    private void checkAssistentAvailability(Long assistentId, LocalDateTime dateTime){

        LocalDateTime starDate =  LocalDate.of(dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth()).atStartOfDay();
        LocalDateTime endDate =  LocalDate.of(dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth()).atTime(23,59,59);

        System.out.println("START DATE: " + starDate);
        System.out.println("END DATE: " + endDate);

        Pageable pageable = PageRequest.of(0, 15);

        Page<Appointment> pageApp = appointmentRepository.findAppointmentsByAssistentIdAndDateRange(assistentId, starDate, endDate , true, pageable);

        List<Appointment> appointments = pageApp.getContent();


        for (Appointment appointment: appointments){
            System.out.println(dateTime.until(appointment.getDate(), ChronoUnit.MINUTES));
            long diff = dateTime.until(appointment.getDate(), ChronoUnit.MINUTES);
            if (diff < 30 && diff >= 0 ){
                throw new EntityNotFoundException("You Have an appointment on this hour id : " + appointment.getId());
            }
        }
    }

    private void checkAssistedAvailability(Long assistedId, LocalDateTime dateTime) {

        LocalDateTime starDate = LocalDate.of(dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth()).atStartOfDay();
        LocalDateTime endDate = LocalDate.of(dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth()).atTime(23, 59, 59);

        System.out.println("START DATE: " + starDate);
        System.out.println("END DATE: " + endDate);

        Pageable pageable = PageRequest.of(0, 15);

        Page<Appointment> pageApp = appointmentRepository.findAppointmentsByAssistedIdAndDateRange(assistedId, starDate, endDate, true, pageable);

        List<Appointment> appointments = pageApp.getContent();


        for (Appointment appointment : appointments) {
            System.out.println(dateTime.until(appointment.getDate(), ChronoUnit.MINUTES));
            long diff = dateTime.until(appointment.getDate(), ChronoUnit.MINUTES);
            if (diff < 30 && diff >= 0) {
                throw new EntityNotFoundException("You Have an appointment on this hour  id : "  + appointment.getId());
            }
        }
    }

}
