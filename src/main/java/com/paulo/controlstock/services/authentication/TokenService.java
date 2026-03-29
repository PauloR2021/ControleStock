package com.paulo.controlstock.services.authentication;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.paulo.controlstock.models.user.UserModel;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;


    @PostConstruct
    public void checkSecret() {
        if(secret == null || secret.isBlank()) {
            throw new IllegalStateException("JWT secret não configurado!");
        }
    }

    public String generateToken(UserModel user) {
        try{

            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(Instant.now().plusSeconds(7200))
                    .sign(algorithm);

        }catch (Exception e){
            throw new RuntimeException("Erro ao gerar token JWT - ERRO: "+e.getMessage());
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido ou expirado");
        }
    }


}
