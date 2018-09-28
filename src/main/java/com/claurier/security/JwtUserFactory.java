package com.claurier.security;

import java.util.List;
import java.util.stream.Collectors;


import com.claurier.model.Authority;
import com.claurier.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;



public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getAuthorities()),
                user.getEnabled(),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}
