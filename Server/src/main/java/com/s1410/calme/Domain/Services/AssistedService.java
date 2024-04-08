package com.s1410.calme.Domain.Services;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAssisted;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssisted;
import com.s1410.calme.Domain.Dtos.response.ResponseAssisted;

import java.util.List;

public interface AssistedService {

    ResponseAssisted createAssisted(RequestCreateAssisted requestCreateAssisted);
    ResponseAssisted readAssisted(Long id);
    List<ResponseAssisted> readAllAssistedFromAssistant(Long assistantId);
    ResponseAssisted updateAssisted(RequestEditAssisted requestEditAssisted);
    Boolean unlinkAssistedFromAssistant(Long id);
}
