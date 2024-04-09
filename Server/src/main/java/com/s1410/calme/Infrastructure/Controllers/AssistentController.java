package com.s1410.calme.Infrastructure.Controllers;
import com.s1410.calme.Domain.Dtos.request.RequestCreateAssistent;
import com.s1410.calme.Domain.Dtos.request.RequestEditAssistent;
import com.s1410.calme.Domain.Dtos.response.ResponseAssistent;
import com.s1410.calme.Domain.Services.AssistentService;
import com.s1410.calme.Infrastructure.Exceptions.BindingResultException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/assistent")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class AssistentController {
    public final AssistentService assistentService;

    @PostMapping("/register")
    public ResponseEntity<ResponseAssistent> registerAssistent(
            @RequestBody @Valid @NotNull RequestCreateAssistent createAssistent, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.assistentService.createAssistent(createAssistent));
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
            @RequestBody @Valid @NotNull RequestEditAssistent editAssistent, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }
        return ResponseEntity.ok(assistentService.updateAssistent(editAssistent));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Boolean> deleteAssistent(@PathVariable Long id){
        return ResponseEntity.ok(assistentService.toogleDeleteAssistent(id));
    }
}