/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Cami
 */
public class Empresa {
    private final Integer idEmpresa;
    private String nombre;
    
    public Empresa(){
        this.idEmpresa = null;
        this.nombre = null;
    }
    
    public Empresa(Integer id, String emp){
        this.idEmpresa = id;
        this.nombre = emp;
    }
    
    public Integer getIdEmpresa(){
        return idEmpresa;
    }
    
    public String getNombreEmpresa(){
        return nombre;
    }
    
    public void setNombreEmpresa(String nombre){
        this.nombre = nombre;
    }
    
}
