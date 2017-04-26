/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cami
 */
public class Pedido {
    private final Integer idPedido;
    private final Empleado empleado;
    private final String fecha;
    private final Double total;
    private final Double bonificacion;
    private final Integer eliminado;
    private final Usuario usuarioCreacion;
    private List<DetallePedido> detallesPedido;
    
    public Pedido(){
        this.idPedido = null;
        this.empleado = null;
        this.fecha = null;
        this.total = null;
        this.bonificacion = null;
        this.eliminado = null;
        this.usuarioCreacion = null;
        
    }
    
    public Pedido(Integer idPedido,Empleado empleado, String fecha, Double total,Double bonif,Integer elim, Usuario usuarioCreacion){
        this.idPedido = idPedido;
        this.empleado = empleado;
        this.fecha = fecha;
        this.total = total;
        this.bonificacion = bonif;
        this.eliminado = elim;
        this.usuarioCreacion = usuarioCreacion;
        this.detallesPedido = new ArrayList<>();
    }
    
    public Integer getIdPedido(){
        return idPedido;
    }
    
    public Empleado getEmpleado(){
        return empleado;
    }
    
    public String getFecha(){
        return fecha;
    }
    
    public Usuario getUsuarioCreacion(){
        return usuarioCreacion;
    }

    public List<DetallePedido> getDetallesPedido() {
        return detallesPedido;
    }
    
    public Double getTotal(){
        return total;
    }
    
    public Double getBonificacion(){
        return bonificacion;
    }
    
    public Integer getEliminado(){
        return eliminado;
    }

    public void setDetallesPedido(List<DetallePedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }
}
