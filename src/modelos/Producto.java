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
    private final Double precio;
    
    public Producto(){
        this.idProducto = null;
        this.descripcion = null;
        this.precio = 0.0;
    }
    
    public Producto(Integer idProducto, String descripcion, Double precio){
        this.idProducto = idProducto;
        this.descripcion  = descripcion;
        this.precio = precio;
    }
        
    public Integer getIdProducto(){
        return idProducto;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public Double getPrecio(){
        return precio;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString(){
        return descripcion;
    }
            
}
