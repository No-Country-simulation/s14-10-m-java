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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        assisted.setActive(true);

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

        Assistent assistant = this.assistentRepository.findById(assistantId)
                .orElseThrow(() -> new EntityNotFoundException("Assistant with ID " + assistantId + " not Found."));

        if (!assistant.getActive()) throw new RuntimeException();

        List<ResponseAssisted> responseAssistedsList = new ArrayList<>();

        //Este método está perfecto, abajo agrego otro para utilizar el conversor del mapper.
        /*for (RelationAA relation:
        this.relationAARepository.findAllByAssistentId(assistantId)) {
            responseAssistedsList.add(this.assistedMapper
            .assistedToResponse(relation.getAssisted()));
        }*/

        /*Se espera una lista de respuesta de assisted. Se mapea a través del mapstruct
        entre ambas listas buscando en el repo de relaciónAA todas las relaciones del assistant
        y luego... stream() permite transformar la lista, map() funciona como el for, y collect()
        lo hace lista. */
        responseAssistedsList = assistedMapper
                .assistedListToResponseList(this.relationAARepository
                        .findAllByAssistentId(assistantId)
                        .stream().map(RelationAA::getAssisted).collect(Collectors.toList()));

        return responseAssistedsList;

    }

    @Transactional
    @Override
    public ResponseAssisted updateAssisted(RequestEditAssisted requestEditAssisted) {

        Assisted assisted = this.assistedRepository.findById(requestEditAssisted.id())
                .orElseThrow(() -> new EntityNotFoundException(requestEditAssisted.id().toString()));

       if (assisted.getActive()) {
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
    @Override
    public boolean updateRelationAA(Long assistantId, Long assistedId, RelationType relationType){
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
