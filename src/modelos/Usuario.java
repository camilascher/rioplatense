/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author cami
 */
public class Usuario {
    private final Integer idUsuario;
    private String nombre;
    private String clave;
    
    public Usuario(){
        this.idUsuario = null;
        this.nombre = null;
        this.clave = null;
    }
    
    public Usuario(Integer idUsuario, String nombre, String clave){
        this.idUsuario = idUsuario;
        this.nombre  = nombre;
        this.clave = clave;
    }
    
    public Integer getIdUsuario(){
        return idUsuario;
    }
    
    public String getNombreUsuario(){
        return nombre;
    }
    
    public String getClaveUsuario(){
        return clave;
    }
    
}
