package com.election.electionsystem.data.models;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
public class SecuredUser implements UserDetails {
    private final Admin voter;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return voter.getVoter().getPassword();
    }

    @Override
    public String getUsername() {
        return null;
    }
}
