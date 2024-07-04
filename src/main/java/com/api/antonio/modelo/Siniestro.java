package com.api.antonio.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase que representa un siniestro en el sistema.
 *
 * Un siniestro es un suceso que causa daños y que puede estar cubierto por una
 * póliza de seguro. Esta clase se utiliza para almacenar y gestionar la
 * información relacionada con los siniestros.
 */
@NoArgsConstructor 
@AllArgsConstructor 
@Entity
@Table(name = "siniestros")
public class Siniestro {

    /**
     * Identificador único del siniestro.
     */
    @Getter 
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSiniestro;
    /**
     * Número identificativo del siniestro.
     */
    @Getter 
    @Setter
    private String numSiniestro;
    /**
     * Fecha en la que ocurrió el siniestro.
     */
    @Getter 
    @Setter
    private LocalDate fechaSiniestro;
    /**
     * Descripción del siniestro, incluyendo los detalles del suceso ocurrido.
     */
    @Getter 
    @Setter
    private String descripcion;
    /**
     * Identificador de la póliza de seguro asociada al siniestro.
     */
    @Getter 
    @Setter
    private int idPoliza;
    /**
     * Lugar donde ocurrió el siniestro.
     */
    @Getter 
    @Setter
    private String lugarSiniestro;
    /**
     * Tipo de siniestro (por ejemplo, incendio, robo, etc.).
     */
    @Getter 
    @Setter
    private String tipoSiniestro;
    /**
     * Importe total reclamado por el siniestro a la compañía de seguros.
     */
    @Getter 
    @Setter
    private String totalReclamado;
    /**
     * Estado actual del siniestro (por ejemplo, abierto, cerrado, pendiente de
     * resolución).
     */
    @Getter 
    @Setter
    private String estadoSiniestro;
    /**
     * Fecha de resolución del siniestro, si ya se ha resuelto.
     */
    @Getter 
    @Setter
    private LocalDate fechaResolucion;

}
