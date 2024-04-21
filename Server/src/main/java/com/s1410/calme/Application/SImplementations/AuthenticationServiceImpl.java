package com.s1410.calme.Application.SImplementations;
import com.s1410.calme.Application.Security.JwtService;
import com.s1410.calme.Domain.Dtos.request.RequestLogin;
import com.s1410.calme.Domain.Dtos.response.ResponseLogin;
import com.s1410.calme.Domain.Repositories.AssistentRepository;
import com.s1410.calme.Domain.Repositories.DoctorRepository;
import com.s1410.calme.Domain.Services.AuthenticationService;
import com.s1410.calme.Domain.Entities.User;
import com.s1410.calme.Domain.Entities.Assistent;
import com.s1410.calme.Domain.Entities.Doctor;
import com.s1410.calme.Domain.Services.EmailService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AssistentRepository assistentRepository;
    private final DoctorRepository doctorRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public ResponseLogin login(RequestLogin data) {

        String role = "";
        Long id = 0L;
        String token = "";

        var user = assistentRepository.findByEmail(data.email());
        if (user.isPresent()) {
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

    public String forgotPassword(String email) {
        User user = findUserByEmail(email);
        emailService.sendPasswordRecoveryMail(email);
        return "Por favor, revise su correo electrónico para establecer una nueva contraseña para su cuenta";
    }

    public String setPassword(String email, String newPassword) {
        Optional<Doctor> doctorEmail = doctorRepository.findByEmail(email);
        if (doctorEmail.isPresent()) {
            Doctor doctor = doctorEmail.get();
            String encoderPassword = passwordEncoder.encode(newPassword);
            doctor.setPassword(encoderPassword);

            doctorRepository.save(doctor);
            return "Nueva contraseña establecida con éxito, por favor inicie sesión";
        }

        Optional<Assistent> assistentEmail = assistentRepository.findByEmail(email);
        if (assistentEmail.isPresent()) {
            Assistent assistent = assistentEmail.get();
            String encoderPassword = passwordEncoder.encode(newPassword);
            assistent.setPassword(encoderPassword);

            assistentRepository.save(assistent);
            return "Nueva contraseña establecida con éxito, por favor inicie sesión";
        }
        throw new RuntimeException("Usuario no encontradocon este email:" + email);
    }

    private User findUserByEmail(String email) {
        Optional<Assistent> assistentOptional = assistentRepository.findByEmail(email);
        if (assistentOptional.isPresent()) {
            return assistentOptional.get();
        }

        Optional<Doctor> doctorOptional = doctorRepository.findByEmail(email);
        if (doctorOptional.isPresent()) {
            return doctorOptional.get();
        }

        throw new RuntimeException("No se ha encontrado este email relacionado a una cuenta de asistente o doctor: " + email);
    }
}