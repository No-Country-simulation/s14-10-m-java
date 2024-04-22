package com.s1410.calme.Infrastructure.Controllers;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAssisted;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssisted;
import com.s1410.calme.Domain.Dtos.request.RequestEditRelationAA;
import com.s1410.calme.Domain.Dtos.response.ResponseAssisted;
import com.s1410.calme.Domain.Services.AssistedService;
import com.s1410.calme.Infrastructure.Exceptions.BindingResultException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/assisted")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class AssistedController {

    public final AssistedService assistedService;

    @PostMapping("/register")
    public ResponseEntity<ResponseAssisted> registerAssisted(
            @RequestBody
            @Valid
            RequestCreateAssisted createAssisted,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()){
              throw new BindingResultException(bindingResult);
            }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.assistedService.createAssisted(createAssisted));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseAssisted> findAssisted(@PathVariable Long id) {
        return ResponseEntity.ok(assistedService.readAssisted(id));
    }

    @GetMapping("/all/{assistantID}")
    public ResponseEntity<Page<ResponseAssisted>> findAllAssistedFromAssistant(
            @PathVariable Long assistantID,
            @PageableDefault(page = 0, size = Integer.MAX_VALUE) Pageable pageable,
            @RequestParam(required = false, defaultValue = "true") Boolean actives) {
        return ResponseEntity.ok(this.assistedService.readAllAssistedFromAssistant(assistantID, pageable, actives));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseAssisted> updateAssisted(
            @RequestBody @Valid @NonNull RequestEditAssisted editAssisted, BindingResult bindingResult) {

        try {
            if (bindingResult.hasErrors()){
                throw new BindingResultException(bindingResult);
            }
            return ResponseEntity.ok(assistedService.updateAssisted(editAssisted));
        } catch (NoResultException e) {
            throw new EntityNotFoundException();
        }
    }

    @PutMapping("/updateRelation")
    public ResponseEntity<Boolean> updateRelationAA(
            @RequestBody @Valid @NonNull RequestEditRelationAA dto) {
            return ResponseEntity.ok(assistedService
           .updateRelationAA(dto.assistantId(), dto.assistedId(), dto.relationType()));
    }

    @DeleteMapping("/deleteRelation/{assistantId}/{assistedId}")
    public ResponseEntity<Boolean> removeAssistedToAssistant(@PathVariable Long assistantId, @PathVariable Long assistedId ) {
        return ResponseEntity.ok(this.assistedService.unlinkAssistedFromAssistant(assistantId, assistedId));
    }
}
