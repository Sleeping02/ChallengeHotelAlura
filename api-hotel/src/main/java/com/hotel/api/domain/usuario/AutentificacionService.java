package com.hotel.api.domain.usuario;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutentificacionService implements UserDetailsService{


    private UsuarioRepository usuarioRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return usuarioRepository.findByEmail(username);

        
    }


    
}