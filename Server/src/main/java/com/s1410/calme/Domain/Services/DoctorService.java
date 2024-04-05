package com.s1410.calme.Domain.Services;
import com.s1410.calme.Domain.Dtos.request.RequestCreateDoctor;
import com.s1410.calme.Domain.Dtos.request.RequestEditDoctor;
import com.s1410.calme.Domain.Dtos.response.ResponseDoctor;
import java.util.List;

public interface DoctorService {

    ResponseDoctor createDoctor(RequestCreateDoctor requestCreateDoctor);
    ResponseDoctor readDoctor(Long id);
    List<ResponseDoctor> readAllDoctors();
    ResponseDoctor updateDoctor(RequestEditDoctor requestEditDoctor);
    Boolean deleteDoctor(Long id);
}
