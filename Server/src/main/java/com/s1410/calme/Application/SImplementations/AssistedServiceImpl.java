package com.s1410.calme.Application.SImplementations;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAssisted;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssisted;
import com.s1410.calme.Domain.Dtos.response.ResponseAssisted;
import com.s1410.calme.Domain.Entities.Assisted;
import com.s1410.calme.Domain.Entities.Assistent;
import com.s1410.calme.Domain.Entities.RelationAA;
import com.s1410.calme.Domain.Mapper.AssistedMapper;
import com.s1410.calme.Domain.Repositories.AssistedRepository;
import com.s1410.calme.Domain.Repositories.AssistentRepository;
import com.s1410.calme.Domain.Repositories.RelationAARepository;
import com.s1410.calme.Domain.Services.AssistedService;
import com.s1410.calme.Domain.Utils.RelationType;
import jakarta.persistence.EntityNotFoundException;
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
    private final RelationAARepository relationAARepository;

    @Override
    public ResponseAssisted createAssisted(RequestCreateAssisted requestCreateAssisted) {

        // Check that requestCreateAssisted is not null.
        if (requestCreateAssisted == null) {
            throw new EntityNotFoundException("User cannot be null");
        }

        Long assistantId = requestCreateAssisted.AssistantID();

        // Search if the assistant user exists in DB to link with assisted, otherwise launch an exception
        Assistent assistent = assistentRepository.findById(assistantId).orElseThrow(
                () -> new EntityNotFoundException("Assistant with ID " + assistantId + " not Found."));

        //Map DTO request to assisted.
        Assisted assisted = this.assistedMapper
                .requestCreateToAssisted(requestCreateAssisted);

        // Get relation type value between assisted and assistant.
        RelationType relationType = RelationType.valueOf(requestCreateAssisted.relationTypeWithAssistant());

        // Create relation between the assistant and the new assisted
        RelationAA relationAA = new RelationAA(
                null,
                assisted,
                assistent,
                relationType
                );


        this.assistedRepository.save(assisted);
        this.relationAARepository.save(relationAA);

        return this.assistedMapper.assistedToResponse(assisted);
    }

    @Override
    public ResponseAssisted readAssisted(Long id) {
        return null;
    }

    @Override
    public List<ResponseAssisted> readAllAssisted() {
        return null;
    }

    @Transactional
    @Override
    public ResponseAssisted updateAssisted(RequestEditAssisted requestEditAssisted) {

        Assisted assisted = this.assistedRepository.findById(requestEditAssisted.id())
                .orElseThrow(() -> new EntityNotFoundException(requestEditAssisted.id().toString()));

       // if (assisted.getActive()) {
            if (requestEditAssisted.DNI() != null) {
                assisted.setDNI(requestEditAssisted.DNI());
            }

            if (requestEditAssisted.dateOfBirth() != null) {
                assisted.setDateOfBirth(requestEditAssisted.dateOfBirth());
            }

            this.assistedRepository.save(assisted);
       // }
        return assistedMapper.assistedToResponse(assisted);
    }

    @Override
    public Boolean deleteAssisted(Long id) {
        return null;
    }

}
