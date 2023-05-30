package com.hotel.api.domain.huesped;

import jakarta.validation.constraints.NotBlank;


import com.hotel.api.domain.reserva.Reserva;

public record DtoListarHuespedes(  
Long id_huesped, 
String nombre, 
String apellido, 
String fechaNacimiento, 
String nacionalidad,
String telefono,
@NotBlank
Reserva id_reserva
)
{
    public DtoListarHuespedes(Huesped huesped){
        this(huesped.getId_huesped(), huesped.getNombre(),
        huesped.getApellido(), huesped.getFechaNacimiento(), huesped.getNacionalidad(),
    huesped.getTelefono(), huesped.getReserva());
    
    }
    
   
}
