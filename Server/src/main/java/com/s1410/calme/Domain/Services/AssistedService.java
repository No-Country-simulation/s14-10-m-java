package com.s1410.calme.Domain.Services;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAssisted;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssisted;
import com.s1410.calme.Domain.Dtos.response.ResponseAssisted;
import com.s1410.calme.Domain.Utils.RelationType;

import java.util.List;

public interface AssistedService {

    ResponseAssisted createAssisted(RequestCreateAssisted requestCreateAssisted);
    ResponseAssisted readAssisted(Long id);
    List<ResponseAssisted> readAllAssistedFromAssistant(Long assistantId);
    ResponseAssisted updateAssisted(RequestEditAssisted requestEditAssisted);
    boolean updateRelationAA(Long assistantId, Long assistedId, RelationType relationType);
    Boolean unlinkAssistedFromAssistant(Long assistantId, Long assistedId );
}
