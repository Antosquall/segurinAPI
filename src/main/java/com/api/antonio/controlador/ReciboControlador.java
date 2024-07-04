/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.antonio.controlador;

import com.api.antonio.modelo.Cliente;
import com.api.antonio.modelo.Recibo;
import com.api.antonio.repositorio.ReciboRepositorio;
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
@RequestMapping("/api/recibos")
public class ReciboControlador {
    
    @Autowired
    private ReciboRepositorio reciboRepositorio;

    @GetMapping
    public List<Recibo> obtenerTodosLosClientes() {
        return reciboRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recibo> obtenerReciboPorId(@PathVariable int id) {
        return reciboRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


        @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarRecibo(@PathVariable int id) {
        return reciboRepositorio.findById(id)
                .map(recibo -> {
                    reciboRepositorio.delete(recibo);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
}
