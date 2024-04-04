package com.s1410.calme.Domain.Mapper;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAssistent;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssistent;
import com.s1410.calme.Domain.Dtos.response.ResponseAssistent;
import com.s1410.calme.Domain.Entities.Assistent;
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
