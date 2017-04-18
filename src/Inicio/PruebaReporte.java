/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import servicios.Conexion;

/**
 *
 * @author Nico
 */
public class PruebaReporte {

    public PruebaReporte() {
        URL in = this.getClass().getResource("report1.jasper");
        JasperReport reporte;
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile("C:\\Users\\Nico\\Documents\\NetBeansProjects2\\ciaber\\src\\Inicio\\report1.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, Conexion.getConnection());
            JasperViewer.viewReport(jasperPrint);
        } catch (Exception ex) {
            Logger.getLogger(PruebaReporte.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
