/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.Empleado;
import java.sql.ResultSet;

/**
 *
 * @author cami
 */
public class Empleados_servicio {
    private final String tabla = "empleado";
    public List<Empleado> recuperarTodas(Connection conexion) throws SQLException{
        List<Empleado> emp = new ArrayList<>();
        try{
            PreparedStatement consulta = conexion.prepareStatement("SELECT idempleado,nombre from ABMPrueba."+this.tabla+" ORDER BY nombre");
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                emp.add(new Empleado(resultado.getInt("idEmpleado"),resultado.getString("nombre")));
                
            }
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
        return emp;
    }
    
    public Empleado recuperarEmpPorDescripcion(Connection conexion,String empleado) throws SQLException{
        Empleado emp = null;
        try{
            PreparedStatement consulta = conexion.prepareStatement("SELECT idempleado,nombre from ABMPrueba."+this.tabla+" where nombre='"+empleado+"';");
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
            emp = new Empleado(resultado.getInt("idempleado"), resultado.getString("nombre"));
         }                          
            
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
        return emp;
    }
            
    
}
