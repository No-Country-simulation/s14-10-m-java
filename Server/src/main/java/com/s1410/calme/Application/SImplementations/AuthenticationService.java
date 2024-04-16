package com.s1410.calme.Application.SImplementations;
import com.s1410.calme.Application.Security.JwtService;
import com.s1410.calme.Domain.Dtos.request.RequestLogin;
import com.s1410.calme.Domain.Dtos.response.ResponseLogin;
import com.s1410.calme.Domain.Entities.Assistent;
import com.s1410.calme.Domain.Repositories.AssistentRepository;
import com.s1410.calme.Domain.Repositories.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AssistentRepository assistentRepository;
    private final DoctorRepository doctorRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseLogin login(RequestLogin data) {

        String role = "";
        Long id = 0L;
        var user = assistentRepository.findByEmail(data.email());
        if(user.isPresent()) {
            role = user.get().getAuthorities().toString();
            id = user.get().getId();
        }

        if (!user.isPresent()) {
            var user2 = doctorRepository.findByEmail(data.email());
            role = user2.get().getAuthorities().toString();
            id = user2.get().getId();
            if (!user2.isPresent()) {
                throw new EntityNotFoundException(data.email());
            }
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.email(),
                        data.password()));

        String token = jwtService.getToken(data.email());
        ResponseLogin responseLogin = new ResponseLogin(token,role,id);

        return responseLogin;
    }
}