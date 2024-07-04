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
 * Representa un recibo asociado a una póliza de seguro dentro del sistema.
 */
@NoArgsConstructor 
@AllArgsConstructor 
@Entity
@Table(name = "recibos")
public class Recibo {

    @Getter 
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRecibo;

    /**
     * Número del recibo, utilizado para identificar el recibo dentro de la
     * empresa y para el cliente.
     */
    @Getter 
    @Setter
    private String numRecibo;

    /**
     * Fecha en que el recibo fue emitido.
     */
    @Getter 
    @Setter
    private LocalDate fechaEmision;

    /**
     * Fecha en que el recibo vence. Esta fecha indica hasta cuando el pago del
     * recibo puede ser realizado sin penalizaciones.
     */
    @Getter 
    @Setter
    private LocalDate fechaVencimiento;

    /**
     * Monto total a pagar indicado en el recibo.
     */
    @Getter 
    @Setter
    private String totalPagar;

    /**
     * Identificador de la póliza asociada con este recibo. Esto vincula el
     * recibo a una póliza específica de seguro.
     */
    @Getter 
    @Setter
    private int idPoliza;

    /**
     * Estado actual del recibo, que puede incluir estados como 'Pagado',
     * 'Pendiente', 'Atrasado', etc.
     */
    @Getter 
    @Setter
    private String estadoRecibo;

    /**
     * Tipo de pago utilizado para pagar el recibo, puede ser 'Tarjeta',
     * 'Efectivo', 'Transferencia', etc.
     */
    @Getter 
    @Setter
    private String tipoPago;

}
