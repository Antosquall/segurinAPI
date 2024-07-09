/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.antonio.controlador;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.Base64;
import java.util.Map;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class InformeServicio {

    @Autowired
    private DataSource dataSource;

    public String generarReporteBase64(JasperReport jasperReport, Map<String, Object> parametros) {
        try (Connection conexion = dataSource.getConnection();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexion);
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            
            byte[] pdfBytes = baos.toByteArray();
            return Base64.getEncoder().encodeToString(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar el reporte", e);
        }
    }
}