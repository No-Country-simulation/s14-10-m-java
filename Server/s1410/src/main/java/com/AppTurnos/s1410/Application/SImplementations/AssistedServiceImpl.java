package com.AppTurnos.s1410.Application.SImplementations;

import com.AppTurnos.s1410.Domain.Dtos.request.RequestCreateAssisted;
import com.AppTurnos.s1410.Domain.Dtos.request.RequestEditAssisted;
import com.AppTurnos.s1410.Domain.Dtos.response.ResponseAssisted;
import com.AppTurnos.s1410.Domain.Mapper.AssistedMapper;
import com.AppTurnos.s1410.Domain.Repositories.AssistedRepository;
import com.AppTurnos.s1410.Domain.Services.AssistedService;
import com.AppTurnos.s1410.Domain.Services.AssistentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssistedServiceImpl implements AssistedService {

    private final AssistedMapper assistedMapper;
    private final AssistedRepository assistedRepository;

    @Override
    public ResponseAssisted createAssisted(RequestCreateAssisted requestCreateAssisted) {
        return null;
    }

    @Override
    public ResponseAssisted readAssisted(Long id) {
        return null;
    }

    @Override
    public List<ResponseAssisted> readAllAssisted() {
        return null;
    }

    @Override
    public ResponseAssisted updateAssisted(RequestEditAssisted requestEditAssisted) {
        return null;
    }

    @Override
    public Boolean deleteAssisted(Long id) {
        return null;
    }

}

