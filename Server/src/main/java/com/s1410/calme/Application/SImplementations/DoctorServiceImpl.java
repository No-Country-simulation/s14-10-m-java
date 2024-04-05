package com.s1410.calme.Application.SImplementations;
import com.s1410.calme.Domain.Dtos.request.RequestCreateDoctor;
import com.s1410.calme.Domain.Dtos.request.RequestEditDoctor;
import com.s1410.calme.Domain.Dtos.response.ResponseDoctor;
import com.s1410.calme.Domain.Mapper.DoctorMapper;
import com.s1410.calme.Domain.Repositories.DoctorRepository;
import com.s1410.calme.Domain.Services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return null;
    }

    @Override
    public ResponseDoctor readDoctor(Long id) {
        return null;
    }

    @Override
    public Page<ResponseDoctor> readAllDoctors(boolean active,Pageable paging) {
        return null;
    }

    @Override
    public ResponseDoctor updateDoctor(RequestEditDoctor requestEditDoctor) {
        return null;
    }

    @Override
    public Boolean toogleDeleteDoctor(Long id) {
        return null;
    }
}
