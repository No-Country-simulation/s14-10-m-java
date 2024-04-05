package com.s1410.calme.Application.SImplementations;
import com.s1410.calme.Domain.Dtos.request.RequestCreateDoctor;
import com.s1410.calme.Domain.Dtos.request.RequestEditDoctor;
import com.s1410.calme.Domain.Dtos.response.ResponseDoctor;
import com.s1410.calme.Domain.Mapper.DoctorMapper;
import com.s1410.calme.Domain.Repositories.DoctorRepository;
import com.s1410.calme.Domain.Services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;

    @Transactional
    @Override
    public ResponseDoctor createDoctor(RequestCreateDoctor requestCreateDoctor) {
        return null;
    }

    @Override
    public ResponseDoctor readDoctor(Long id) {
        return null;
    }

    @Override
    public List<ResponseDoctor> readAllDoctors() {
        return null;
    }

    @Override
    public ResponseDoctor updateDoctor(RequestEditDoctor requestEditDoctor) {
        return null;
    }

    @Override
    public Boolean deleteDoctor(Long id) {
        return null;
    }
}
