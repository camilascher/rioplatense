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
import javax.swing.JOptionPane;
import servicios.Parametros_servicio;

/**
 *
 * @author Nico
 */
public class Impresora1 implements Impresora {

    private FileWriter fw;
    private BufferedWriter bw;
    private PrintWriter pw;
    private String dispositivo = "";

    public Impresora1(String texto) {
        if (texto == null) {
            dispositivo = Parametros_servicio.getInstance().recuperarValorPorCodigo("dispositivo");
        } else {
            dispositivo = "LPT1";
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
            pw.println(texto);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void cortar() {
        try {
            char[] chars = new char[]{0x1B, 'm'};
            if (!this.dispositivo.trim().equals("pantalla.txt")) {
                pw.write(chars);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void avanza_pagina() {
        try {
            if (!this.dispositivo.trim().equals("pantalla.txt")) {
                pw.write(0x0C);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void setRojo() {
        try {
            char[] chars = new char[]{0x1B, 'r', 1};
            if (!this.dispositivo.trim().equals("pantalla.txt")) {
                pw.write(chars);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void setNegro() {
        try {
            char[] chars = new char[]{0x1B, 'r', 0};
            if (!this.dispositivo.trim().equals("pantalla.txt")) {
                pw.write(chars);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void setTipoCaracterLatino() {
        try {
            char[] chars = new char[]{0x1B, 'R', 18};
            if (!this.dispositivo.trim().equals("pantalla.txt")) {
                pw.write(chars);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void setFormato(int formato) {
        try {
            char[] chars = new char[]{0x1B, '!', (char) formato};
            if (!this.dispositivo.trim().equals("pantalla.txt")) {
                pw.write(chars);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void correr(int fin) {
        try {
            int i = 0;
            for (i = 1; i <= fin; i++) {
                this.salto();
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void salto() {
        try {
            pw.println("");
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void dividir() {
        escribir("—————————————-");
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
