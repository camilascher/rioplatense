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
import modelos.Empresa;
import modelos.Parametros;

/**
 *
 * @author Cami
 */
public class Empresas_servicio {
    private static Empresas_servicio instance = null;

    protected Empresas_servicio() {
        //Evita que la clase se instancie
    }

    public static Empresas_servicio getInstance() {
        if (instance == null) {
            instance = new Empresas_servicio();
        }
        return instance;
    }
    
     public List <Empresa> recuperarTodas() {
        List <Empresa> empr= new ArrayList<>();
        PreparedStatement consulta;
        try {
            consulta = Conexion.getConnection().prepareStatement("SELECT * FROM rioplatense.empresa;");
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
            empr.add(new Empresa(resultado.getInt("idempresa"),resultado.getString("nombre")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Parametros_servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empr;        
    }
     
    public String crearEmpresa(String nombre){
        String a = "INSERT INTO rioplatense.EMPRESA (nombre) values ('"+nombre+"');";

        try {
            Conexion.getConnection().setAutoCommit(false);
            PreparedStatement insert = Conexion.getConnection().prepareStatement(a);
            insert.executeUpdate();
            Conexion.getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(Pedidos_servicio.class.getName()).log(Level.SEVERE, null, ex);
            return ex.getMessage();
        }
        return "OK";        
    }
    
}
