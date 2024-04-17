package com.s1410.calme.Application.Config.Validations;

import com.s1410.calme.Domain.Repositories.AssistentRepository;
import com.s1410.calme.Domain.Repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class RoleValidation {
    private final DoctorRepository doctorRepository;
    private final AssistentRepository assistentRepository;

    public boolean checkDoctorRole(){
        var role = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (!role.toString().equals("[ROLE_DOCTOR]")) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED,
                    "¡No tiene credenciales necesarias para esta acción!");
        } return true;
    }

    public boolean checkAssistentRole(){
        var role = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (!role.toString().equals("[ROLE_ASSISTENT]")) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED,
                    "¡No tiene credenciales necesarias para esta acción!");
        } return true;
    }
}
