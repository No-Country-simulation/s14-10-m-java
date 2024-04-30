package com.s1410.calme.Infrastructure.Controllers;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAssisted;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssisted;
import com.s1410.calme.Domain.Dtos.request.RequestEditRelationAA;
import com.s1410.calme.Domain.Dtos.response.ResponseAssisted;
import com.s1410.calme.Domain.Services.AssistedService;
import com.s1410.calme.Infrastructure.Exceptions.ApiException;
import com.s1410.calme.Infrastructure.Exceptions.BindingResultException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Register assisted", description = "Registers an assisted. Email must not exist previously stored in database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully registered assisted"),
            @ApiResponse(responseCode = "400", description = "Invalid Assisted Body", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error registering assisted", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
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


    @Operation(summary = "Get assisted by Id", description = "Retrieves an assisted by its Id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved assisted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "404", description = "No assisted found with requested Id", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error retrieving assisted", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseAssisted> findAssisted(@PathVariable Long id) {
        return ResponseEntity.ok(assistedService.readAssisted(id));
    }

    @Operation(summary = "Get assisted from an Assistant", description = "Retrieves all Assisted that have a relation with an Assistant. Assistant is passed by its Id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved assisteds"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "404", description = "No assistant found with requested Id", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error retrieving assisteds", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @GetMapping("/all/{assistantID}")
    public ResponseEntity<Page<ResponseAssisted>> findAllAssistedFromAssistant(
            @PathVariable Long assistantID,
            @PageableDefault(page = 0, size = Integer.MAX_VALUE) Pageable pageable,
            @RequestParam(required = false, defaultValue = "true") Boolean actives) {
        return ResponseEntity.ok(this.assistedService.readAllAssistedFromAssistant(assistantID, pageable, actives));
    }

    @Operation(summary = "Update assisted", description = "Updates the data from an assisted. Assisted is passed by its Id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated assisted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "404", description = "No assisted found with requested Id", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "400", description = "Invalid Assisted Body", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error updating assisted", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
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


    @Operation(summary = "Update assisted relation", description = "Updates the relation between an assisted and an assistant")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated relationship"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "400", description = "Invalid Relation Body", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error updating relation", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @PutMapping("/updateRelation")
    public ResponseEntity<Boolean> updateRelationAA(
            @RequestBody @Valid @NonNull RequestEditRelationAA dto) {
            return ResponseEntity.ok(assistedService
           .updateRelationAA(dto.assistantId(), dto.assistedId(), dto.relationType()));
    }


    @Operation(summary = "Delete relation between assisted and Assistant", description = "Deletes the relation between an assisted and an assistant.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully deleted relationship"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "404", description = "No assisted found with requestedId", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "404", description = "No assistent found with requestedId", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error deleting relation", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @DeleteMapping("/deleteRelation/{assistantId}/{assistedId}")
    public ResponseEntity<Boolean> removeAssistedToAssistant(@PathVariable Long assistantId, @PathVariable Long assistedId ) {
        return ResponseEntity.ok(this.assistedService.unlinkAssistedFromAssistant(assistantId, assistedId));
    }
}
