package com.s1410.calme.Infrastructure.Controllers;
import com.s1410.calme.Application.SImplementations.AuthenticationService;
import com.s1410.calme.Domain.Dtos.request.RequestLogin;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@SecurityRequirement(name = "Bearer Authentication")
public class LoginController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<String> login(
            @RequestBody @Valid @NotNull RequestLogin requestLogin){
        return ResponseEntity.status(HttpStatus.OK).body(
                this.authenticationService.login(requestLogin));
    }
}
