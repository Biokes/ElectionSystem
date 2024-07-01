package com.election.electionsystem.security;

import com.election.electionsystem.dtos.requests.AdminLoginRequest;
import com.election.electionsystem.exceptions.ElectionException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.InputStream;

import static com.election.electionsystem.exceptions.ExceptionMessages.SOMETHING_WENT_WRONG;

@AllArgsConstructor
public class AdminAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private ObjectMapper objectMapper;
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        try{
            InputStream inputStream = request.getInputStream();
            AdminLoginRequest login = objectMapper.readValue(inputStream, AdminLoginRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(login.getEmail(),
                    login.getPassword());
            Authentication authResult = authenticationManager.authenticate(authentication);
        }
        catch(IOException exception){
            throw new ElectionException(SOMETHING_WENT_WRONG.getMessage());
        }
    }
}
