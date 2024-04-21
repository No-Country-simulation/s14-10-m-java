package com.s1410.calme.Infrastructure.Controllers;
import com.s1410.calme.Application.SImplementations.AuthenticationServiceImpl;
import com.s1410.calme.Domain.Dtos.request.RequestLogin;
import com.s1410.calme.Domain.Dtos.response.ResponseLogin;
import com.s1410.calme.Infrastructure.Exceptions.BindingResultException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@SecurityRequirement(name = "Bearer Authentication")
public class LoginController {

    private final AuthenticationServiceImpl authenticationServiceImpl;

    @PostMapping
    public ResponseEntity<ResponseLogin> login(
            @RequestBody @Valid @NotNull RequestLogin requestLogin,
            BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                this.authenticationServiceImpl.login(requestLogin));
    }


    @PutMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        String result = authenticationServiceImpl.forgotPassword(email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/set-password")
    public ResponseEntity<String> setPassword(@RequestParam String email, @RequestParam String newPassword) {
        String result = authenticationServiceImpl.setPassword(email, newPassword);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
