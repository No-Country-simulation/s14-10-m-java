package com.s1410.calme.Domain.Entities;
import com.s1410.calme.Domain.Utils.RolesEnum;
import com.s1410.calme.Domain.Utils.Specialty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority(RolesEnum.DOCTOR.name());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        return authorities;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
