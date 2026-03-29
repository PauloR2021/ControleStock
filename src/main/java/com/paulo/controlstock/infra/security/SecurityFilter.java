package com.paulo.controlstock.infra.security;


import com.paulo.controlstock.repositorys.users.UserRepository;
import com.paulo.controlstock.services.authentication.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository  userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException
    {
        String token = recoverToken(request);

        if(token != null) {
            String username = tokenService.validateToken(token);

            if(username != null) {
                userRepository.findByUsernameIgnoreCase(username).ifPresent(user -> {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                });
            }
        }
        filterChain.doFilter(request, response);


    }


    private String recoverToken(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")) return null;
        return authHeader.substring(7).trim(); // remove "Bearer " e espaços extras
    }
}
