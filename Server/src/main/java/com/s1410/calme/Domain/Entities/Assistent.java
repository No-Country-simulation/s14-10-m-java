package com.s1410.calme.Domain.Entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.s1410.calme.Domain.Utils.RolesEnum;
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
@Table(name = "assistents")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public final class Assistent extends User {

    @Column(unique = true)
    String email;
    String password;
    RolesEnum role = RolesEnum.ASSISTENT;

    @OneToMany(mappedBy = "assistent" , fetch = FetchType.EAGER)
    @JsonManagedReference
    List<RelationAA> relationsAA; //relation assistent to assisted

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority authority = new SimpleGrantedAuthority(RolesEnum.ASSISTENT.name());
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
