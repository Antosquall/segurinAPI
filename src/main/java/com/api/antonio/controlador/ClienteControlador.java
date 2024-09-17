package com.api.antonio.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.antonio.modelo.Cliente;
import com.api.antonio.repositorio.ClienteRepositorio;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/clientes")
@PreAuthorize("denyAll()")
public class ClienteControlador {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @GetMapping
    @PreAuthorize("hasAuthority('READ') or hasAuthority('CREATE')")
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
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        Cliente savedCliente = clienteRepositorio.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable int id, @RequestBody Cliente clienteDetalles) {
        if(!clienteRepositorio.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        clienteDetalles.setIdCliente(id);
        Cliente clienteSalvado= clienteRepositorio.save(clienteDetalles);
        return ResponseEntity.ok(clienteSalvado);

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
            //return informeServicio.generarReporteBase64(jasperReport, parametros != null ? parametros : new HashMap<>());
            //String conca = informeServicio.generarReporteBase64(jasperReport, parametros != null ? parametros : new HashMap<>());
            String conca;

            ObjectMapper mapper = new ObjectMapper();
            conca = mapper.writeValueAsString(informeServicio.generarReporteBase64(jasperReport, parametros != null ? parametros : new HashMap<>()));

            return conca;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar el informe de clientes", e);
        }
    }

}
