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
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        var found = assistentRepository.findByEmail(email);
        if (found.isPresent()) {
            var user = User.builder().username(email)
                    .password(found.get().getPassword()).build();
            return user;
        }

        found = doctorRepository.findByEmail(email);
        if (found.isPresent()) {
            var user = User.builder().username(email)
                    .password(found.get().getPassword()).build();
            return user;
        }

        throw new EntityNotFoundException(email);
    }
}

