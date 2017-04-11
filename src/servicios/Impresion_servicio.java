/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Empleado;
import modelos.Pedido;
import servicios.Empleados_servicio;
import servicios.Pedidos_servicio;
import util.Impresora;

/**
 *
 * @author Nico
 */
public class Impresion_servicio {

    static final String NOMBRE_EMPRESA = "EURO-CUCINE S.R.L.";
    
    static void imprimirPedido(Pedido pedido) {
        Impresora impresora = new Impresora(null);
        List<Pedido> items = null;
        Empleado empleado = null;
        Double total = 0.0;
        Double acumulado = 0.0;

        try {
            items = Pedidos_servicio.getInstance().recuperarPedidoCompleto(pedido.getIdPedido().toString());
        } catch (SQLException ex) {
            Logger.getLogger(Impresion_servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            empleado = Empleados_servicio.getInstance().recuperarEmpPorDescripcion(pedido.getNombreEmpleado());
        } catch (SQLException ex) {
            Logger.getLogger(Impresion_servicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Cabecera
        impresora.escribir(NOMBRE_EMPRESA);
        //String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
        impresora.escribir("Fecha     " + pedido.getFechaCreacion());
        impresora.escribir("Ticket Nro " + pedido.getIdPedido());
        impresora.escribir(empleado.getNombreEmpleado().toUpperCase());
        impresora.escribir(empleado.getIdEmpleado().toString());
        //Items
        impresora.escribir("CNT Producto        Precio");
        impresora.escribir("* ADICIONALES       ******");
        for (Pedido item : items) {
            total += item.getCantidad() * item.getPrecio();
            String precio = String.format("%5s", item.getPrecio().toString());
            String cant = String.format("%3s", item.getCantidad().toString());
            String desc = String.format("%-16s", item.getDescProd()).substring(0, 16);
            String linea = cant + " " + desc + " " + precio;
            impresora.escribir(linea);
        }
        //Totales
        impresora.escribir("* SALDOS            ******");
        impresora.escribir("   Total Ticket:" + String.format("%10s", total.toString()));
        impresora.escribir("  Acum. del Mes:" + String.format("%10s", acumulado.toString()));
        //Pie
        impresora.escribir("");
        impresora.escribir("**************************");
        impresora.escribir("*       USO INTERNO      *");
        impresora.escribir("* Este comprobante no es *");
        impresora.escribir("* valido como factura    *");
        impresora.escribir("**************************");

    }
}
