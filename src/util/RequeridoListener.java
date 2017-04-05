/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.event.*;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Nico
 */
public class RequeridoListener implements DocumentListener{
    JTextComponent comp = null;
    Border defaultBorder = null;
    Border highlightBorder = BorderFactory.createLineBorder(java.awt.Color.RED);
 
    public RequeridoListener(JTextComponent jtc) {
        comp = jtc;
        defaultBorder = comp.getBorder();
        // Adding this listener to a specified component:
        comp.getDocument().addDocumentListener(this);
        // Highlight if empty:
        this.debeResaltar();
    }
 
    public void insertUpdate(DocumentEvent e) {
        debeResaltar();
    }
 
    public void removeUpdate(DocumentEvent e) {
        debeResaltar();
    }
 
    public void changedUpdate(DocumentEvent e) {
        debeResaltar();
    }
 
    private void debeResaltar() {
        if (comp.getText().trim().length() != 0) {
            comp.setBorder(defaultBorder);
        } else {
            comp.setBorder(highlightBorder);
        }
    }    
}
