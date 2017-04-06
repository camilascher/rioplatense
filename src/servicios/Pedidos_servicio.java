/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.Pedido;

/**
 *
 * @author cami
 */
public class Pedidos_servicio {
    private final String tabla = "pedido";
    public List<Pedido> recuperarTodasEnc(Connection conexion,String pedido, String fecha, String empleado) throws SQLException{
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
            PreparedStatement consulta = conexion.prepareStatement("SELECT distinct ped.idpedido,ped.idempleado,emp.nombre,ped.fecha from ABMPrueba."+this.tabla+" ped, ABMPrueba.empleado emp where emp.idempleado = ped.idempleado "+where+" ORDER BY fecha desc");
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                ped.add(new Pedido(resultado.getInt("idpedido"),resultado.getInt("idempleado"),resultado.getString("nombre"),null,resultado.getString("fecha"),null,null));
                
            }
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
        return ped;
    }
    
    public static String recuperarIdNuevoPed(Connection conexion) throws SQLException{
        String ped = "";
        
        try{
            PreparedStatement consulta = conexion.prepareStatement("select ifnull(max(idpedido),0)+1 idpedido from ABMPrueba.pedido;");
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                ped = resultado.getString("idpedido");
                
            }
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
        return ped;
    }
}
