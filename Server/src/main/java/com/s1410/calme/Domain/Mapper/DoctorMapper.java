package com.s1410.calme.Domain.Mapper;
import com.s1410.calme.Domain.Dtos.request.RequestCreateDoctor;
import com.s1410.calme.Domain.Dtos.request.RequestEditDoctor;
import com.s1410.calme.Domain.Dtos.response.ResponseDoctor;
import com.s1410.calme.Domain.Entities.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    Doctor requestCreateToDoctor (RequestCreateDoctor requestCreateDoctor);
    Doctor requestEditToDoctor (RequestEditDoctor requestEditDoctor);
    ResponseDoctor doctorToResponse (Doctor doctor);

}
