package com.s1410.calme.Infrastructure.Controllers;
import com.s1410.calme.Domain.Entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.s1410.calme.Domain.Services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
public class LoginControllerHTML {
    private static final Logger logger = LoggerFactory.getLogger(LoginControllerHTML.class);
    private final AuthenticationService authenticationService;


    @GetMapping("/forget-passwordHtml")  //abierto en config
    public String mostrarPaginaOlvidoContrasena(Model model) {
        // Verificar si existen mensajes de éxito o error
        if (!model.containsAttribute("successMessage")) {
            model.addAttribute("successMessage", null);
        }
        if (!model.containsAttribute("errorMessage")) {
            model.addAttribute("errorMessage", null);
        }
        return "thymeleafForgetPassword"; // Nombre del archivo HTML de Thymeleaf

    }
    @PostMapping("/forgot-password")
    public String forgotPassword(@ModelAttribute("email") String email, RedirectAttributes redirectAttributes) {
        try {
            String result = authenticationService.forgotPassword(email);
            redirectAttributes.addFlashAttribute("successMessage", result);  // Agregando mensaje de éxito
            return "redirect:/forget-passwordHtml";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());  // Agregando mensaje de error
            return "redirect:/forget-passwordHtml";
        }
    }
    @GetMapping("/resetPassword")
    public String showPasswordThymeleaf(Model model, @RequestParam String email) {
        model.addAttribute("email", email);
        return "thymeleafResetPassword";
    }

    @PostMapping("/set-password")
    public ResponseEntity<String> setPassword(@RequestParam String email, @RequestParam String password) {
        logger.info("Email recibido para resetear contraseña: {}", email);
        String result = authenticationService.setPassword(email, password);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
