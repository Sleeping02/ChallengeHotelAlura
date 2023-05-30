package com.hotel.api.domain.reserva;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.hotel.api.domain.huesped.Huesped;
import jakarta.persistence.*;


@Embeddable
@Table(name="reservas")
@Entity(name="Reserva")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id_reserva")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reserva;
    private String fecha_entrada;
    private String fecha_salida;
    private String valor;
    private String formaPago;
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore

    private List<Huesped>huespeds = new ArrayList<>();


    public Reserva(DtoRegistroReserva registroReserva) { 
    this.fecha_entrada=registroReserva.diaEntrada();
    this.fecha_salida=registroReserva.diaSalida();
    this.valor=registroReserva.valor();
    this.formaPago=registroReserva.formaPago();
    }


    public void actualizarDatos(DtoActulizarReserva datosActulizarReserva) {
        if(datosActulizarReserva.diaEntrada() != null){
            this.fecha_entrada=datosActulizarReserva.diaEntrada();
        }
        if(datosActulizarReserva.diaSalida() != null){
            this.fecha_salida=datosActulizarReserva.diaSalida();
        }
        if(datosActulizarReserva.valor() != null){
            this.valor=datosActulizarReserva.valor();
        }
        if(datosActulizarReserva.formaPago() != null){
            this.formaPago=datosActulizarReserva.formaPago();
        }
        
        
       

    }
}