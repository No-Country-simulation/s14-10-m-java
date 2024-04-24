package com.s1410.calme.Domain.Services;
import com.s1410.calme.Domain.Dtos.request.RequestCreateDoctor;
import com.s1410.calme.Domain.Dtos.request.RequestEditDoctor;
import com.s1410.calme.Domain.Dtos.response.ResponseDoctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DoctorService {

    ResponseDoctor createDoctor(RequestCreateDoctor requestCreateDoctor) throws Exception;
    ResponseDoctor readDoctor(Long id);
    Page<ResponseDoctor> readAllDoctors(boolean active, Pageable paging);
    ResponseDoctor updateDoctor(RequestEditDoctor requestEditDoctor, String tokenUser);
    Boolean toogleDeleteDoctor(Long id, String tokenUser);
    List<ResponseDoctor> readAllDoctorBySpecialty(String specialty);
    Page<ResponseDoctor> readAllDoctorsByAvailability(String availability, Pageable paging);
    List<ResponseDoctor> readAllDoctorsBySamePostalCode(Integer postalCode);
    Page<ResponseDoctor> readAllDoctorsBySurname(Boolean asc, Pageable paging);
}
