package com.hotel.api.domain.reserva;

public record DtoListarReservas(
    Long id_reserva,
    String diaEntrada, 
    String diaSalida, 
    String valor, 
    String formaPago)
{

    public DtoListarReservas(Reserva reserva) {
        this(reserva.getId_reserva(), reserva.getFecha_entrada(), reserva.getFecha_salida(), 
        reserva.getValor(), reserva.getFormaPago());
    }
}
    

