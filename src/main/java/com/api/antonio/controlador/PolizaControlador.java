/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.antonio.controlador;

import com.api.antonio.modelo.Cliente;
import com.api.antonio.modelo.Poliza;
import com.api.antonio.repositorio.PolizaRepositorio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */

@RestController
@RequestMapping("/api/poliza")
public class PolizaControlador {
    
    @Autowired
    private PolizaRepositorio polizaRepositorio;
    
    
    @GetMapping
    public List<Poliza> obtenerTodasLasPolizas() {
        return polizaRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poliza> obtenerPolizaPorId(@PathVariable int id) {
        return polizaRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Poliza crearPoliza(@RequestBody Poliza poliza) {
        return polizaRepositorio.save(poliza);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Poliza> actualizarPoliza(@PathVariable int id, @RequestBody Poliza polizaDetalles) {
        if(!polizaRepositorio.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        polizaDetalles.setIdCliente(id);
        Poliza polizaSalvado= polizaRepositorio.save(polizaDetalles);
        return ResponseEntity.ok(polizaSalvado);

    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarPoliza(@PathVariable int id) {
        return polizaRepositorio.findById(id)
                .map(cliente -> {
                    polizaRepositorio.delete(cliente);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
