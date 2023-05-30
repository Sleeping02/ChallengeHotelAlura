package com.hotel.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import com.hotel.api.domain.reserva.DtoActulizarReserva;
import com.hotel.api.domain.reserva.DtoRegistroReserva;
import com.hotel.api.domain.reserva.DtoListarReservas;
import com.hotel.api.domain.reserva.Reserva;
import com.hotel.api.domain.reserva.ReservaRepository;

@RestController
@RequestMapping("/reservas")
public class ReservaController{

    @Autowired
    private  ReservaRepository reservaRepository;

    @PostMapping
    public void registrarReserva(@RequestBody @Validated DtoRegistroReserva datosReserva){
        reservaRepository.save(new Reserva(datosReserva));

    }

    @GetMapping
    public List<DtoListarReservas> listadoReservas(){
        return reservaRepository.findAll().stream().map(DtoListarReservas::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoListarReservas> listarId(@PathVariable Long id){
        Reserva reserva = reservaRepository.getReferenceById(id);
      var datosReservas= new DtoListarReservas(reserva);

      return ResponseEntity.ok(datosReservas);
    }

    

    @PutMapping
    @Transactional
    public void actualizarReserva(@RequestBody @Validated DtoActulizarReserva datosActulizarReserva){
        Reserva reserva = reservaRepository.getReferenceById(datosActulizarReserva.id_reserva());
        reserva.actualizarDatos(datosActulizarReserva);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarReserva(@PathVariable Long id){
        Reserva reserva = reservaRepository.getReferenceById(id);
        reservaRepository.delete(reserva);
    }

    
}
