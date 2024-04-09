package com.s1410.calme.Application.SImplementations;
import com.s1410.calme.Application.Security.JwtService;
import com.s1410.calme.Domain.Dtos.request.RequestLogin;
import com.s1410.calme.Domain.Repositories.AssistentRepository;
import com.s1410.calme.Domain.Repositories.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AssistentRepository assistentRepository;
    private final DoctorRepository doctorRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /*Primero hace verificación de que existe en repositorio.
    Luego autentica. Finalmente genera el token. */

    public String login(RequestLogin data) {
        var user = assistentRepository.findByEmail(data.email());
        if (!user.isPresent()) {
            var user2 = doctorRepository.findByEmail(data.email());
            if (!user2.isPresent()) {
                throw new EntityNotFoundException(data.email());
            }
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.email(),
                        data.password()));

        String token = jwtService.getToken(data.email());
        return token;
    }
    }