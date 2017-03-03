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
public class Producto {
    private final Integer idProducto;
    private String descripcion;
    
    public Producto(){
        this.idProducto = null;
        this.descripcion = null;
    }
    
    public Producto(Integer idProducto, String descripcion){
        this.idProducto = idProducto;
        this.descripcion  = descripcion;
    }
    
    public Integer getIdProducto(){
        return idProducto;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString(){
        return "Producto{"+"idProducto"+"descripcion"+"}";
    }
            
}
