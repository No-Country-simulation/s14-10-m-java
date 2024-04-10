package com.s1410.calme.Domain.Mapper;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAssistent;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssistent;
import com.s1410.calme.Domain.Dtos.response.ResponseAssisted;
import com.s1410.calme.Domain.Dtos.response.ResponseAssistent;
import com.s1410.calme.Domain.Entities.Assisted;
import com.s1410.calme.Domain.Entities.Assistent;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AssistentMapper {

    Assistent requestCreateToAssistent (RequestCreateAssistent requestCreateAssistent);
    Assistent requestEditToAssistent (RequestEditAssistent requestEditAssistent);
    ResponseAssistent assistentToResponse (Assistent assistent);
    List<ResponseAssistent> assistentListToResponseList (List<Assistent> assistentList);

}
