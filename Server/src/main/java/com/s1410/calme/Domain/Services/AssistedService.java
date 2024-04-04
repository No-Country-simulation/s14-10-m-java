package com.s1410.calme.Domain.Services;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAssisted;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssisted;
import com.s1410.calme.Domain.Dtos.response.ResponseAssisted;

import java.util.List;

public interface AssistedService {

    ResponseAssisted createAssisted(RequestCreateAssisted requestCreateAssisted);
    ResponseAssisted readAssisted(Long id);
    List<ResponseAssisted> readAllAssisted();
    ResponseAssisted updateAssisted(RequestEditAssisted requestEditAssisted);
    Boolean deleteAssisted(Long id);
}
