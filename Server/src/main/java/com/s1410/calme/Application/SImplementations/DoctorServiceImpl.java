package com.s1410.calme.Application.SImplementations;
import com.s1410.calme.Domain.Dtos.request.RequestCreateDoctor;
import com.s1410.calme.Domain.Dtos.request.RequestEditDoctor;
import com.s1410.calme.Domain.Dtos.response.ResponseDoctor;
import com.s1410.calme.Domain.Entities.Doctor;
import com.s1410.calme.Domain.Mapper.DoctorMapper;
import com.s1410.calme.Domain.Repositories.DoctorRepository;
import com.s1410.calme.Domain.Services.DoctorService;
import com.s1410.calme.Domain.Utils.Specialty;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    //Create doctor
    @Transactional
    @Override
    public ResponseDoctor createDoctor(RequestCreateDoctor requestCreateDoctor) {

    if (requestCreateDoctor == null){throw new EntityNotFoundException();}

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
    public ResponseDoctor updateDoctor(RequestEditDoctor requestEditDoctor) {

        Doctor doctor = doctorRepository.findById(requestEditDoctor.id()).orElseThrow(()->
                new EntityNotFoundException(requestEditDoctor.id().toString()));

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

/*            if (requestEditDoctor.availability() != null) {
                doctor.setAvailability(requestEditDoctor.availability());
            }*/
        }

        return doctorMapper.doctorToResponse(doctor);
    }

    @Override
    public Boolean toogleDeleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(
        ()-> new EntityNotFoundException("Could not find the doctor with id: "+id));

        doctor.setActive(!doctor.getActive());
        return doctor.getActive();
    }
    @Override
    public Page<ResponseDoctor> readAllDoctorBySpecialty (String specialty,Pageable paging){
        return doctorRepository.findBySpecialty(Specialty.valueOf(specialty),paging).map(doctorMapper::doctorToResponse);
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
}
