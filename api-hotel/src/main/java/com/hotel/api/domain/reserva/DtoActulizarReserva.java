package com.hotel.api.domain.reserva;

import jakarta.validation.constraints.NotNull;

public record DtoActulizarReserva(
    @NotNull
    Long id_reserva,
    String diaEntrada, 
    String diaSalida, 
    String valor, 
    String formaPago)
{

    
}