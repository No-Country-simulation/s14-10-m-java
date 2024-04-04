package com.s1410.calme.Domain.Services;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAssistent;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssistent;
import com.s1410.calme.Domain.Dtos.response.ResponseAssistent;

import java.util.List;

public interface AssistentService {

    ResponseAssistent createAssistent(RequestCreateAssistent requestCreateAssistent);
    ResponseAssistent readAssistent(Long id);
    List<ResponseAssistent> readAllAsistents();
    ResponseAssistent updateAssisten(RequestEditAssistent requestEditAssistent);
    Boolean deleteAssistent(Long id);
}
