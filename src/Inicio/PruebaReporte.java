/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
        URL in = this.getClass().getResource("report3.jasper");
        JasperReport reporte;
        Map parametersMap = new HashMap();
        parametersMap.put("my_query", "SELECT\n" +
"                prod.idproducto as Codigo,\n" +
"                prod.descripcion as Producto,\n" +
"                sum(det.cantidad) as Cantidad,\n" +
"                sum(det.cantidad*det.precio) as Total,\n" +
"                usr.nombre as Turno\n" +
"                FROM\n" +
"			ABMPrueba.pedido ped,\n" +
"                ABMPrueba.detalle_pedido det,\n" +
"                ABMPrueba.producto prod,\n" +
"                ABMPrueba.usuario usr\n" +
"                WHERE\n" +
"                prod.idproducto = det.idproducto\n" +
"			and det.idpedido = ped.idpedido\n" +
"                and usr.idusuario = ped.usuarioid_creacion\n" +
"                and str_to_date(ped.fecha,'%Y-%m-%d') between str_to_date('01/01/2017','%d/%m/%Y') and str_to_date('01/10/2017','%d/%m/%Y') \n" +
"                and usr.nombre = 'Cami'\n" +
"                group by Codigo,Producto;");
        try {
            reporte = (JasperReport) JRLoader.loadObjectFromFile("C:\\Users\\Cami\\Documents\\ABM Prueba\\src\\Inicio\\report3.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametersMap, Conexion.getConnection());
            JasperViewer.viewReport(jasperPrint);
        } catch (Exception ex) {
            Logger.getLogger(PruebaReporte.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
