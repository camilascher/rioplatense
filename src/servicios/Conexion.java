/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Conexion {
   private static Connection cnx = null;
   
   protected Conexion(){
       //Evita que la clase se instancie
   }
   
    public static Connection getConnection()
    {
        if (cnx == null)
            cnx = obtener();
        return cnx;
    }
    
    private static Connection obtener() {
        Connection conn = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost/ABMPrueba", "root", "ciaber1");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        return conn;
    }
    
    public static void cerrar() {
        if (cnx != null) {
            try {
                cnx.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
