package com.s1410.calme.Domain.Entities;
import com.s1410.calme.Domain.Utils.RolesEnum;
import com.s1410.calme.Domain.Utils.Specialty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Doctor extends User {

    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Column(unique = true)
    private String email;
    private String password;
    private Boolean morning;
    private Boolean afternoon;
    private Boolean night;
    private Integer postalCode;
    private Long licenseNumber;
    private String address;
    private RolesEnum role = RolesEnum.DOCTOR;
    private Boolean validUser = false;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role.toString()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
