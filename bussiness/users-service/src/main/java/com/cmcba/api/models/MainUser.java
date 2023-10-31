package com.cmcba.api.models;

import com.cmcba.domain.entities.UserApplication;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author claudio.vilas
 * date 10/2023
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MainUser implements UserDetails {
    private String username;
    private String password;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    public static MainUser build(UserApplication user){
        List<GrantedAuthority> authorities = user.getRoles().stream().map(rol -> {
            return new SimpleGrantedAuthority(rol.getRoleName().name());
        }).collect(Collectors.toList());
        return new MainUser(user.getUsername(), user.getPassword(), user.getEmail(),
                authorities);
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
