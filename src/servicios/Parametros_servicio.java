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
import modelos.Parametros;

/**
 *
 * @author Cami
 */
public class Parametros_servicio {

    private static Parametros_servicio instance = null;

    protected Parametros_servicio() {
        //Evita que la clase se instancie
    }

    public static Parametros_servicio getInstance() {
        if (instance == null) {
            instance = new Parametros_servicio();
        }
        return instance;
    }

    public String recuperarValorPorCodigo(String cod) {
        String valor=null;
        PreparedStatement consulta;
        try {
            consulta = Conexion.getConnection().prepareStatement("SELECT valor FROM rioplatense.parametros where codigo ='"+cod+"';");
            ResultSet resultado = consulta.executeQuery();
            resultado.next();
            valor = resultado.getString("valor");
            
        } catch (SQLException ex) {
            Logger.getLogger(Parametros_servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;        
    }
    
    public List <Parametros> recuperarTodos() {
        List <Parametros> par= new ArrayList<>();
        PreparedStatement consulta;
        try {
            consulta = Conexion.getConnection().prepareStatement("SELECT * FROM rioplatense.parametros where visible = 1;");
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
            par.add(new Parametros(resultado.getInt("idparametros"),resultado.getString("codigo"),resultado.getString("valor")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Parametros_servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return par;        
    }
    
    public void guardarParametro(String codigo, String valor) {
        String a = "UPDATE rioplatense.parametros SET valor='"+valor+"' where codigo='"+codigo+"';";

        try {
            PreparedStatement insert = Conexion.getConnection().prepareStatement(a);
            insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Pedidos_servicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
