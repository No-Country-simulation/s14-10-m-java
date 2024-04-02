package com.AppTurnos.s1410.Domain.Mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.AppTurnos.s1410.Domain.Dtos.request.RequestCreateAssistent;
import com.AppTurnos.s1410.Domain.Dtos.request.RequestEditAssistent;
import com.AppTurnos.s1410.Domain.Dtos.response.ResponseAssistent;
import com.AppTurnos.s1410.Domain.Entities.Assistent;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AssistentMapper {
   /*
   * RequestCreateAssistent to Assistent
   * RequestEditAssistent to Assistent
   * Assistent to ResponseAssistent
   * */
    Assistent requestCreateToAssistent (RequestCreateAssistent requestCreateAssistent);
    Assistent requestEditToAssistent (RequestEditAssistent requestEditAssistent);
    ResponseAssistent assistentToResponse (Assistent assistent);

}
