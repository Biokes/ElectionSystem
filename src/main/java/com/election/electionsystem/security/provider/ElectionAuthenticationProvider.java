package com.election.electionsystem.security.provider;

import com.election.electionsystem.data.models.SecuredUser;
import com.election.electionsystem.exceptions.ElectionException;
import com.election.electionsystem.services.implementations.SecuredUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import static com.election.electionsystem.exceptions.ExceptionMessages.INVALID_DETAILS;

@Component
@AllArgsConstructor
public class ElectionAuthenticationProvider implements AuthenticationProvider {
    private SecuredUserService securedUserService;
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username= authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        UserDetails user = securedUserService.loadUserByUsername(username);
        if(passwordEncoder.matches(password,user.getPassword()))
            return new UsernamePasswordAuthenticationToken(username,password,user.getAuthorities());
        throw new ElectionException(INVALID_DETAILS.getMessage());
    }
    @Override
    public boolean supports(Class<?> authentication){
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
