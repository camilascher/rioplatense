/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import Inicio.Pedidos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelos.Empleado;

/**
 *
 * @author cami
 */
public class Empleados_servicio {

    private static Empleados_servicio instance = null;

    protected Empleados_servicio() {
        //Evita que la clase se instancie
    }

    public static Empleados_servicio getInstance() {
        if (instance == null) {
            instance = new Empleados_servicio();
        }
        return instance;
    }

    public List<Empleado> recuperarTodas() throws SQLException {
        List<Empleado> emp = new ArrayList<>();
        try {
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("SELECT idempleado,nombre,dni,tarjeta,bonificado,bonif_tope from rioplatense.empleado ORDER BY nombre");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                emp.add(new Empleado(resultado.getInt("idEmpleado"), resultado.getString("nombre"), resultado.getInt("dni"), resultado.getString("tarjeta"), resultado.getDouble("bonificado"), resultado.getInt("bonif_tope")));

            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return emp;
    }

    public Empleado recuperarEmpPorDescripcion(String empleado) throws SQLException {
        Empleado emp = null;
        try {
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("SELECT idempleado,nombre,dni,tarjeta,bonificado,bonif_tope from rioplatense.empleado where nombre='" + empleado + "' ;");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                emp = new Empleado(resultado.getInt("idempleado"), resultado.getString("nombre"), resultado.getInt("dni"), resultado.getString("tarjeta"), resultado.getDouble("bonificado"), resultado.getInt("bonif_tope"));
            }

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return emp;
    }

    public Empleado recuperarEmpPorId(String id) throws SQLException {
        Empleado emp = null;
        try {
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("SELECT idempleado,nombre,dni,tarjeta,bonificado,bonif_tope from rioplatense.empleado where idempleado='" + id + "' and eliminado <> 1;");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                emp = new Empleado(resultado.getInt("idempleado"), resultado.getString("nombre"), resultado.getInt("dni"), resultado.getString("tarjeta"), resultado.getDouble("bonificado"), resultado.getInt("bonif_tope"));
            }

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return emp;
    }

    public Empleado recuperarEmpPorIdTarj(String id) throws SQLException {
        Empleado emp = null;
        try {
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("SELECT idempleado,nombre,dni,tarjeta,bonificado,bonif_tope from rioplatense.empleado where (idempleado='" + id + "' or tarjeta='" + id + "' or dni='" + id + "') and eliminado <> 1;");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                emp = new Empleado(resultado.getInt("idempleado"), resultado.getString("nombre"), resultado.getInt("dni"), resultado.getString("tarjeta"), resultado.getDouble("bonificado"), resultado.getInt("bonif_tope"));
            }

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return emp;
    }

    public void guardarEmpleado(Integer id, String nombre, Integer dni, String tarj, Double bonificado, Double bonif_tope, String tipo) {
        String tar = null;
        if (tarj != null) {
            tar = "'" + tarj + "'";
        }
        String a = "INSERT INTO rioplatense.empleado (idempleado, nombre, dni,tarjeta,bonificado, bonif_tope,eliminado,tipo) VALUES (" + id + ",'" + nombre + "'," + dni + ", " + tar + "," + bonificado + "," + bonif_tope + ",0,'"+tipo+"');";

        try {
            PreparedStatement insert = Conexion.getConnection().prepareStatement(a);
            Conexion.getConnection().setAutoCommit(false);
            insert.executeUpdate();
            Conexion.getConnection().commit();            
        } catch (SQLException ex) {
            Logger.getLogger(Pedidos_servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarEmpleado(Integer id){
        String a = "UPDATE rioplatense.empleado SET eliminado = 1 where idempleado = "+id+";";
        try {
            PreparedStatement insert = Conexion.getConnection().prepareStatement(a);
            Conexion.getConnection().setAutoCommit(false);
            insert.executeUpdate();
            Conexion.getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(Pedidos_servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
