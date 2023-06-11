package com.hotel.api.infra.security;

import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import ch.qos.logback.core.filter.Filter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("el filtro está siendo llamado");
        filterChain.doFilter(request, response);
    }
    
}