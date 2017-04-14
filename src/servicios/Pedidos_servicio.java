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
            int pedidoAnterior = 0;
            Pedido pedido = null;
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("SELECT ped.idpedido,ped.idempleado,emp.nombre,ped.fecha,ped.usuarioid_creacion,usu.nombre,det.idproducto,prod.descripcion,det.precio,det.cantidad FROM ABMPrueba.pedido ped JOIN ABMPrueba.empleado emp ON emp.idempleado = ped.idempleado JOIN ABMPrueba.usuario usu ON usu.idusuario = ped.usuarioid_creacion LEFT JOIN (ABMPrueba.detalle_pedido det JOIN ABMPrueba.producto prod ON det.idproducto = prod.idproducto) ON ped.idpedido = det.idpedido" + where + ";");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                if (pedidoAnterior != resultado.getInt("idpedido")) {
                    pedido = new Pedido(resultado.getInt("ped.idpedido"), new Empleado(resultado.getInt("ped.idempleado"), resultado.getString("emp.nombre")), new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(resultado.getTimestamp("ped.fecha")), new Usuario(resultado.getInt("ped.usuarioid_creacion"), resultado.getString("usu.nombre"), null));
                    ped.add(pedido);
                    pedidoAnterior = resultado.getInt("ped.idpedido");
                }
                if (resultado.getString("prod.descripcion") != null) {
                    pedido.getDetallesPedido().add(new DetallePedido(resultado.getInt("ped.idpedido"), new Producto(resultado.getInt("det.idproducto"), resultado.getString("prod.descripcion"), null), resultado.getInt("precio"), resultado.getInt("cantidad")));
                }
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

    public void guardarPedidoCab(String idPedido, String idEmpleado,String idUsuario) {
        String a = "INSERT INTO ABMPrueba.pedido (idpedido, idempleado, fecha, usuarioid_creacion) VALUES (" + idPedido + "," + idEmpleado + ", sysdate(), " + idUsuario + ");";
                
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

    public Pedido recuperarPedidoCompleto(String idPedido) throws SQLException {
        //List<Pedido> ped = new ArrayList<>();
        Pedido pedido = null;
        String where = " where ped.idpedido=" + idPedido;
        try {
            int pedidoAnterior = 0;
            
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("SELECT ped.idpedido,ped.idempleado,emp.nombre,ped.fecha,ped.usuarioid_creacion,usu.nombre,det.idproducto,prod.descripcion,det.precio,det.cantidad FROM ABMPrueba.pedido ped JOIN ABMPrueba.empleado emp ON emp.idempleado = ped.idempleado JOIN ABMPrueba.usuario usu ON usu.idusuario = ped.usuarioid_creacion LEFT JOIN (ABMPrueba.detalle_pedido det JOIN ABMPrueba.producto prod ON det.idproducto = prod.idproducto) ON ped.idpedido = det.idpedido" + where + ";");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                if (pedidoAnterior != resultado.getInt("ped.idpedido")) {
                    pedido = new Pedido(resultado.getInt("ped.idpedido"), new Empleado(resultado.getInt("ped.idempleado"), resultado.getString("emp.nombre")), new SimpleDateFormat("dd/MM/yyyy HH:mm").format(resultado.getDate("ped.fecha")), new Usuario(resultado.getInt("ped.usuarioid_creacion"), resultado.getString("usu.nombre"), null));
                   // ped.add(pedido);
                    pedidoAnterior = resultado.getInt("ped.idpedido");
                }
                if (resultado.getString("prod.descripcion") != null) {
                    pedido.getDetallesPedido().add(new DetallePedido(resultado.getInt("ped.idpedido"), new Producto(resultado.getInt("det.idproducto"), resultado.getString("prod.descripcion"), null), resultado.getInt("precio"), resultado.getInt("cantidad")));
                }
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return pedido;
    }
}
