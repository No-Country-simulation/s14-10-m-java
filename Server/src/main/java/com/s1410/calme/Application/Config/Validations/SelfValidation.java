package com.s1410.calme.Application.Config.Validations;

import com.s1410.calme.Domain.Repositories.AssistentRepository;
import com.s1410.calme.Domain.Repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SelfValidation {
    private final DoctorRepository doctorRepository;
    private final AssistentRepository assistentRepository;

    public boolean checkSelfValidation(Long id){
        String userLogged = SecurityContextHolder.getContext().getAuthentication().getName();
        var possibleAssistent = assistentRepository.findById(id);
        var possibleDoctor = doctorRepository.findById(id);


        if (possibleAssistent.isEmpty() || !userLogged.equals(possibleAssistent.get().getEmail())) {
             if (possibleDoctor.isEmpty() || !userLogged.equals(possibleDoctor.get().getEmail())) {
            throw new IllegalArgumentException("Logged user cannot edit this user!");
        }
        } return true;
    }

}
