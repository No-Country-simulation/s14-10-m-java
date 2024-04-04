package com.AppTurnos.s1410.Domain.Services;

import com.AppTurnos.s1410.Domain.Dtos.request.RequestCreateAssisted;
import com.AppTurnos.s1410.Domain.Dtos.request.RequestEditAssisted;
import com.AppTurnos.s1410.Domain.Dtos.response.ResponseAssisted;

import java.util.List;

public interface AssistedService {

    ResponseAssisted createAssisted(RequestCreateAssisted requestCreateAssisted);
    ResponseAssisted readAssisted(Long id);
    List<ResponseAssisted> readAllAssisted();
    ResponseAssisted updateAssisted(RequestEditAssisted requestEditAssisted);
    Boolean deleteAssisted(Long id);
}
