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
 * Clase que representa una póliza de seguro dentro del sistema. Contiene todos
 * los atributos relevantes para una póliza y métodos para acceder y modificar
 * estos atributos.
 */
@NoArgsConstructor 
@AllArgsConstructor 
@Entity
@Table(name = "polizas")
public class Poliza {

    /**
     * Identificador único de la póliza.
     */
    @Getter 
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPoliza;

    /**
     * Identificador del cliente asociado a la póliza.
     */
    @Getter @Setter
    private int idCliente;

    /**
     * Identificador del recibo asociado a la póliza.
     */
    @Getter @Setter
    private String idRecibo;

    /**
     * Número de la póliza.
     */
    @Getter @Setter
    private String numeroPoliza;

    /**
     * Fecha de emisión de la póliza.
     */
    @Getter @Setter
    private LocalDate fechaEmision;

    /**
     * Fecha de vencimiento de la póliza.
     */
    @Getter @Setter
    private LocalDate fechaVencimiento;

    /**
     * Tipo de cobertura de la póliza.
     */
    @Getter @Setter
    private String tipoCobertura;

    /**
     * Cobertura adicional de la póliza.
     */
    @Getter @Setter
    private String coberturaAdicional;

    /**
     * Agente comercial asociado a la póliza.
     */
    @Getter @Setter
    private String comercial;

    /**
     * Comentarios adicionales sobre la póliza.
     */
    @Getter @Setter
    private String comentarios;

}
