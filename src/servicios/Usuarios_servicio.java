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
import modelos.Usuario;
import java.sql.ResultSet;

/**
 *
 * @author Nico
 */
public class Usuarios_servicio {
    private final String tabla = "usuario";
    public List<Usuario> recuperarTodas(Connection conexion) throws SQLException{
        List<Usuario> usuarios = new ArrayList<>();
        try{
            PreparedStatement consulta = conexion.prepareStatement("SELECT idusuario,nombre,clave from ABMPrueba."+this.tabla+" ORDER BY nombre");
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                usuarios.add(new Usuario(resultado.getInt("idusuario"),resultado.getString("nombre"),resultado.getString("clave")));
                
            }
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
        return usuarios;
    }
            
    public Usuario recuperarUsuario(Connection conexion, String nombre) throws SQLException{
        Usuario usuario = null;
        try{
            PreparedStatement consulta = conexion.prepareStatement("SELECT idusuario,nombre,clave from ABMPrueba."+this.tabla+" WHERE nombre='"+nombre+"'");
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                usuario = new Usuario(resultado.getInt("idusuario"),resultado.getString("nombre"),resultado.getString("clave"));
            }
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
        return usuario;
    }
}
