package com.election.electionsystem.services.implementations;

import com.election.electionsystem.data.models.SecuredUser;
import com.election.electionsystem.services.abstractClasses.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecuredUserService implements UserDetailsService {
    private AdminService adminService;
    @Override
    public SecuredUser loadUserByUsername(String email) throws UsernameNotFoundException {
        return new SecuredUser(adminService.getAdminByUsername(email));
    }
}
