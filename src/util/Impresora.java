/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileInputStream;

/**
 *
 * @author Nico
 */
public interface Impresora {
    
    public void escribir(String texto);
    public java.io.File finalizar();

}
