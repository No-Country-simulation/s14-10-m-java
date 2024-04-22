package com.s1410.calme.Infrastructure.Controllers;
import com.s1410.calme.Domain.Dtos.request.RequestCreateDoctor;
import com.s1410.calme.Domain.Dtos.request.RequestEditDoctor;
import com.s1410.calme.Domain.Dtos.response.ResponseDoctor;
import com.s1410.calme.Domain.Services.DoctorService;
import com.s1410.calme.Infrastructure.Exceptions.ApiException;
import com.s1410.calme.Infrastructure.Exceptions.BindingResultException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/doctor")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class DoctorController {

    private final DoctorService doctorService;

    @Operation(summary = "Register doctor", description = "Registers a doctor. Email must not exist previously stored in database.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully registered doctor"),
            @ApiResponse(responseCode = "400", description = "Invalid Doctor Body", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error registering doctor", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @PostMapping("/register")
    public ResponseEntity<ResponseDoctor> registerDoctor(@RequestBody @Valid @NotNull RequestCreateDoctor requestCreateDoctor,
                                                         BindingResult bindingResult) throws Exception {
        try {
            if (bindingResult.hasErrors()){
                throw new BindingResultException(bindingResult);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(this.doctorService.createDoctor(requestCreateDoctor));
        } catch (EntityExistsException e) {
            throw new IllegalArgumentException();
        }
    }

    @Operation(summary = "Get doctor by Id", description = "Retrieves an doctor by its Id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved doctor"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "404", description = "No doctor found with requested Id", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error retrieving doctor", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseDoctor> findDoctor(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.readDoctor(id));
    }

    @Operation(summary = "Get all doctors.", description = "Retrieves all doctors. Can filter by active status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved doctors"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error retrieving doctors", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @GetMapping("/all/{active}")
    public ResponseEntity<Page<ResponseDoctor>> findAllDoctors(@PathVariable boolean active,
                                                               @RequestParam(required = false)
                                                               Pageable paging){
        return ResponseEntity.ok(doctorService.readAllDoctors(active, paging));
    }


    @Operation(summary = "Get all doctors based on availability.", description = "Retrieves all doctors.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved doctors"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error retrieving doctors", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @GetMapping("/findByAvailability/{availability}")
    public ResponseEntity<Page<ResponseDoctor>> readAllDoctorByAvailability(
            @PathVariable String availability, Pageable pageable) {
        return ResponseEntity.ok(doctorService.readAllDoctorsByAvailability(availability, pageable));
    }

    @Operation(summary = "Get all doctors based on postal code.", description = "Retrieves all doctors.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved doctors"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error retrieving doctors", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @GetMapping("/findByPostalCode")
    public ResponseEntity<List<ResponseDoctor>> readAllDoctorsBySamePostalCode(@RequestParam Integer postalCode) {
        List<ResponseDoctor> doctors = doctorService.readAllDoctorsBySamePostalCode(postalCode);
        return ResponseEntity.ok(doctors);
    }

    @Operation(summary = "Get all doctors based on specialty.", description = "Retrieves all doctors.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved doctors"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error retrieving doctors", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @GetMapping("/findBySpecialty")
    public ResponseEntity<List<ResponseDoctor>> readAllDoctorBySpecialty(@RequestParam String specialty) {
        List<ResponseDoctor> doctors = doctorService.readAllDoctorBySpecialty(specialty);
        return ResponseEntity.ok(doctors);
    }

    @Operation(summary = "Get all doctors based surname", description = "Retrieves all doctors. Can order ASC or DESC")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved doctors"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error retrieving doctors", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @GetMapping("/findBySurname/{asc}")
    public ResponseEntity<Page<ResponseDoctor>> readAllDoctorBySurname(
            @PathVariable Boolean asc, Pageable pageable) {
        return ResponseEntity.ok(doctorService.readAllDoctorsBySurname(asc, pageable));
    }


    @Operation(summary = "Update doctor", description = "Updates the data from a doctor. doctor is passed by its Id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated assisted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "404", description = "No doctor found with requested Id", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "400", description = "Invalid Doctor Body", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error updating doctor", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDoctor> updateDoctor(
            @RequestBody @Valid @NotNull RequestEditDoctor editDoctor,
            BindingResult bindingResult,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String tokenUser){
        try {
            if (bindingResult.hasErrors()){
                throw new BindingResultException(bindingResult);
            }
            return ResponseEntity.ok(doctorService.updateDoctor(editDoctor, tokenUser));
        } catch (NoResultException e) {
            throw new EntityNotFoundException();
        }
    }


    @Operation(summary = "Delete doctor by Id", description = "Deletes a doctor by its Id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully deleted doctor"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request/Invalid token"),
            @ApiResponse(responseCode = "404", description = "No doctor found with requested Id", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error deleting doctor", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Boolean> deleteDoctor(@PathVariable Long id,
          @RequestHeader(HttpHeaders.AUTHORIZATION) String tokenUser){
        return ResponseEntity.ok(doctorService.toogleDeleteDoctor(id, tokenUser));
    }
}
