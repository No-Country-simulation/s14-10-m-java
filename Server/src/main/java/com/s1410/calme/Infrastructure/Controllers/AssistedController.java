package com.s1410.calme.Infrastructure.Controllers;

import com.s1410.calme.Domain.Dtos.request.RequestCreateAssisted;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssisted;
import com.s1410.calme.Domain.Dtos.response.ResponseAssisted;
import com.s1410.calme.Domain.Services.AssistedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/assisted")
@RestController
@RequiredArgsConstructor
public class AssistedController {

    public final AssistedService assistedService;


    @PostMapping("/register")
    public ResponseEntity<ResponseAssisted> registerAssisted(@RequestBody
                                                             RequestCreateAssisted createAssisted){
        return null;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseAssisted> findAssisted(@PathVariable Long id){
        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseAssisted>> findAllAssisted(){
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseAssisted> updateAssisted(@RequestBody
                                                           RequestEditAssisted editAssisted){
        return null;
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Boolean> deleteAssisted(@PathVariable Long id){
        return null;
    }
}
