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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/findBySpeciality")
    public ResponseEntity<Page<ResponseDoctor>> readAllDoctorBySpecialty(@RequestParam String specialty, Pageable pageable) {
        return ResponseEntity.ok(doctorService.readAllDoctorBySpecialty(specialty, pageable));
    }

    @GetMapping("/findByPostalCode")
    public ResponseEntity<Page<ResponseDoctor>> readAllDoctorsBySamePostalCode(@RequestParam int postalCode, Pageable pageable) {
        return ResponseEntity.ok(doctorService.readAllDoctorsBySamePostalCode(postalCode, pageable));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDoctor> updateDoctor(@RequestBody @Valid @NotNull RequestEditDoctor editDoctor,
                                                       BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()){
                throw new BindingResultException(bindingResult);
            }
            return ResponseEntity.ok(doctorService.updateDoctor(editDoctor));
        } catch (NoResultException e) {
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Boolean> deleteDoctor(@PathVariable Long id){
        return ResponseEntity.ok(doctorService.toogleDeleteDoctor(id));
    }
}
