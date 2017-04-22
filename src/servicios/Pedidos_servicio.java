/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.DetallePedido;
import modelos.Empleado;
import modelos.Pedido;
import modelos.Producto;
import modelos.Usuario;

/**
 *
 * @author cami
 */
public class Pedidos_servicio {

    private static Pedidos_servicio instance = null;

    protected Pedidos_servicio() {
        //Evita que la clase se instancie
    }

    public static Pedidos_servicio getInstance() {
        if (instance == null) {
            instance = new Pedidos_servicio();
        }
        return instance;
    }

    public List<Pedido> recuperarTodasEnc(String idPedido, String fecha, String empleado) throws SQLException {
        List<Pedido> ped = new ArrayList<>();
        String where = "";
        if (!idPedido.isEmpty()) {
            where += "ped.idpedido=" + idPedido;
        }
        if (!fecha.isEmpty()) {
            where += (!where.isEmpty() ? " and " : "") + "str_to_date(fecha,'%Y-%m-%d')=str_to_date('" + fecha + "','%d/%m/%Y')";
        }
        if (!empleado.isEmpty()) {
            where += (!where.isEmpty() ? " and " : "") + "emp.nombre='" + empleado + "'";
        }
        if (!where.isEmpty()) {
            where = " where " + where;
        }
        try {
            Pedido pedido = null;
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("SELECT ped.idpedido,ped.idempleado,emp.nombre,emp.dni,emp.tarjeta,emp.bonificado,emp.bonif_tope,ped.fecha,ped.total,ped.usuarioid_creacion,usu.nombre FROM ABMPrueba.pedido ped JOIN ABMPrueba.empleado emp ON emp.idempleado = ped.idempleado JOIN ABMPrueba.usuario usu ON usu.idusuario = ped.usuarioid_creacion" + where + ";");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                pedido = new Pedido(resultado.getInt("ped.idpedido"), new Empleado(resultado.getInt("ped.idempleado"), resultado.getString("emp.nombre"), resultado.getInt("emp.dni"), resultado.getString("emp.tarjeta"), resultado.getDouble("emp.bonificado"), resultado.getInt("emp.bonif_tope")), new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(resultado.getTimestamp("ped.fecha")), resultado.getDouble("ped.total"), new Usuario(resultado.getInt("ped.usuarioid_creacion"), resultado.getString("usu.nombre"), null));
                ped.add(pedido);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return ped;
    }

    public String recuperarIdNuevoPed() throws SQLException {
        String ped = "";

        try {
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("select ifnull(max(idpedido),0)+1 idpedido from ABMPrueba.pedido;");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                ped = resultado.getString("idpedido");

            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return ped;
    }

    public void guardarPedidoCab(String idPedido, String idEmpleado,String bonificacion,String total,String idUsuario) {
        String a = "INSERT INTO ABMPrueba.pedido (idpedido, idempleado, fecha,bonificacion,total, usuarioid_creacion) VALUES (" + idPedido + "," + idEmpleado + ", sysdate(), "+bonificacion+","+total+"," + idUsuario + ");";

        try {
            PreparedStatement insert = Conexion.getConnection().prepareStatement(a);
            insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Pedidos_servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarPedidoDet(String idPedido, String idProducto, String precio, String cant) {
        String a = "INSERT INTO ABMPrueba.detalle_pedido (idpedido,idproducto,precio,cantidad) VALUES (" + idPedido + "," + idProducto + "," + precio + "," + cant + ");";
        try {
            PreparedStatement insert = Conexion.getConnection().prepareStatement(a);
            insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Pedidos_servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarPedidoDet(String idPedido) {
        String a = "DELETE FROM ABMPrueba.detalle_pedido where idpedido = " + idPedido + ";";
        try {
            PreparedStatement delete = Conexion.getConnection().prepareStatement(a);
            delete.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Pedidos_servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarPedidoCab(String idPedido) {
        String a = "DELETE FROM ABMPrueba.pedido where idpedido = " + idPedido + ";";
        try {
            PreparedStatement delete = Conexion.getConnection().prepareStatement(a);
            delete.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Pedidos_servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Pedido recuperarPedidoCompleto(String idPedido) throws SQLException {
        Pedido pedido = null;
        String where = " where ped.idpedido=" + idPedido;
        try {
            int pedidoAnterior = 0;

            PreparedStatement consulta = Conexion.getConnection().prepareStatement("SELECT ped.idpedido,ped.idempleado,emp.nombre,emp.nombre,emp.dni,emp.tarjeta,emp.bonificado,emp.bonif_tope,ped.fecha,ped.total,ped.usuarioid_creacion,usu.nombre,det.idproducto,prod.descripcion,det.precio,det.cantidad FROM ABMPrueba.pedido ped JOIN ABMPrueba.empleado emp ON emp.idempleado = ped.idempleado JOIN ABMPrueba.usuario usu ON usu.idusuario = ped.usuarioid_creacion LEFT JOIN (ABMPrueba.detalle_pedido det JOIN ABMPrueba.producto prod ON det.idproducto = prod.idproducto) ON ped.idpedido = det.idpedido" + where + ";");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                if (pedidoAnterior != resultado.getInt("ped.idpedido")) {
                    pedido = new Pedido(resultado.getInt("ped.idpedido"), new Empleado(resultado.getInt("ped.idempleado"), resultado.getString("emp.nombre"), resultado.getInt("emp.dni"), resultado.getString("emp.tarjeta"), resultado.getDouble("emp.bonificado"), resultado.getInt("emp.bonif_tope")), new SimpleDateFormat("dd/MM/yyyy HH:mm").format(resultado.getDate("ped.fecha")), resultado.getDouble("ped.total"), new Usuario(resultado.getInt("ped.usuarioid_creacion"), resultado.getString("usu.nombre"), null));

                    pedidoAnterior = resultado.getInt("ped.idpedido");
                }
                if (resultado.getString("prod.descripcion") != null) {
                    pedido.getDetallesPedido().add(new DetallePedido(resultado.getInt("ped.idpedido"), new Producto(resultado.getInt("det.idproducto"), resultado.getString("prod.descripcion"), resultado.getDouble("det.precio")), resultado.getDouble("precio"), resultado.getInt("cantidad")));
                }
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return pedido;
    }

    public Double recuperarTotalEmpleado(Integer emp, String fecha, Pedido ped) { //pasar fecha null para traer saldo del día solamente y ped null para nuevo pedido o ped con datos para modificación
        ResultSet resultado = null;
        Double tot = 0.0;
        if (fecha==null){
            fecha = " sysdate(),'%Y-%m-%d' ";
        }
        else{
            fecha = fecha + ",'%Y/%m/%d'";
        }
        String where = " ped.idempleado=" + emp.toString()+" and str_to_date(fecha,'%Y-%m-%d')>=str_to_date(" + fecha + ")";
        if (ped != null) { //Si el pedido no es nulo, está modificando, se usa la fecha del día del pedido
            where += " and ped.idpedido<>" + ped.getIdPedido().toString(); //agrego el <> del id de pedido modificado para que no lotenga en cuenta en el cálculo del total
        } 
        try {
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("SELECT ifnull(sum(ped.total),0) as Total FROM ABMPrueba.pedido ped where" + where + ";");
            resultado = consulta.executeQuery();
            resultado.next();
            tot = resultado.getDouble("Total");
        } catch (SQLException ex) {
            Logger.getLogger(Pedidos_servicio.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return tot;
    }
}
