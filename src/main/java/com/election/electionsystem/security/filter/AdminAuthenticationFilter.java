package com.election.electionsystem.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.election.electionsystem.dtos.requests.AdminLoginRequest;
import com.election.electionsystem.exceptions.ElectionException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.election.electionsystem.exceptions.ExceptionMessages.SOMETHING_WENT_WRONG;
import static com.election.electionsystem.utils.ElectionUtils.SECRET;

@AllArgsConstructor
public class AdminAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private ObjectMapper objectMapper;
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        try{
            InputStream inputStream = request.getInputStream();
            AdminLoginRequest login = objectMapper.readValue(inputStream, AdminLoginRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(login.getEmail(),login.getPassword());
            Authentication authResult = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authResult);
            return authResult;
        }
        catch(IOException exception){
            throw new ElectionException(SOMETHING_WENT_WRONG.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        String token = JWT.create().withIssuer("ElectionSystem")
                .withArrayClaim("roles",getClaimsFrom(authResult.getAuthorities()))
                .withExpiresAt(Instant.now().plusSeconds(24*60*60*7))
                .sign(Algorithm.HMAC512(SECRET));
        Map<String, String> res = new HashMap<>();
        res.put("access_token", token);
        response.getOutputStream().write(objectMapper.writeValueAsBytes(res));
        response.flushBuffer();
        chain.doFilter(request,response);
    }

    private String[] getClaimsFrom(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .toArray(String[]::new);
    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
