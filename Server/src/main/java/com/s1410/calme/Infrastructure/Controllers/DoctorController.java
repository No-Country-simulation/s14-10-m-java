package com.s1410.calme.Infrastructure.Controllers;
import com.s1410.calme.Domain.Dtos.request.RequestCreateDoctor;
import com.s1410.calme.Domain.Dtos.request.RequestEditDoctor;
import com.s1410.calme.Domain.Dtos.response.ResponseDoctor;
import com.s1410.calme.Domain.Services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/doctor")
@RestController
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDoctor> registerDoctor(@RequestBody RequestCreateDoctor requestCreateDoctor) {
        return null;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseDoctor> findDoctor(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseDoctor>> findAllDoctors(){
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDoctor> updateDoctor(@RequestBody RequestEditDoctor editDoctor){
        return null;
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Boolean> deleteDoctor(@PathVariable Long id){
        return null;
    }
}
