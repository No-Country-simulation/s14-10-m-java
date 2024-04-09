package com.s1410.calme.Infrastructure.Controllers;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAssisted;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssisted;
import com.s1410.calme.Domain.Dtos.response.ResponseAssisted;
import com.s1410.calme.Domain.Services.AssistedService;
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
    public ResponseEntity<ResponseAssisted> registerAssisted(
            @RequestBody
            @Valid
            RequestCreateAssisted createAssisted
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.assistedService.createAssisted(createAssisted));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseAssisted> findAssisted(@PathVariable Long id) {
        return ResponseEntity.ok(assistedService.readAssisted(id));
    }

    @GetMapping("/all/{assistantID}")
    public ResponseEntity<List<ResponseAssisted>> findAllAssistedFromAssistant(@PathVariable Long assistantID) {
        return ResponseEntity.ok(this.assistedService.readAllAssistedFromAssistant(assistantID));
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

    @DeleteMapping("/id/{relationAAId}")
    public ResponseEntity<Boolean> removeAssistedToAssistant(@PathVariable Long relationAAId) {
        return ResponseEntity.ok(this.assistedService.unlinkAssistedFromAssistant(relationAAId));
    }
}
