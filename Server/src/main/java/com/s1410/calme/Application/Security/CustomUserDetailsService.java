package com.s1410.calme.Application.Security;

import com.s1410.calme.Domain.Repositories.AssistentRepository;
import com.s1410.calme.Domain.Repositories.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AssistentRepository assistentRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        /* si es assistent */
        var found = assistentRepository.findByEmail(email);
        if (found.isPresent()) {
            var user = User.builder()
                    .username(email)
                    .roles(found.get().getAuthorities().toString())
                    .password(found.get().getPassword())
                    .build();
            return user;
        }

        /* si es doctor */
        var found2 = doctorRepository.findByEmail(email);
        if (found2.isPresent()) {
            var user = User.builder()
                    .username(email)
                    .password(found2.get().getPassword())
                    .roles(found2.get().getAuthorities().toString())
                    .build();
            return user;
        }

        throw new EntityNotFoundException(email);
    }
}

