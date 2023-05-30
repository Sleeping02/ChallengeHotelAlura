package com.hotel.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import com.hotel.api.domain.usuario.DtoAutentificacion;
import com.hotel.api.domain.usuario.Usuario;
import com.hotel.api.infra.security.DtoTokenJWT;
import com.hotel.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AutentificacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;



    @PostMapping
   
    public ResponseEntity<DtoTokenJWT> autenticarUsuario(@RequestBody @Valid
                                                               DtoAutentificacion datosAutenticacionUsuario) {
                                                                System.out.println("inicio1");
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.email(),
      
                datosAutenticacionUsuario.contrasena());
                System.out.println("inicio2");
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        System.out.println("inicio3");
        var JWToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        System.out.println("inicio4");
        return ResponseEntity.ok(new DtoTokenJWT(JWToken));
        
    }


    // @PostMapping
    // public ResponseEntity autentificarUsuario(@RequestBody @Valid DtoAutentificacion dtoAutentificacion ){
    //     System.out.println("Holaaaaa");

    //     Authentication authtoken = new UsernamePasswordAuthenticationToken(dtoAutentificacion.email(), dtoAutentificacion.contrasena()  );
       
    //     System.out.println("paso por acá");


    //     manager.authenticate(authtoken);

    //     System.out.println("Llegue");
    //     var usuarioAutenticado = manager.authenticate(authtoken);
    //     var JWTtoken= tokenService.generarToken((Usuario)usuarioAutenticado.getPrincipal());
       
    //     return ResponseEntity.ok(new DtoTokenJWT(JWTtoken));

    // }

    // @PostMapping
    // public ResponseEntity efectuarLogin(@RequestBody @Validated DtoAutentificacion dtoAutentificacion){

    //     var authenticationToken = new UsernamePasswordAuthenticationToken(dtoAutentificacion.email(), 
    //     dtoAutentificacion.contrasena());

    //     var authentication = manager.authenticate(authenticationToken);

    //     var tokenJWT = tokenService.generarToken((Usuario) authentication.getPrincipal());

    //     return ResponseEntity.ok(new DtoTokenJWT(tokenJWT));

    // }
    
}