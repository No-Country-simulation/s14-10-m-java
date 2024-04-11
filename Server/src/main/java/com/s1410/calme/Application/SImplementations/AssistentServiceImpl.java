package com.s1410.calme.Application.SImplementations;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAssistent;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssistent;
import com.s1410.calme.Domain.Dtos.response.ResponseAssistent;
import com.s1410.calme.Domain.Entities.Assisted;
import com.s1410.calme.Domain.Entities.Assistent;
import com.s1410.calme.Domain.Entities.RelationAA;
import com.s1410.calme.Domain.Mapper.AssistentMapper;
import com.s1410.calme.Domain.Repositories.AssistedRepository;
import com.s1410.calme.Domain.Repositories.AssistentRepository;
import com.s1410.calme.Domain.Repositories.RelationAARepository;
import com.s1410.calme.Domain.Services.AssistentService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssistentServiceImpl implements AssistentService {

    private final AssistentMapper assistentMapper;
    private final AssistentRepository assistentRepository;
    private final AssistedRepository assistedRepository;
    private final RelationAARepository relationAARepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public ResponseAssistent createAssistent(
            RequestCreateAssistent requestCreateAssistent) {
        if(requestCreateAssistent == null){ throw new EntityNotFoundException(); }

        var assistentAlreadyExists = assistentRepository.findByEmail(requestCreateAssistent.email());
        if(assistentAlreadyExists.isPresent()){ throw new EntityExistsException("Email already in use"); }

        Assistent assistent = this.assistentMapper
                .requestCreateToAssistent(requestCreateAssistent);
        assistent.setPassword(passwordEncoder.encode(requestCreateAssistent.password()));
        assistent.setActive(Boolean.TRUE);
        var assistentAdded = assistentRepository.save(assistent);
        return  assistentMapper.assistentToResponse(assistentAdded);
    }

    @Override
    public ResponseAssistent readAssistent(Long id) {
        Assistent assistent = assistentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("id") );
        return assistentMapper.assistentToResponse(assistent);
    }

    /*
    *active checks status of assistent
    *pageable pulls 20 entries by default
    * */
    @Override
    public Page<ResponseAssistent> readAllAsistents(Boolean active,Pageable paging) {
        return assistentRepository.findAllByActive(active,paging)
                .map(assistentMapper::assistentToResponse);
    }

    @Override
    public List<ResponseAssistent> readAllAssistentFromAssisted(Long assistedId) {
        Assisted assisted = this.assistedRepository.findById(assistedId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Assistant with ID " + assistedId + " not Found."));
        if (!assisted.getActive()) {
            throw new IllegalArgumentException("Assistant with ID " + assistedId + " is inactive.");
        }

        List<ResponseAssistent> responseAssistentList = assistentMapper
                .assistentListToResponseList(this.relationAARepository
                        .findAllByAssistedId(assistedId)
                        .stream().map(RelationAA::getAssistent).collect(Collectors.toList()));
        return responseAssistentList;
    }


    @Transactional
    @Override
    public ResponseAssistent updateAssistent(RequestEditAssistent requestEditAssistent) {
        Assistent assistent = this.assistentRepository.findById(requestEditAssistent.id())
                .orElseThrow(() -> new EntityNotFoundException(requestEditAssistent.id().toString()));

        if (assistent.getActive()) {
            if (requestEditAssistent.firstName() != null) {
                assistent.setFirstName(requestEditAssistent.firstName());
            }
            if (requestEditAssistent.secondName() != null) {
                assistent.setSecondName(requestEditAssistent.secondName());
            }
            if (requestEditAssistent.lastName() != null) {
                assistent.setLastName(requestEditAssistent.lastName());
            }
            if (requestEditAssistent.DNI() != null) {
                assistent.setDNI(requestEditAssistent.DNI());
            }
            if (requestEditAssistent.dateOfBirth() != null) {
                assistent.setDateOfBirth(requestEditAssistent.dateOfBirth());
            }

            this.assistentRepository.save(assistent);
        }
        return assistentMapper.assistentToResponse(assistent);
    }


    @Transactional
    @Override
    public Boolean toogleDeleteAssistent(Long id) {
        Assistent assistent = assistentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("id") );

        assistent.setActive(!assistent.getActive());
        assistentRepository.save(assistent);
        return assistent.getActive();
    }
}
