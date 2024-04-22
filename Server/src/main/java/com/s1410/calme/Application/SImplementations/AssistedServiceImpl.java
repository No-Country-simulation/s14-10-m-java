package com.s1410.calme.Application.SImplementations;

import com.s1410.calme.Application.Config.Validations.RoleValidation;
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
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AssistedServiceImpl implements AssistedService {

    private final AssistedMapper assistedMapper;
    private final AssistedRepository assistedRepository;
    private final AssistentRepository assistentRepository;
    private final RelationAARepository relationAARepository;
    private final RoleValidation roleValidation;

    @Transactional
    @Override //Assistent role needed.
    public ResponseAssisted createAssisted(RequestCreateAssisted requestCreateAssisted) {

        roleValidation.checkAssistentRole();
        Long assistantId = requestCreateAssisted.AssistantID();

        // Search if the assistant user exists in DB to link with assisted, otherwise launch an exception.
        Assistent assistent = assistentRepository.findById(assistantId).orElseThrow(
                () -> new EntityNotFoundException("Assistant with ID " + assistantId + " not Found."));

        // Search if there is an assisted with the same DNI in db.
        // If an assisted with the same DNI is not found, one is initialized with the request data.
        Assisted assisted = this.assistedRepository.findByDNI(requestCreateAssisted.DNI())
                .orElse(this.assistedMapper
                        .requestCreateToAssisted(requestCreateAssisted));

        // Search if a relation exists between the assisted and the assistant in the db.
        RelationAA relationAA = this.relationAARepository
                .findByAssistentIdAndAssistedId(assistent.getId(), assisted.getId()).orElse(null);

        // If the relation exist in db.
        if (relationAA != null) {

                // Check that the relation is deactivated. If it is active, throw an exception
                if (relationAA.getActive()) throw new EntityExistsException("Assistant and Assisted already have an active relation between them");

                // Reactivate the relation between assisted and assistant
                relationAA.setActive(true);

        } else // If the relation does not exist in the database.
        {
                // Get relation type value between assisted and assistant.
                RelationType relationType = RelationType.valueOf(requestCreateAssisted.relationTypeWithAssistant());

                // Create relation between the assistant and the new assisted.
                relationAA = new RelationAA(
                        null,
                        assisted,
                        assistent,
                        relationType,
                        true
                );
            }

        assisted.setActive(true);

        this.relationAARepository.save(relationAA);
        this.assistedRepository.save(assisted);

        return this.assistedMapper.assistedToResponse(assisted);
    }

    @Override
    public ResponseAssisted readAssisted(Long id) {
        Assisted assisted = assistedRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("id"));
        return assistedMapper.assistedToResponse(assisted);
    }

    @Override
    public Page<ResponseAssisted> readAllAssistedFromAssistant(Long assistantId, Pageable pageable, Boolean actives) {

        // Get assistant from db.
        Assistent assistant = this.assistentRepository.findById(assistantId)
                .orElseThrow(() -> new EntityNotFoundException("Assistant with ID " + assistantId + " not Found."));

        // Validate if the assistant is active.
        if (!assistant.getActive()) throw new IllegalArgumentException("Assistant with ID " + assistantId + " is inactive.");

        // Get all the assisted from an assistant
        Page<Assisted> allAssistedPage = this.relationAARepository.findByAssistentId(assistantId, pageable);

        // Filter assisted by active (by default) or inactive (parameter condition)
        List<ResponseAssisted> filteredAssisteds = allAssistedPage
                .getContent()
                .stream()
                .filter(assisted -> actives == assisted.getActive())
                .map(assistedMapper::assistedToResponse).toList();

        // Return a Page of assisted with the filter applied
        return new PageImpl<>(filteredAssisteds, pageable, allAssistedPage.getTotalElements());
    }

    @Transactional
    @Override //Assistent role needed.
    public ResponseAssisted updateAssisted(RequestEditAssisted requestEditAssisted) {
        roleValidation.checkAssistentRole();

        Assisted assisted = this.assistedRepository.findById(requestEditAssisted.id())
                .orElseThrow(() -> new EntityNotFoundException(requestEditAssisted.id().toString()));

       if (assisted.getActive()) {
            if (requestEditAssisted.firstName() != null) {
                assisted.setFirstName(requestEditAssisted.firstName());
            }

           if (requestEditAssisted.secondName() != null) {
               assisted.setSecondName(requestEditAssisted.secondName());
           }

           if (requestEditAssisted.lastName() != null) {
               assisted.setLastName(requestEditAssisted.lastName());
           }

           if (requestEditAssisted.DNI() != null) {
               assisted.setDNI(requestEditAssisted.DNI());
           }

            if (requestEditAssisted.dateOfBirth() != null) {
                assisted.setDateOfBirth(requestEditAssisted.dateOfBirth());
            }

            this.assistedRepository.save(assisted);
        }
        return assistedMapper.assistedToResponse(assisted);
    }

    @Transactional
    @Override //Assistent role needed.
    public boolean updateRelationAA(Long assistantId, Long assistedId, RelationType relationType){
        roleValidation.checkAssistentRole();
        Assisted assisted = this.assistedRepository.findById(assistedId)
                .orElseThrow(() -> new EntityNotFoundException(assistedId.toString()));
        Assistent assistant = this.assistentRepository.findById(assistantId)
                .orElseThrow(() -> new EntityNotFoundException(assistantId.toString()));

        var relationAA = relationAARepository.findByAssistentIdAndAssistedId(assistantId, assistedId)
                .orElseThrow(() -> new EntityNotFoundException("La relación no existe"));
        relationAA.setRelationType(relationType);

        relationAARepository.save(relationAA);
        return true;
    }

    @Transactional
    @Override //Assistent role needed.
    public Boolean unlinkAssistedFromAssistant(Long assistantId, Long assistedId) {
        roleValidation.checkAssistentRole();

        // Get relation between Assisted and Assistant.
        RelationAA relation = this.relationAARepository.findByAssistentIdAndAssistedId(assistantId, assistedId)
                .orElseThrow(() -> new EntityNotFoundException("Relation with assistantId " + assistantId + " and assistedId " + assistedId + " not Found."));

        if (!relation.getActive()) throw new EntityExistsException("The relation between assisted and assistant is already inactive.");

        // logical deletion of RelationAA
        relation.setActive(false);
        this.relationAARepository.save(relation);

        Assisted assisted = relation.getAssisted();

        // Verify if the assisted has active relation with other assistant.
        if (assisted.getRelationsAA()
                .stream()
                .filter(r -> !Objects.equals(r.getId(), relation.getId()))
                .noneMatch(RelationAA::getActive)) {

            // If the assisted does not have any active relation with other assistant, it is inactive
            // logical deletion of Assisted
            assisted.setActive(false);
            this.assistedRepository.save(assisted);
        }

        return true;
    }

}
