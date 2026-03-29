package com.paulo.controlstock.infra.security;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@SecurityScheme(name = SecurityConfiguration.SECURITY, type = SecuritySchemeType.HTTP,bearerFormat = "JWT",scheme = "bearer")
public class SecurityConfiguration {
    private final SecurityFilter  securityFilter;

    public SecurityConfiguration(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    public static final String SECURITY = "bearerAuth";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                                // Swagger
                                .requestMatchers(
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/swagger-ui.html"
                                ).permitAll()
                                .requestMatchers("/auth/login").permitAll()// qualquer usuário
                                .requestMatchers("/user/admin/**").hasRole("ADMIN")


                                .anyRequest().authenticated() //Qualquer outro endPoint precisa ser autenticado

                        //.anyRequest().permitAll() // libera tudo

                )

                // Adiciona o filtro de autenticação JWT antes do filtro padrão
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
