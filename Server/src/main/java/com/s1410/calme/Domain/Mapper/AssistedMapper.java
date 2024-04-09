package com.s1410.calme.Domain.Mapper;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAssisted;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssisted;
import com.s1410.calme.Domain.Dtos.response.ResponseAssisted;
import com.s1410.calme.Domain.Entities.Assisted;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AssistedMapper {

    Assisted requestCreateToAssisted (RequestCreateAssisted requestCreateAssisted);
    Assisted requestEditToAssisted (RequestEditAssisted requestCreateEditAssisted);
    ResponseAssisted assistedToResponse (Assisted assisted);
    List<ResponseAssisted> assistedListToResponseList (List<Assisted> assistedList);
}
