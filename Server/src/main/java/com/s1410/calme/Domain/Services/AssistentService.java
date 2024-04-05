package com.s1410.calme.Domain.Services;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAssistent;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssistent;
import com.s1410.calme.Domain.Dtos.response.ResponseAssistent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AssistentService {

    ResponseAssistent createAssistent(RequestCreateAssistent requestCreateAssistent);
    ResponseAssistent readAssistent(Long id);
    Page<ResponseAssistent> readAllAsistents(boolean active, Pageable paging);
    ResponseAssistent updateAssistent(RequestEditAssistent requestEditAssistent);
    Boolean toogleDeleteAssistent(Long id);
}
