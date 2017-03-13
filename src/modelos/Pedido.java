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
public class Pedido {
    private final Integer idPedido;
    private final Integer idEmpleado;
    private final String nombreEmpleado;
    private final Integer idProducto;
    private final String fecha;
    private final Integer idUsuarioCreacion;
    private final String fechaCreacion;
    
    public Pedido(){
        this.idPedido = null;
        this.idEmpleado = null;
        this.nombreEmpleado=null;
        this.idProducto = null;
        this.fecha = null;
        this.idUsuarioCreacion = null;
        this.fechaCreacion = null;
    }
    
    public Pedido(Integer idPedido,Integer idEmpleado,String nombreEmpleado,Integer idProducto, String fecha, Integer idUsuarioCreacion, String fechaCreacion){
        this.idPedido = idPedido;
        this.idEmpleado = idEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.idProducto = idProducto;
        this.fecha = fecha;
        this.idUsuarioCreacion = idUsuarioCreacion;
        this.fechaCreacion = fechaCreacion;
    }
    
    public Integer getIdPedido(){
        return idPedido;
    }
    
    public Integer getIdEmpleado(){
        return idEmpleado;
    }
    
    public String getNombreEmpleado(){
        return nombreEmpleado;
    }
    
    public Integer getIdProducto(){
        return idProducto;
    }
    
    public String getFecha(){
        return fecha;
    }
    
    public String getFechaCreacion(){
        return fechaCreacion;
    }
    
    public Integer getIdUsuarioCreacion(){
        return idUsuarioCreacion;
    }
    
}
