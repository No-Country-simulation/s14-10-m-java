package com.s1410.calme.Infrastructure.Controllers;
import com.s1410.calme.Domain.Dtos.request.RequestCreateDoctor;
import com.s1410.calme.Domain.Dtos.request.RequestEditDoctor;
import com.s1410.calme.Domain.Dtos.response.ResponseDoctor;
import com.s1410.calme.Domain.Services.DoctorService;
import com.s1410.calme.Infrastructure.Exceptions.BindingResultException;
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

    @PostMapping("/register")
    public ResponseEntity<ResponseDoctor> registerDoctor(@RequestBody @Valid @NotNull RequestCreateDoctor requestCreateDoctor,
                                                         BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()){
                throw new BindingResultException(bindingResult);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(this.doctorService.createDoctor(requestCreateDoctor));
        } catch (EntityExistsException e) {
            throw new IllegalArgumentException();
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseDoctor> findDoctor(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.readDoctor(id));
    }

    @GetMapping("/all/{active}")
    public ResponseEntity<Page<ResponseDoctor>> findAllDoctors(@PathVariable boolean active,
                                                               @RequestParam(required = false)
                                                               Pageable paging){
        return ResponseEntity.ok(doctorService.readAllDoctors(active, paging));
    }

    @GetMapping("/findByAvailability/{availability}")
    public ResponseEntity<Page<ResponseDoctor>> readAllDoctorByAvailability(
            @PathVariable String availability, Pageable pageable) {
        return ResponseEntity.ok(doctorService.readAllDoctorsByAvailability(availability, pageable));
    }

    @GetMapping("/findByPostalCode")
    public ResponseEntity<List<ResponseDoctor>> readAllDoctorsBySamePostalCode(@RequestParam Integer postalCode) {
        List<ResponseDoctor> doctors = doctorService.readAllDoctorsBySamePostalCode(postalCode);
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/findBySpecialty")
    public ResponseEntity<List<ResponseDoctor>> readAllDoctorBySpecialty(@RequestParam String specialty) {
        List<ResponseDoctor> doctors = doctorService.readAllDoctorBySpecialty(specialty);
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/findBySurname/{asc}")
    public ResponseEntity<Page<ResponseDoctor>> readAllDoctorBySurname(
            @PathVariable Boolean asc, Pageable pageable) {
        return ResponseEntity.ok(doctorService.readAllDoctorsBySurname(asc, pageable));
    }


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

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Boolean> deleteDoctor(@PathVariable Long id,
          @RequestHeader(HttpHeaders.AUTHORIZATION) String tokenUser){
        return ResponseEntity.ok(doctorService.toogleDeleteDoctor(id, tokenUser));
    }
}