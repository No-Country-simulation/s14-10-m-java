package com.s1410.calme.Infrastructure.Controllers;
import com.s1410.calme.Domain.Dtos.request.RequestLogin;
import com.s1410.calme.Domain.Dtos.response.ResponseLogin;
import com.s1410.calme.Infrastructure.Exceptions.ApiException;
import com.s1410.calme.Domain.Services.AuthenticationService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@SecurityRequirement(name = "Bearer Authentication")
public class LoginController {

    private final AuthenticationService authenticationService;


    @Operation(summary = "Login user", description = "Logs in an user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully logged in"),
            @ApiResponse(responseCode = "400", description = "Invalid Login Body", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error logging user", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @PostMapping
    public ResponseEntity<ResponseLogin> login(
            @RequestBody @Valid @NotNull RequestLogin requestLogin,
            BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                this.authenticationService.login(requestLogin));
    }



    @Operation(summary = "Recover user password", description = "Sends email for user to recovers their password.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully sent email"),
            @ApiResponse(responseCode = "400", description = "Invalid email body", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error sending email", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @PutMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        String result = authenticationService.forgotPassword(email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @Operation(summary = "Change user password", description = "Changes an user password after they accepted the 'forgot-password' email")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully changed password"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error changing password", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = ApiException.class
                            )
                    )}
            )
    })
    @PutMapping("/set-password")
    public ResponseEntity<String> setPassword(@RequestParam String email, @RequestParam String newPassword) {

        String result = authenticationService.setPassword(email, newPassword);


        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
