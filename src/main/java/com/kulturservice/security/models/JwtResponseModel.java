package com.kulturservice.security.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponseModel  {
    private final String token;
    private final Collection<? extends GrantedAuthority> roles;
}
