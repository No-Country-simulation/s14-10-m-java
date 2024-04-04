package com.AppTurnos.s1410.Domain.Services;

import com.AppTurnos.s1410.Domain.Dtos.request.RequestCreateAssistent;
import com.AppTurnos.s1410.Domain.Dtos.request.RequestEditAssistent;
import com.AppTurnos.s1410.Domain.Dtos.response.ResponseAssistent;

import java.util.List;

public interface AssistentService {

    ResponseAssistent createAssistent(RequestCreateAssistent requestCreateAssistent);
    ResponseAssistent readAssistent(Long id);
    List<ResponseAssistent> readAllAsistents();
    ResponseAssistent updateAssisten(RequestEditAssistent requestEditAssistent);
    Boolean deleteAssistent(Long id);
}
