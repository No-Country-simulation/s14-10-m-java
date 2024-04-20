package com.s1410.calme.Application.SImplementations;
import com.s1410.calme.Application.Security.JwtService;
import com.s1410.calme.Domain.Dtos.request.RequestCreateDoctor;
import com.s1410.calme.Domain.Dtos.request.RequestEditDoctor;
import com.s1410.calme.Domain.Dtos.response.ResponseDoctor;
import com.s1410.calme.Domain.Entities.Assistent;
import com.s1410.calme.Domain.Entities.Doctor;
import com.s1410.calme.Domain.Mapper.DoctorMapper;
import com.s1410.calme.Domain.Repositories.DoctorRepository;
import com.s1410.calme.Domain.Services.DoctorService;
import com.s1410.calme.Domain.Utils.Specialty;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    //Create doctor
    @Transactional
    @Override
    public ResponseDoctor createDoctor(RequestCreateDoctor requestCreateDoctor) {

    if (requestCreateDoctor == null){throw new EntityNotFoundException();}

        var doctorAlreadyExists = doctorRepository.findByEmail(requestCreateDoctor.email());
        if(doctorAlreadyExists.isPresent()){ throw new EntityExistsException("Email already in use"); }

    Doctor doctor = doctorMapper.requestCreateToDoctor(requestCreateDoctor);
        doctor.setPassword(passwordEncoder.encode(requestCreateDoctor.password()));
    doctor.setActive(true);
    doctorRepository.save(doctor);

        return doctorMapper.doctorToResponse(doctor);
    }

    @Override
    public ResponseDoctor readDoctor(Long id) {

        Doctor doctor = doctorRepository.findById(id).orElseThrow( ()->
                new EntityNotFoundException("The doctor with id: "+ id+" was not found"));
        return doctorMapper.doctorToResponse(doctor);
    }

    @Override
    public Page<ResponseDoctor> readAllDoctors(boolean active,Pageable paging) {
        return doctorRepository.findAllByActive(active,paging)
                .map(doctorMapper::doctorToResponse);
    }

    @Override
    public ResponseDoctor updateDoctor(RequestEditDoctor requestEditDoctor,String tokenUser) {
        String email = jwtService.getUsernameFromToken(tokenUser.substring(7));

        Doctor doctor = doctorRepository.findById(requestEditDoctor.id()).orElseThrow(()->
                new EntityNotFoundException(requestEditDoctor.id().toString()));

        if (!email.equals(doctor.getEmail())) { throw new IllegalArgumentException(
                "Logged user cannot edit this user!"); }

        if (doctor.getActive()){
            if (requestEditDoctor.firstName() != null) {
                doctor.setFirstName(requestEditDoctor.firstName());
            }
            if (requestEditDoctor.secondName() != null) {
                doctor.setSecondName(requestEditDoctor.secondName());
            }
            if (requestEditDoctor.lastName() != null) {
                doctor.setLastName(requestEditDoctor.lastName());
            }
            if (requestEditDoctor.DNI() != null) {
                doctor.setDNI(requestEditDoctor.DNI());
            }
            if (requestEditDoctor.dateOfBirth() != null) {
                doctor.setDateOfBirth(requestEditDoctor.dateOfBirth());
            }
            if (requestEditDoctor.specialty() != null){
                doctor.setSpecialty(requestEditDoctor.specialty());
            }
            if (requestEditDoctor.phoneNumber() != null){
                doctor.setPhoneNumber(requestEditDoctor.phoneNumber());
            }
            if (requestEditDoctor.morning() != null){
                doctor.setMorning(requestEditDoctor.morning());
            }
            if (requestEditDoctor.afternoon() != null){
                doctor.setAfternoon(requestEditDoctor.afternoon());
            }
            if (requestEditDoctor.night() != null){
                doctor.setNight(requestEditDoctor.night());
            }
        }
         var doctorAdded = doctorRepository.save(doctor);
        return doctorMapper.doctorToResponse(doctorAdded);
    }

    @Override
    public Boolean toogleDeleteDoctor(Long id, String tokenUser) {
        String email = jwtService.getUsernameFromToken(tokenUser.substring(7));

        Doctor doctor = doctorRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Could not find the doctor with id: "+id));

        if (!email.equals(doctor.getEmail())) { throw new IllegalArgumentException(
                "Logged user cannot edit this user!"); }

        doctor.setActive(!doctor.getActive());
        return doctor.getActive();
    }
    @Override
    public List<ResponseDoctor> readAllDoctorBySpecialty (String specialty){
        return doctorRepository.findBySpecialty(Specialty.valueOf(specialty))
                .stream()
                .map(doctorMapper::doctorToResponse).collect(Collectors.toList());
    }

    @Override
    public Page<ResponseDoctor> readAllDoctorsByAvailability(String availability, Pageable paging) {

        if (availability != null && !availability.isEmpty()){
            return switch (availability) {
                case "morning" -> doctorRepository.findByMorningAvailabilityAndEnabled(paging)
                        .map(doctorMapper::doctorToResponse);
                case "afternoon" -> doctorRepository.findByAfternoonAvailabilityAndEnabled(paging)
                        .map(doctorMapper::doctorToResponse);
                case "night" -> doctorRepository.findByNightAvailabilityAndEnabled(paging)
                        .map(doctorMapper::doctorToResponse);
                default -> throw new IllegalStateException("Unexpected value: " + availability);
            };
        }else {
            return doctorRepository.findAllByActive(true,paging).map(doctorMapper::doctorToResponse);
        }

    }

    @Override
    public List<ResponseDoctor> readAllDoctorsBySamePostalCode (Integer postalCode){
        return doctorRepository.readAllDoctorsBySamePostalCode(postalCode)
                .stream()
                .map(doctorMapper::doctorToResponse).collect(Collectors.toList());
    }

    @Override
    public Page<ResponseDoctor> readAllDoctorsBySurname(Boolean asc, Pageable paging) {
        if (asc) {
            return doctorRepository.findBySurnameAsc(paging)
                    .map(doctorMapper::doctorToResponse);
        } else {
            return doctorRepository.findBySurnameDesc(paging)
                    .map(doctorMapper::doctorToResponse);
        }
    }

}
