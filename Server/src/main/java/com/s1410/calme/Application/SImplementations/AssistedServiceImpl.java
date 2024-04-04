package com.s1410.calme.Application.SImplementations;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAssisted;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssisted;
import com.s1410.calme.Domain.Dtos.response.ResponseAssisted;
import com.s1410.calme.Domain.Entities.Assisted;
import com.s1410.calme.Domain.Mapper.AssistedMapper;
import com.s1410.calme.Domain.Repositories.AssistedRepository;
import com.s1410.calme.Domain.Repositories.AssistentRepository;
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

    private final AssistentRepository assistentRepository;

    @Override
    public ResponseAssisted createAssisted(RequestCreateAssisted requestCreateAssisted) {
        // Check that requestCreateAssisted is not null.
        if (requestCreateAssisted == null) {
            throw new RuntimeException("User cannot be null");
        }

        // Search if the assistant user exists in DB to link with assisted, otherwise launch an exception
        /* Assistent assistent = assistentRepository.findById(assistantID).orElseThrow(
                () -> new RuntimeException("Assistant with ID " + assistantID + " not Found.")
        );
        */

        Assisted assisted = this.assistedMapper
                .requestCreateToAssisted(requestCreateAssisted);

        /*
        Assisted existingAssisted = assistedRepository.findByDNI(assisted.getDNI());
        if (existingAssisted == null) {
            // CREAR
            // VINCULAR
        } else {
            // SOLO VINCULAR
        }
*/
        return this.assistedMapper.assistedToResponse(assistedRepository.save(assisted));
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

