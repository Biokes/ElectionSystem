package com.election.electionsystem.data.enums;

import org.springframework.security.core.GrantedAuthority;

public enum AdminRole implements GrantedAuthority {
    SYSTEM_ADMINISTRATOR("STYSTEM_ADMINISTRATOR"),
    ELECTION_ADMIN("ELECTION_ADMIN");
    AdminRole(String role){
        this.role = role;
    }
    String role;

    @Override
    public String getAuthority() {
        return this.role;
    }
}
