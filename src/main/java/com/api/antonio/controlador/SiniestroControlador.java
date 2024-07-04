/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.antonio.controlador;

import com.api.antonio.modelo.Siniestro;
import com.api.antonio.repositorio.SiniestroRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RestController
@RequestMapping("/api/siniestros")
public class SiniestroControlador {

    @Autowired
    private SiniestroRepositorio siniestroRepositorio;

    @GetMapping
    public List<Siniestro> obtenerTodosLosSiniestros() {
        return siniestroRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Siniestro> obtenerSiniestroPorId(@PathVariable int id) {
        return siniestroRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarSiniestro(@PathVariable int id) {
        return siniestroRepositorio.findById(id)
                .map(siniestro -> {
                    siniestroRepositorio.delete(siniestro);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
