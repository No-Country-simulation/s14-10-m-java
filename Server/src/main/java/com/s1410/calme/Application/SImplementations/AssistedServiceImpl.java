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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssistedServiceImpl implements AssistedService {

    private final AssistedMapper assistedMapper;
    private final AssistedRepository assistedRepository;
    private final AssistentRepository assistentRepository;
    private final RelationAARepository relationAARepository;

    @Transactional
    @Override
    public ResponseAssisted createAssisted(RequestCreateAssisted requestCreateAssisted) {

        Long assistantId = requestCreateAssisted.AssistantID();

        // Search if the assistant user exists in DB to link with assisted, otherwise launch an exception.
        Assistent assistent = assistentRepository.findById(assistantId).orElseThrow(
                () -> new EntityNotFoundException("Assistant with ID " + assistantId + " not Found."));

        // Search if there is an assisted with the same DNI in db.
        Assisted assisted = this.assistedRepository.findByDNI(requestCreateAssisted.DNI());

        // If it does not exist in DB, initialize with data from the request
        if (assisted == null) {
            //Map DTO request to assisted.
            assisted = this.assistedMapper
                    .requestCreateToAssisted(requestCreateAssisted);

            assisted.setActive(true);
        } else if // If assisted already exists, check if it has a relation with the assistant
        (this.relationAARepository.existsByAssistentIdAndAssistedId(assistent.getId(), assisted.getId())) {
            throw new IllegalArgumentException("Assisted and assistant already have a relation between them");
        }

        // Get relation type value between assisted and assistant.
        RelationType relationType = RelationType.valueOf(requestCreateAssisted.relationTypeWithAssistant());

        // Create relation between the assistant and the new assisted.
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
        Assisted assisted = assistedRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("id"));
        return assistedMapper.assistedToResponse(assisted);
    }

    @Override
    public List<ResponseAssisted> readAllAssistedFromAssistant(Long assistantId) {

        // Get assistant from db.
        Assistent assistant = this.assistentRepository.findById(assistantId)
                .orElseThrow(() -> new EntityNotFoundException("Assistant with ID " + assistantId + " not Found."));

        // Validate if the assistant is active.
        if (!assistant.getActive()) throw new IllegalArgumentException("Assistant with ID " + assistantId + " is inactive.");

        //Set assisted list for response.
        List<ResponseAssisted> responseAssistedsList = new ArrayList<>();
        this.relationAARepository.findAllByAssistentId(assistantId)
                .forEach((RelationAA relation) -> responseAssistedsList
                        .add(this.assistedMapper.assistedToResponse(relation.getAssisted())));

        return responseAssistedsList;

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

    @Transactional
    @Override
    public Boolean unlinkAssistedFromAssistant(Long id) {

        // Get relation between Assisted and Assistant.
        RelationAA relation = this.relationAARepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Relation with id " + id + " not Found."));

        // Delete relation.
        this.relationAARepository.deleteById(id);

        // Verify if the assisted  has relation with other assistant.
        Assisted assisted = relation.getAssisted();
        if (!this.relationAARepository.existsByAssistedId(assisted.getId())) {
            // If the assistant does not have any related assistant, it is inactive
            assisted.setActive(false);
            this.assistedRepository.save(assisted);
        }

        return true;
    }

}
