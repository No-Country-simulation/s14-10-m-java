package com.s1410.calme.Domain.Services;
import com.s1410.calme.Domain.Dtos.request.RequestCreateDoctor;
import com.s1410.calme.Domain.Dtos.request.RequestEditDoctor;
import com.s1410.calme.Domain.Dtos.response.ResponseDoctor;

import java.awt.print.Pageable;
import java.util.List;

public interface DoctorService {

    ResponseDoctor createDoctor(RequestCreateDoctor requestCreateDoctor);
    ResponseDoctor readDoctor(Long id);

    List<ResponseDoctor> readAllDoctors(boolean active, Pageable paging);

    ResponseDoctor updateDoctor(RequestEditDoctor requestEditDoctor);
    Boolean deleteDoctor(Long id);
}
