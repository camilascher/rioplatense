/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Pedido;

/**
 *
 * @author cami
 */
public class Pedidos_servicio {
    private static Pedidos_servicio instance = null;
    
    protected Pedidos_servicio(){
        //Evita que la clase se instancie
    }
    
    public static Pedidos_servicio getInstance(){
        if (instance == null){
            instance = new Pedidos_servicio();
        }
        return instance;
    }
    
    public List<Pedido> recuperarTodasEnc(String pedido, String fecha, String empleado) throws SQLException{
        List<Pedido> ped = new ArrayList<>();
        String where = "";
        if(!pedido.isEmpty()) {
           where+=" and ped.idpedido="+pedido;
        }
        if(!fecha.isEmpty()){
            where+=" and str_to_date(fecha,'%Y-%m-%d')=str_to_date('"+fecha+"','%d/%m/%Y')";
        }
        if(!empleado.isEmpty()){
            where+=" and emp.nombre='"+empleado+"'";
        }
        try{
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("SELECT distinct ped.idpedido,ped.idempleado,emp.nombre,ped.fecha from ABMPrueba.pedido ped, ABMPrueba.empleado emp where emp.idempleado = ped.idempleado "+where+" ORDER BY idpedido desc");
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                ped.add(new Pedido(resultado.getInt("idpedido"),resultado.getInt("idempleado"),resultado.getString("nombre"),null,null,null,null,resultado.getString("fecha"),null,null));
                
            }
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
        return ped;
    }
    
    public String recuperarIdNuevoPed() throws SQLException{
        String ped = "";
        
        try{
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("select ifnull(max(idpedido),0)+1 idpedido from ABMPrueba.pedido;");
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                ped = resultado.getString("idpedido");
                
            }
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
        return ped;
    }
    
    public void guardarPedidoLinea(String idPedido,String idEmpleado,String idProducto,String precio,String cant, String idUsuario){
        String a = "INSERT INTO ABMPrueba.pedido (idpedido, idempleado, idproducto, precio, cantidad, fecha, usuarioid_creacion, fecha_creacion) VALUES ("+idPedido+","+idEmpleado+", "+idProducto+", "+precio+","+cant+", curdate(), "+idUsuario+", sysdate());";
        try {
            PreparedStatement insert = Conexion.getConnection().prepareStatement(a);
            insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Pedidos_servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Pedido> recuperarPedidoCompleto(String pedido) throws SQLException{
        List<Pedido> ped = new ArrayList<>();
        String where="";
        where +=" and ped.idpedido="+pedido;
        try{
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("SELECT ped.idpedido,ped.idempleado,emp.nombre,ped.fecha,ped.idproducto,ped.precio,ped.cantidad,prod.descripcion,ped.usuarioid_creacion, ped.fecha_creacion from ABMPrueba.pedido ped, ABMPrueba.empleado emp, ABMPrueba.producto prod where emp.idempleado = ped.idempleado and ped.idproducto = prod.idproducto "+where+";");
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                ped.add(new Pedido(resultado.getInt("idpedido"),resultado.getInt("idempleado"),resultado.getString("nombre"),resultado.getInt("idproducto"),resultado.getString("descripcion"),resultado.getInt("precio"),resultado.getInt("cantidad"),resultado.getString("fecha"),resultado.getInt("usuarioid_creacion"),resultado.getString("fecha_creacion")));
                
            }
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
        return ped;
    }
}
