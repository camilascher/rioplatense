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
    private static Usuarios_servicio instance = null;
    private String usuarioLogeado;
    
    protected Usuarios_servicio(){
        //Evita que la clase se instancie
    }
    
    public static Usuarios_servicio getInstance(){
        if (instance == null){
            instance = new Usuarios_servicio();
        }
        return instance;
    }
    
    public List<Usuario> recuperarTodas() throws SQLException{
        List<Usuario> usuarios = new ArrayList<>();
        try{
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("SELECT idusuario,nombre,clave from ABMPrueba.usuario ORDER BY nombre");
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                usuarios.add(new Usuario(resultado.getInt("idusuario"),resultado.getString("nombre"),resultado.getString("clave")));
                
            }
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
        return usuarios;
    }
            
    public Usuario recuperarUsuario(String nombre) throws SQLException{
        Usuario usuario = null;
        try{
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("SELECT idusuario,nombre,clave from ABMPrueba.usuario WHERE nombre='"+nombre+"'");
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                usuario = new Usuario(resultado.getInt("idusuario"),resultado.getString("nombre"),resultado.getString("clave"));
            }
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
        return usuario;
    }

    public String getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(String usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }
}
