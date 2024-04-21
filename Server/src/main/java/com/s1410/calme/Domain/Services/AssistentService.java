package com.s1410.calme.Domain.Services;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAssistent;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssistent;
import com.s1410.calme.Domain.Dtos.response.ResponseAssistent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AssistentService {

    ResponseAssistent createAssistent(RequestCreateAssistent requestCreateAssistent) throws Exception;
    ResponseAssistent readAssistent(Long id);
    Page<ResponseAssistent> readAllAsistents(Boolean active, Pageable paging);
    List<ResponseAssistent> readAllAssistentFromAssisted(Long assistedId);
    ResponseAssistent updateAssistent(RequestEditAssistent requestEditAssistent, String tokenUser);
    Boolean toogleDeleteAssistent(Long id, String tokenUser);
}
