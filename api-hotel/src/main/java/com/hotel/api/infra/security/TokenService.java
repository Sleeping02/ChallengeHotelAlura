package com.hotel.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



import com.hotel.api.domain.usuario.Usuario;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String secret;

    public String generarToken(Usuario usuario){

        try {
            
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
            .withIssuer("hotel com")
            .withSubject(usuario.getEmail())
            .withClaim("id", usuario.getId_usuario())
            .withExpiresAt(dataExpiracion())
            .sign(algoritmo);
        } catch (JWTCreationException  e) {
           throw new RuntimeException("error al generar el token", e);
        }
    }



    public String getSubject(String token){
        DecodedJWT verifier=null;
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            verifier = JWT.require(algoritmo)
            .withIssuer("hotel com")
            .build()
            .verify(token);
            verifier.getSubject();

        } catch (JWTVerificationException e) {
            
        }
        if(verifier.getSubject() ==null){
           throw new RuntimeException("verifier invalido");
        }
        return verifier.getSubject();
        
    }

    // public String getSubject(String tokenJWT){
    //     try {   

    //         Algorithm algoritmo = Algorithm.HMAC256(secret);
    //         return JWT.require(algoritmo)
    //         .withIssuer("hotel com")
    //         .build()
    //         .verify(tokenJWT)
    //         .getSubject();
            
    //     } catch (JWTVerificationException e) {
    //         throw new RuntimeException("Token JWT inválido o expirado", e);
    //     }

    // }

    private Instant dataExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    
}