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
public class Empleado {

    private final Integer idEmpleado;
    private final String nombre;
    private final Integer dni;
    private final String tarjeta;
    private final Double bonificacion;
    private final Integer bonif_tope;

    public Empleado() {
        this.idEmpleado = null;
        this.nombre = null;
        this.dni = null;
        this.tarjeta = null;
        this.bonificacion = 0.0;
        this.bonif_tope = null;
    }

    public Empleado(Integer idEmpleado, String nombre,Integer dni,String tarjeta, Double bonificacion, Integer bonif_tope) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.dni = dni;
        this.tarjeta = tarjeta;
        this.bonificacion = bonificacion;
        this.bonif_tope = bonif_tope;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombre;
    }
    
    public Integer getDniEmpleado(){
        return dni;
    }
    
    public String getTarjetaEmpleado(){
        return tarjeta;
    }
    
    public Double getBonificacion(){
        return bonificacion;
    }
    
    public Integer getBonifTope(){
        return bonif_tope;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
