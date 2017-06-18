/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
public class Reportes {

    public Reportes(String reporte, String[] param) {

        switch (reporte) {
            case "ReporteVentasProducto":
                crearReporteVentasProducto(param);
                break;
            case "ReporteVentasDia":
                crearReporteVentasDia(param);
                break;
            case "ReporteTXT":
                crearReporteTXT(param);
                break;
            case "ReporteConsumosEmpleado":
                crearReporteConsumosEmpleado(param);
                break;
            case "ReporteTXTTotal":
                crearReporteTXTTotal(param);
                break;
            default:
                break;
        }

    }

    private void crearReporteVentasProducto(String[] param) {
        InputStream jasper1 = getClass().getResourceAsStream("/reportes/ReporteVentasProducto.jasper");
        Map parametersMap = new HashMap();
        String where_usr = "";
        if (param[2].length() > 0) {
            where_usr = " and usr.nombre = '" + param[2] + "' \n";
            parametersMap.put("turno", param[2]);
        } else {
            parametersMap.put("turno", "Todos");
        }
        parametersMap.put("fd", param[0]);
        parametersMap.put("fh", param[1]);
        parametersMap.put("query", "SELECT\n"
                + "                prod.idproducto as Codigo,\n"
                + "                prod.descripcion as Producto,\n"
                + "                sum(det.cantidad) as Cantidad,\n"
                + "                '$' as moneda,"
                + "                sum(det.cantidad*det.precio) as Total,\n"
                + "                usr.nombre as Turno\n"
                + "                FROM\n"
                + "			rioplatense.pedido ped,\n"
                + "                rioplatense.detalle_pedido det,\n"
                + "                rioplatense.producto prod,\n"
                + "                rioplatense.usuario usr\n"
                + "                WHERE\n"
                + "                prod.idproducto = det.idproducto\n"
                + "			and det.idpedido = ped.idpedido\n"
                + "                and usr.idusuario = ped.usuarioid_creacion\n" + where_usr
                + "                and str_to_date(ped.fecha,'%Y-%m-%d') between str_to_date('" + param[0] + "','%d/%m/%Y') and str_to_date('" + param[1] + "','%d/%m/%Y') \n"
                + "                and ped.eliminado = 0 \n"
                + "group by Codigo,Producto;");
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper1, parametersMap, Conexion.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crearReporteVentasDia(String[] param) {
        InputStream jasper1 = getClass().getResourceAsStream("/reportes/ReporteVentasDia.jasper");
        Map parametersMap = new HashMap();
        parametersMap.put("query", "SELECT\n"
                + "                date_format((ped.fecha),'%d/%m/%Y') as Dia,"
                + "               count(ped.idpedido) as CantTick,"
                + "'$' as moneda,"
                + "                sum(ped.total) as Total\n"
                + "                FROM\n"
                + "			rioplatense.pedido ped\n"
                + "                 WHERE\n"
                + "                 str_to_date(ped.fecha,'%Y-%m-%d') between str_to_date('" + param[0] + "','%d/%m/%Y') and str_to_date('" + param[1] + "','%d/%m/%Y') and ped.eliminado=0\n"
                + "                group by Dia;");
        parametersMap.put("fd", param[0]);
        parametersMap.put("fh", param[1]);
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper1, parametersMap, Conexion.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crearReporteTXT(String[] param) {
        InputStream jasper1 = getClass().getResourceAsStream("/reportes/ReporteTXT.jasper");
        Map parametersMap = new HashMap();
        parametersMap.put("query", "SELECT \n"
                + "concat(lpad(ped.idpedido,8,0),\n"
                + "lpad(ped.idempleado,6,0),\n"
                + "date_format((ped.fecha),'%d%m%Y%H%i%s'),\n"
                + "lpad(cast((ped.total*100) as decimal(11,0)),10,0) \n"
                + ") res \n"
                + "from\n"
                + "rioplatense.pedido ped,\n"
                + "rioplatense.empleado emp\n"
                + "where \n"
                + "ped.idempleado = emp.idempleado and str_to_date(ped.fecha,'%Y-%m-%d') between str_to_date('" + param[0] + "','%d/%m/%Y') and str_to_date('" + param[1] + "','%d/%m/%Y') and ped.eliminado=0 and emp.tipo in ("+param[2]+"); \n"
        );
        parametersMap.put("fd", param[0]);
        parametersMap.put("fh", param[1]);
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper1, parametersMap, Conexion.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crearReporteTXTTotal(String[] param) {
        InputStream jasper1 = getClass().getResourceAsStream("/reportes/ReporteTXTTotal.jasper");
        Map parametersMap = new HashMap();
        parametersMap.put("query", "SELECT \n"
                + "				concat(\n"
                + "                lpad(ped.idempleado,6,0),\n"
                + "                lpad(cast(sum(ped.total*100) as decimal(11,0)),10,0)) \n"
                + "                res\n"
                + "                from\n"
                + "                rioplatense.pedido ped,\n" 
                + "                rioplatense.empleado emp\n"
                + "                where \n"
                + "                ped.idempleado = emp.idempleado and str_to_date(ped.fecha,'%Y-%m-%d') between str_to_date('" + param[0] + "','%d/%m/%Y') and str_to_date('" + param[1] + "','%d/%m/%Y') and ped.eliminado=0 and emp.tipo in("+param[2]+") group by ped.idempleado; \n"
        );
        parametersMap.put("fd", param[0]);
        parametersMap.put("fh", param[1]);
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper1, parametersMap, Conexion.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crearReporteConsumosEmpleado(String[] param) {
        InputStream jasper1 = getClass().getResourceAsStream("/reportes/ReporteConsumosEmpleado.jasper");
        Map parametersMap = new HashMap();
        String query = "SELECT \n"
                + "emp.nombre Empleado,\n"
                + "ped.idpedido TicketNro,\n"
                + "ped.fecha Fecha,\n"
                + "prod.descripcion Producto,\n"
                + "det.cantidad Cantidad,\n"
                + "det.precio Precio,\n"
                + "det.cantidad * det.precio Total\n"
                + "FROM\n"
                + "rioplatense.pedido ped,\n"
                + "rioplatense.detalle_pedido det,\n"
                + "rioplatense.empleado emp,\n"
                + "rioplatense.producto prod\n"
                + "WHERE\n"
                + "ped.idpedido = det.idpedido\n"
                + "and ped.idempleado = emp.idempleado\n"
                + "and det.idproducto = prod.idproducto\n"
                + "and ped.eliminado = 0";
        if (param[2] != null) {
            query += " and emp.idempleado=" + param[2];
            parametersMap.put("emp", param[3]);
        } else {
            parametersMap.put("emp", "Todos");
        }
        query += " and str_to_date(ped.fecha,'%Y-%m-%d') between str_to_date('" + param[0] + "','%d/%m/%Y') and str_to_date('" + param[1] + "','%d/%m/%Y');";

        parametersMap.put("query", query);
        parametersMap.put("fd", param[0]);
        parametersMap.put("fh", param[1]);
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper1, parametersMap, Conexion.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
