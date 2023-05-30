package com.hotel.api.domain.huesped;

import jakarta.validation.Valid;

import jakarta.validation.constraints.NotNull;
import com.hotel.api.domain.reserva.Reserva;


public record DtoRegistrarHuesped(
    String nombre, 
    String apellido, 
    String fechaNacimiento, 
    String nacionalidad,
    String telefono,
    @NotNull @Valid  
    Reserva id_reserva

) {
    
}