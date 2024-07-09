package com.api.antonio.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.antonio.modelo.Cliente;
import com.api.antonio.repositorio.ClienteRepositorio;
import java.io.InputStream;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@RestController
@RequestMapping("/api/clientes")
public class ClienteControlador {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @GetMapping
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable int id) {
        return clienteRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable int id, @RequestBody Cliente clienteDetalles) {
        return clienteRepositorio.findById(id)
                .map(cliente -> {
                    cliente.setDNI(clienteDetalles.getDNI());
                    cliente.setNombre(clienteDetalles.getNombre());
                    cliente.setApellido(clienteDetalles.getApellido());
                    cliente.setDireccion(clienteDetalles.getDireccion());
                    cliente.setTelefono(clienteDetalles.getTelefono());
                    cliente.setMail(clienteDetalles.getMail());
                    cliente.setFechaNacimiento(clienteDetalles.getFechaNacimiento());
                    cliente.setGenero(clienteDetalles.getGenero());
                    cliente.setTotalPolizas(clienteDetalles.getTotalPolizas());
                    cliente.setBonificacion(clienteDetalles.getBonificacion());
                    cliente.setEstadoCivil(clienteDetalles.getEstadoCivil());
                    cliente.setNumParientes(clienteDetalles.getNumParientes());
                    cliente.setProfesion(clienteDetalles.getProfesion());
                    cliente.setEstudios(clienteDetalles.getEstudios());
                    cliente.setIngresosAnuales(clienteDetalles.getIngresosAnuales());
                    cliente.setFechaRegistro(clienteDetalles.getFechaRegistro());
                    cliente.setFechaBaja(clienteDetalles.getFechaBaja());
                    cliente.setObservaciones(clienteDetalles.getObservaciones());
                    cliente.setNacionalidad(clienteDetalles.getNacionalidad());
                    cliente.setReferido(clienteDetalles.getReferido());
                    cliente.setVip(clienteDetalles.getVip());
                    Cliente clienteActualizado = clienteRepositorio.save(cliente);
                    return ResponseEntity.ok(clienteActualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarCliente(@PathVariable int id) {
        return clienteRepositorio.findById(id)
                .map(cliente -> {
                    clienteRepositorio.delete(cliente);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/datos")
    public Object obtenerDatosEspecificosCliente(@PathVariable int id) {
        return clienteRepositorio.findById(id)
                .map(cliente -> {
                    // Aquí decides qué datos específicos quieres devolver
                    Map<String, Object> datosEspecificos = new HashMap<>();
                    datosEspecificos.put("nombre", cliente.getNombre());
                    datosEspecificos.put("apellido", cliente.getApellido());
                    datosEspecificos.put("telefono", cliente.getTelefono());
                    // Agrega más campos según tus necesidades

                    return ResponseEntity.ok(datosEspecificos);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Autowired
    private InformeServicio informeServicio;

    @GetMapping("/informe")
    public String generarInformeClientes(@RequestParam(required = false) Map<String, Object> parametros) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("informes/Clientes2.jasper");
            if (inputStream == null) {
                throw new RuntimeException("No se pudo encontrar el archivo Clientes2.jasper");
            }

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(inputStream);
            return informeServicio.generarReporteBase64(jasperReport, parametros != null ? parametros : new HashMap<>());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar el informe de clientes", e);
        }
    }
}
