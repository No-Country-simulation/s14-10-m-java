package com.s1410.calme.Infrastructure.Controllers;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAssistent;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssistent;
import com.s1410.calme.Domain.Dtos.response.ResponseAssistent;
import com.s1410.calme.Domain.Services.AssistentService;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/assistent")
@RestController
@RequiredArgsConstructor
public class AssistentController {
    public final AssistentService assistentService;

    @PostMapping("/register")
    public ResponseEntity<ResponseAssistent> registerAssistent(
            @RequestBody @Valid @NotNull RequestCreateAssistent createAssistent){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.assistentService.createAssistent(createAssistent));
        }catch (EntityExistsException e){
            throw new EntityExistsException(createAssistent.email());
            }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseAssistent> findAssistent(@PathVariable Long id){
        return ResponseEntity.ok(assistentService.readAssistent(id));
    }


    //ej de url /assistents?active=false&page=0&size=10
    //Pulls by status and set amount of entries
    @GetMapping("/all/{active}")
    public ResponseEntity<Page<ResponseAssistent>> findAllAssistents(
            @PathVariable boolean active,
            @RequestParam(required = false) Pageable paging){
        return ResponseEntity.ok(assistentService.readAllAsistents(active,paging));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseAssistent> updateAssistent(
            @RequestBody @Valid @NotNull RequestEditAssistent editAssistent){
        return ResponseEntity.ok(assistentService.updateAssistent(editAssistent));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Boolean> deleteAssistent(@PathVariable Long id){
        return ResponseEntity.ok(assistentService.toogleDeleteAssistent(id));
    }
}