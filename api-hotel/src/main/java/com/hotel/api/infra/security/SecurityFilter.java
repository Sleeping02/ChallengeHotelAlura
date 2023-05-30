package com.hotel.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.hotel.api.domain.usuario.UsuarioRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{

        System.out.println("el filtro está siendo llamado");
        var authHeader = request.getHeader("Authorization");
        if(authHeader != null){
            var token = authHeader.replace("Bearer ", "");
            var nombreUsuario = tokenService.getSubject(token);

            System.out.println(token);
            System.out.println(tokenService.getSubject(token));
            if (nombreUsuario != null) {
                var usuario = repository.findByEmail(nombreUsuario);
                var authentication = new UsernamePasswordAuthenticationToken(usuario,
                        null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("el filtro ha finalizado");

                
             
            }
            System.out.println("el filtro ha finalizado");
        }
       
        
        filterChain.doFilter(request, response);
        // var tokenJWT = recuperarToken(request);

        // if(tokenJWT!= null){
        //     var subject = tokenService.getSubject(tokenJWT);
        //     var usuario =repository.findByEmail(subject);

        //     var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        //     SecurityContextHolder.getContext().setAuthentication(authentication);
        // }

        // filterChain.doFilter(request, response);


    }


    // private String recuperarToken(HttpServletRequest request){
    //     var authorizationHeader =request.getHeader("Authorization");
    //     if(authorizationHeader != null){
    //         return authorizationHeader.replace("Bearer", "");
    //     }
    //     return null;
    // }
    
}