package com.s1410.calme.Infrastructure.Controllers;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAssisted;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssisted;
import com.s1410.calme.Domain.Dtos.request.RequestEditRelationAA;
import com.s1410.calme.Domain.Dtos.response.ResponseAssisted;
import com.s1410.calme.Domain.Services.AssistedService;
import com.s1410.calme.Domain.Utils.RelationType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/assisted")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class AssistedController {

    public final AssistedService assistedService;

    @PostMapping("/register")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseAssisted registerAssisted(
            @RequestBody
            @Valid
            RequestCreateAssisted createAssisted
    ) {
        try {
            return this.assistedService.createAssisted(createAssisted);
        } catch (EntityExistsException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseAssisted> findAssisted(@PathVariable Long id){
        return ResponseEntity.ok(assistedService.readAssisted(id));
    }

    @GetMapping("/all/{assistantID}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseAssisted> findAllAssistedFromAssistant(@PathVariable Long assistantID) {
        try {
            return this.assistedService.readAllAssistedFromAssistant(assistantID);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseAssisted> updateAssisted(
            @RequestBody @Valid @NonNull RequestEditAssisted editAssisted) {
        try {
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

    @DeleteMapping("/{relationAAId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Boolean removeAssistedToAssistant(@PathVariable Long relationAAId) {
        try {
            return this.assistedService.unlinkAssistedFromAssistant(relationAAId);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }
}
