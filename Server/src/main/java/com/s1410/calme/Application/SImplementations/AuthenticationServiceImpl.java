package com.s1410.calme.Application.SImplementations;
import com.s1410.calme.Application.Security.JwtService;
import com.s1410.calme.Domain.Dtos.request.RequestLogin;
import com.s1410.calme.Domain.Dtos.response.ResponseLogin;
import com.s1410.calme.Domain.Repositories.AssistentRepository;
import com.s1410.calme.Domain.Repositories.DoctorRepository;
import com.s1410.calme.Domain.Services.AuthenticationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AssistentRepository assistentRepository;
    private final DoctorRepository doctorRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseLogin login(RequestLogin data) {

        String role = "";
        Long id = 0L;
        String token = "";

        var user = assistentRepository.findByEmail(data.email());
        if(user.isPresent()) {
            role = user.get().getAuthorities().toString();
            id = user.get().getId();
            token = jwtService.getToken(user.get());
        }

        if (!user.isPresent()) {
            var user2 = doctorRepository.findByEmail(data.email());
            if (!user2.isPresent()) { throw new EntityNotFoundException(data.email()); }
            role = user2.get().getAuthorities().toString();
            id = user2.get().getId();
            token = jwtService.getToken(user2.get());
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.email(),
                        data.password()));

        ResponseLogin responseLogin = new ResponseLogin(token,role,id);

        return responseLogin;
    }
}