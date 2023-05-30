package com.hotel.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.hotel.api.domain.huesped.Huesped;
import com.hotel.api.domain.huesped.HuespedRepository;
import com.hotel.api.domain.huesped.DtoActualizarHuespedes;
import com.hotel.api.domain.huesped.DtoListarHuespedes;
import com.hotel.api.domain.huesped.DtoRegistrarHuesped;

import java.util.List;



@RestController
@RequestMapping("/huespedes")
public class HuespedController {

    @Autowired
    private HuespedRepository huespedRepository;


    @PostMapping
    public void registrarHuespedes(@RequestBody @Validated DtoRegistrarHuesped dtoHuesped){
        huespedRepository.save(new Huesped(dtoHuesped));
    }

    @GetMapping
    public List<DtoListarHuespedes> listadoHuespedes(){
        return huespedRepository.findAll().stream().map(DtoListarHuespedes::new).toList();
    }

    @PutMapping
    @Transactional
    public void actualizarHuespedes(@RequestBody @Validated DtoActualizarHuespedes dtoActualizarHuespedes){
        Huesped huesped= huespedRepository.getReferenceById(dtoActualizarHuespedes.id_huesped());
        huesped.actualizarDatos(dtoActualizarHuespedes); 
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarHuesped(@PathVariable Long id)
    {
        Huesped huesped = huespedRepository.getReferenceById(id);
        huespedRepository.delete(huesped);
    }
    
}