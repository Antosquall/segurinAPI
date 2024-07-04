/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.antonio.controlador;

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
    public List<Poliza> obtenerTodosLosClientes() {
        return polizaRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poliza> obtenerClientePorId(@PathVariable int id) {
        return polizaRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Poliza crearCliente(@RequestBody Poliza poliza) {
        return polizaRepositorio.save(poliza);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Poliza> actualizarCliente(@PathVariable int id, @RequestBody Poliza polizaDetalles) {
//        return polizaRepositorio.findById(id)
//                .map(poliza -> {
//                    poliza.setDNI(polizaDetalles.getDNI());
//                    poliza.setNombre(polizaDetalles.getNombre());
//                    poliza.setApellido(polizaDetalles.getApellido());
//                    poliza.setDireccion(polizaDetalles.getDireccion());
//                    poliza.setTelefono(polizaDetalles.getTelefono());
//                    poliza.setMail(polizaDetalles.getMail());
//                    poliza.setFechaNacimiento(polizaDetalles.getFechaNacimiento());
//                    poliza.setGenero(polizaDetalles.getGenero());
//                    poliza.setTotalPolizas(polizaDetalles.getTotalPolizas());
//                    poliza.setBonificacion(polizaDetalles.getBonificacion());
//                    poliza.setEstadoCivil(polizaDetalles.getEstadoCivil());
//                    poliza.setNumParientes(polizaDetalles.getNumParientes());
//                    poliza.setProfesion(polizaDetalles.getProfesion());
//                    poliza.setEstudios(polizaDetalles.getEstudios());
//                    poliza.setIngresosAnuales(polizaDetalles.getIngresosAnuales());
//                    poliza.setFechaRegistro(polizaDetalles.getFechaRegistro());
//                    poliza.setFechaBaja(polizaDetalles.getFechaBaja());
//                    poliza.setObservaciones(polizaDetalles.getObservaciones());
//                    poliza.setNacionalidad(polizaDetalles.getNacionalidad());
//                    poliza.setReferido(polizaDetalles.getReferido());
//                    poliza.setVip(polizaDetalles.getVip());
//                    Poliza clienteActualizado = polizaDetalles.save(poliza);
//                    return ResponseEntity.ok(clienteActualizado);
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }

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
