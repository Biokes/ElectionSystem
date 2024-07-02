package com.election.electionsystem.security.manager;

import com.election.electionsystem.exceptions.ElectionException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import static com.election.electionsystem.exceptions.ExceptionMessages.SOMETHING_WENT_WRONG;

@Component
@AllArgsConstructor
public class ElectionAuthenticationManager implements AuthenticationManager{
    private final AuthenticationProvider authenticationProvider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        Class<? extends Authentication> authenticationType = authentication.getClass();
        if (authenticationProvider.supports(authenticationType))
            return authenticationProvider.authenticate(authentication);
        throw new ElectionException(SOMETHING_WENT_WRONG.getMessage());
    }
}
