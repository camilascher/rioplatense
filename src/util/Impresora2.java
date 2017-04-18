/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Nico
 */
public class Impresora2 implements Impresora {

    private FileWriter fw;
    private BufferedWriter bw;
    private PrintWriter pw;
    private String dispositivo = "";

    public Impresora2(String texto) {
        if (texto == null) {
            dispositivo = "pantalla.txt";
        } else {
            dispositivo = texto;
        }
        try {
            fw = new FileWriter(dispositivo);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    public void escribir(String texto) {
        try {
            pw.print(texto + "\r\n");
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void finalizar() {
        try {
            pw.close();
            if (this.dispositivo.trim().equals("pantalla.txt")) {
                java.io.File archivo = new java.io.File("pantalla.txt");
                java.awt.Desktop.getDesktop().open(archivo);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
