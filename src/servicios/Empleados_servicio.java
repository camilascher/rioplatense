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
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("SELECT idempleado,nombre from ABMPrueba.empleado ORDER BY nombre");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                emp.add(new Empleado(resultado.getInt("idEmpleado"), resultado.getString("nombre")));

            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return emp;
    }

    public Empleado recuperarEmpPorDescripcion(String empleado) throws SQLException {
        Empleado emp = null;
        try {
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("SELECT idempleado,nombre from ABMPrueba.empleado where nombre='" + empleado + "';");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                emp = new Empleado(resultado.getInt("idempleado"), resultado.getString("nombre"));
            }

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return emp;
    }
}
