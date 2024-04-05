package com.s1410.calme.Domain.Mapper;


import com.s1410.calme.Domain.Dtos.request.RequestCreateAssisted;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssisted;
import com.s1410.calme.Domain.Dtos.response.ResponseAssisted;
import com.s1410.calme.Domain.Entities.Assisted;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssistedMapper {
    /*
     * RequestCreateAssisted to Assisted
     * RequestEditAssisted to Assisted
     * Assisted to ResponseAssisted
     * */
    Assisted requestCreateToAssisted (RequestCreateAssisted requestCreateAssisted);
    Assisted requestEditToAssisted (RequestEditAssisted requestCreateEditAssisted);
    ResponseAssisted assistedToResponse (Assisted assisted);
}
