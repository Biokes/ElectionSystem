package com.election.electionsystem.security.models;

import com.election.electionsystem.data.models.Admin;
import com.election.electionsystem.data.models.Voter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecuredUser implements UserDetails {
    private Admin admin;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(admin.getRole().toString()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return admin.getVoter().getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getVoter().getEmail();
    }
}
