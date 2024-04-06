package com.s1410.calme.Application.SImplementations;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAssisted;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssisted;
import com.s1410.calme.Domain.Dtos.response.ResponseAssisted;
import com.s1410.calme.Domain.Mapper.AssistedMapper;
import com.s1410.calme.Domain.Repositories.AssistedRepository;
import com.s1410.calme.Domain.Services.AssistedService;
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

