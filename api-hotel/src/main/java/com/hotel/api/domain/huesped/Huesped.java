package com.hotel.api.domain.huesped;



import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.hotel.api.domain.reserva.Reserva;

@Table(name="tb_huespedes")
@Entity(name="Huesped")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id_huesped")
public class Huesped {

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_huesped;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String nacionalidad;
    private String telefono;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva", nullable = false)
    @JsonBackReference
    private Reserva reserva;
   

  
    public Huesped(DtoRegistrarHuesped dtoHuesped ) {
        this.nombre = dtoHuesped.nombre();
        this.apellido= dtoHuesped.apellido();
        this.fechaNacimiento=dtoHuesped.fechaNacimiento();
        this.nacionalidad=dtoHuesped.nacionalidad();
        this.telefono=dtoHuesped.telefono();
        // this.reserva= dtoHuesped.id_reserva();
        this.reserva= dtoHuesped.id_reserva();
    }

    public void actualizarDatos(DtoActualizarHuespedes datoActualizarHuespedes){
        if(datoActualizarHuespedes.nombre() !=null){
            this.nombre= datoActualizarHuespedes.nombre();
        }
    
        if(datoActualizarHuespedes.apellido() !=null){
            this.apellido= datoActualizarHuespedes.apellido();
        }

        if(datoActualizarHuespedes.fechaNacimiento() !=null){
            this.fechaNacimiento= datoActualizarHuespedes.fechaNacimiento();
        }

        if(datoActualizarHuespedes.nacionalidad() !=null){
            this.nacionalidad= datoActualizarHuespedes.nacionalidad();
        }

        if(datoActualizarHuespedes.nombre() !=null){
            this.telefono= datoActualizarHuespedes.telefono();
        }
  
}
    


    


  

}