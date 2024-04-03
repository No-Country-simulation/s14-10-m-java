package com.AppTurnos.s1410.Domain.Mapper;


import com.AppTurnos.s1410.Domain.Dtos.request.RequestCreateAssisted;
import com.AppTurnos.s1410.Domain.Dtos.request.RequestEditAssisted;
import com.AppTurnos.s1410.Domain.Dtos.response.ResponseAssisted;
import com.AppTurnos.s1410.Domain.Entities.Assisted;
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
