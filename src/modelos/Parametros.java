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
public class Parametros {
    private final Integer idParametros;
    private final String codigo;
    private final String valor;
    
    public Parametros(){
        this.idParametros = null;
        this.codigo = null;
        this.valor = null;
    }
    
    public Parametros (Integer idParametros,String codigo,String valor){
        this.idParametros = idParametros;
        this.codigo = codigo;
        this.valor = valor;
    }
    
    public Integer getIdParametros(){
        return this.idParametros;
    }
    
    public String getCodigo(){
        return this.codigo;
    }
    
    public String getValor(){
        return this.valor;
    }
            
    
}
