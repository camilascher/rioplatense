/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import modelos.DetallePedido;
import modelos.Empleado;
import modelos.Pedido;
import util.Impresora;
import util.Impresora1;
import util.Impresora2;

/**
 *
 * @author Nico
 */
public class Impresion_servicio {

    private static Impresion_servicio instance = null;
    private final int tipoImpresora;

    protected Impresion_servicio(int tipoImpresora) {
        this.tipoImpresora = tipoImpresora;
    }

    public static Impresion_servicio getInstance() {
        if (instance == null) {
            instance = new Impresion_servicio(1);
        }
        return instance;
    }

    private Impresora getImpresora() {
        if(tipoImpresora == 1)
            return new Impresora1(null);
        else
            return new Impresora2(null);
    }

    public void imprimirPedido(Pedido pedido) {
        Impresora impresora = getImpresora();
        String empresa = Parametros_servicio.getInstance().recuperarValorPorCodigo("empresa");
        String cliente = Parametros_servicio.getInstance().recuperarValorPorCodigo("cliente");
        String quincena = Parametros_servicio.getInstance().recuperarValorPorCodigo("quincena");
        Empleado empleado = pedido.getEmpleado();
        Double total = pedido.getTotal();
        Double acumulado = Pedidos_servicio.getInstance().recuperarTotalEmpleado(empleado.getIdEmpleado(), quincena, null);
        Double totalLegajo = pedido.getTotal() - pedido.getBonificacion();

        //Cabecera
        impresora.escribir(empresa);
        impresora.escribir(cliente);
        //String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
        impresora.escribir("Fecha  " + pedido.getFecha());
        impresora.escribir("Ticket Nro " + pedido.getIdPedido());
        impresora.escribir(empleado.getNombreEmpleado().toUpperCase());
        impresora.escribir("Legajo: " + empleado.getIdEmpleado().toString());
        //Items
        impresora.escribir("CNT Producto        Precio");
        for (DetallePedido detalle : pedido.getDetallesPedido()) {
            //total += detalle.getCantidad() * detalle.getPrecio();
            String precio = String.format("%5s", String.valueOf(detalle.getCantidad() * detalle.getPrecio()));
            String cant = String.format("%3s", detalle.getCantidad().toString());
            String desc = String.format("%-16s", detalle.getProducto().getDescripcion()).substring(0, 16);
            String linea = cant + " " + desc + " " + precio;
            impresora.escribir(linea);
        }
        //Totales
        impresora.escribir("* SALDOS            ******");
        impresora.escribir("   Total Ticket:" + String.format("%10s", total.toString()));
        impresora.escribir("   Bonificacion:" + String.format("%10s", pedido.getBonificacion().toString()));
        impresora.escribir("   Total Legajo:" + String.format("%10s", totalLegajo.toString()));
        impresora.escribir("  Acum. del Mes:" + String.format("%10s", acumulado.toString()));
        //Pie
        impresora.escribir("");
        impresora.escribir("**************************");
        impresora.escribir("*       USO INTERNO      *");
        impresora.escribir("* Este comprobante no es *");
        impresora.escribir("* valido como factura    *");
        impresora.escribir("**************************");
        impresora.finalizar();
    }
}
