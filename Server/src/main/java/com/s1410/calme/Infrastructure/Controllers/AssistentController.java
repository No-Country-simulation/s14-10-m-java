package com.s1410.calme.Infrastructure.Controllers;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAssistent;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssistent;
import com.s1410.calme.Domain.Dtos.response.ResponseAssistent;
import com.s1410.calme.Domain.Services.AssistentService;
import com.s1410.calme.Infrastructure.Exceptions.ApiException;
import com.s1410.calme.Infrastructure.Exceptions.BindingResultException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/assistent")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class AssistentController {
    public final AssistentService assistentService;


    @Operation(summary = "Register assistent", description = "Registers an assistent. Email must not exist previously stored in database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully registered assistent"),
            @ApiResponse(responseCode = "400", description = "Invalid Assistent Body", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error registering assistent", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @PostMapping("/register")
    public ResponseEntity<ResponseAssistent> registerAssistent(
            @RequestBody @Valid @NotNull RequestCreateAssistent createAssistent, BindingResult bindingResult)
            throws Exception{
        if (bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.assistentService.createAssistent(createAssistent));
    }


    @Operation(summary = "Get assistent by Id", description = "Retrieves an assistent by its Id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved assistent"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "404", description = "No assisted found with requested Id", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error retrieving assistent", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseAssistent> findAssistent(@PathVariable Long id){
        return ResponseEntity.ok(assistentService.readAssistent(id));
    }


    @Operation(summary = "Get all assistents.", description = "Retrieves all asisstents. Can filter by active status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved assistents"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error retrieving assistents", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    //ej de url /assistents?active=false&page=0&size=10
    //Pulls by status and set amount of entries

    @GetMapping("/all/{active}")
    public ResponseEntity<Page<ResponseAssistent>> findAllAssistents(
            @PathVariable boolean active,
            @RequestParam(required = false) Pageable paging){
        return ResponseEntity.ok(assistentService.readAllAsistents(active,paging));
    }


    @Operation(summary = "Update assistent", description = "Updates the data from an assistent. Assistent is passed by its Id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated assisted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "404", description = "No assistent found with requested Id", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "400", description = "Invalid Assistent Body", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error updating assistent", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ASSISTENT')")
    public ResponseEntity<ResponseAssistent> updateAssistent(
            @RequestBody @Valid @NotNull RequestEditAssistent editAssistent,
            BindingResult bindingResult,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String tokenUser){
        if (bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }
        return ResponseEntity.ok(assistentService.updateAssistent(editAssistent, tokenUser));
    }


    @Operation(summary = "Delete assistent", description = "Deletes an assistent from the database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully deleted assistent"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "404", description = "No assistent found with requested Id", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error deleting assistent", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Boolean> deleteAssistent(@PathVariable Long id,
       @RequestHeader(HttpHeaders.AUTHORIZATION) String tokenUser){
        return ResponseEntity.ok(assistentService.toogleDeleteAssistent(id, tokenUser));
    }
}