package com.s1410.calme.Domain.Services;
import com.s1410.calme.Domain.Dtos.request.RequestCreateDoctor;
import com.s1410.calme.Domain.Dtos.request.RequestEditDoctor;
import com.s1410.calme.Domain.Dtos.response.ResponseDoctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DoctorService {

    ResponseDoctor createDoctor(RequestCreateDoctor requestCreateDoctor);
    ResponseDoctor readDoctor(Long id);
    Page<ResponseDoctor> readAllDoctors(boolean active, Pageable paging);
    ResponseDoctor updateDoctor(RequestEditDoctor requestEditDoctor);
    Boolean toogleDeleteDoctor(Long id);

    Page<ResponseDoctor> readAllDoctorBySpecialty(String specialty, Pageable paging);

    Page<ResponseDoctor> readAllDoctorsByAvailability(String availability, Pageable paging);

    Page<ResponseDoctor> readAllDoctorsBySamePostalCode(int postalCode, Pageable pageable);
}
