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

    public Empleado() {
        this.idEmpleado = null;
        this.nombre = null;
    }

    public Empleado(Integer idEmpleado, String nombre) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombreEmpleado() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
