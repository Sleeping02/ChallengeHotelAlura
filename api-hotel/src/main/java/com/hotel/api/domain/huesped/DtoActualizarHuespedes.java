package com.hotel.api.domain.huesped;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DtoActualizarHuespedes( 
    @NotNull @Valid
    Long  id_huesped,
    String nombre, 
    String apellido, 
    String fechaNacimiento, 
    String nacionalidad,
    String telefono)
 {

    
}