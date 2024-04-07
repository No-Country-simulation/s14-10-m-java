package com.s1410.calme.Application.SImplementations;
import com.s1410.calme.Domain.Dtos.request.RequestCreateDoctor;
import com.s1410.calme.Domain.Dtos.request.RequestEditDoctor;
import com.s1410.calme.Domain.Dtos.response.ResponseDoctor;
import com.s1410.calme.Domain.Entities.Doctor;
import com.s1410.calme.Domain.Mapper.DoctorMapper;
import com.s1410.calme.Domain.Repositories.DoctorRepository;
import com.s1410.calme.Domain.Services.DoctorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;

    //Create doctor
    @Transactional
    @Override
    public ResponseDoctor createDoctor(RequestCreateDoctor requestCreateDoctor) {

    if (requestCreateDoctor == null){throw new EntityNotFoundException();}

    Doctor doctor = doctorMapper.requestCreateToDoctor(requestCreateDoctor);
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
    public List<ResponseDoctor> readAllDoctors(boolean active, Pageable paging) {
        return null;
    }

    @Override
    public ResponseDoctor updateDoctor(RequestEditDoctor requestEditDoctor) {
        return null;
    }

    @Override
    public Boolean deleteDoctor(Long id) {

        Doctor doctor = doctorRepository.findById(id).orElseThrow( ()->
                new EntityNotFoundException("The doctor with id: "+ id+" was not found"));

        doctor.setActive(!doctor.getActive());
        doctorRepository.save(doctor);
        return doctor.getActive();
    }
}
