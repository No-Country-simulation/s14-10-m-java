package com.s1410.calme.Application.SImplementations;
import com.s1410.calme.Application.Security.JwtService;
import com.s1410.calme.Domain.Dtos.request.RequestLogin;
import com.s1410.calme.Domain.Dtos.response.ResponseLogin;
import com.s1410.calme.Domain.Repositories.AssistentRepository;
import com.s1410.calme.Domain.Repositories.DoctorRepository;
import com.s1410.calme.Domain.Services.AuthenticationService;
import com.s1410.calme.Domain.Entities.User;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AssistentRepository assistentRepository;
    private final DoctorRepository doctorRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    private final PasswordEncoder passwordEncoder;

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
    public void sendPasswordRecoveryMail(String email) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(email);
            helper.setSubject("Recuperacion de contrasena");

            Context context = new Context();
            // Agregar variables de contexto para la plantilla Thymeleaf
           // context.setVariable("message", "CLICK aqui para recuperar contresena " + " \"http://localhost:8080/verify-password?token=\"" + email);
            context.setVariable("message", "CLICK aquí para recuperar contraseña: <a href=\"http://localhost:8080/login/set-password?email=" + email + "\">Recuperar contraseña</a>");

            // Procesar la plantilla Thymeleaf
            String htmlBody = templateEngine.process("email", context);

            // Establecer el cuerpo del mensaje como HTML
            helper.setText(htmlBody, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException(" Error " + " al enviar el correo de recuperacion, reenvie el correo : " + e.getMessage(), e);
        }
    }

    public String forgotPassword(String email) {
        User user = assistentRepository.findByEmail(email) //MOOD
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con este email: " + email));

        sendPasswordRecoveryMail(email);  // REUTILIZACION DE FUNCION - refactorizamos el try catch

        return "Por favor, revise su correo electrónico para establecer una nueva contraseña para su cuenta";
    }

    //public String setPassword(String email, String newPassword) {
        public String setPassword(String email, String newPassword) {
            var user = assistentRepository.findByEmail(email)

       // User user = assistentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con este email: " + email));

        String encodedPassword = passwordEncoder.encode(newPassword);

        user.setPassword(encodedPassword);

        assistentRepository.save(user);

        return "Nueva contraseña establecida con éxito, por favor inicie sesión";
    }
}