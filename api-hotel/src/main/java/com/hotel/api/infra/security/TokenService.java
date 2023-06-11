package com.hotel.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.hotel.api.domain.usuario.Usuario;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;


    public String generarToken(Usuario usuario){
       try {
    Algorithm algorithm = Algorithm.HMAC256(apiSecret);
    return JWT.create()
        .withIssuer("hotel api")
        .withSubject(usuario.getEmail())
        .withClaim("id", usuario.getId_usuario())
        .withExpiresAt(generarFechaExpiracion())
        .sign(algorithm);
} catch (JWTCreationException exception){
    throw new RuntimeException();
}
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
    
}

