/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelos.Producto;

public class Productos_servicio {

    private static Productos_servicio instance = null;

    protected Productos_servicio() {
        //Evita que la clase se instancie
    }

    public static Productos_servicio getInstance() {
        if (instance == null) {
            instance = new Productos_servicio();
        }
        return instance;
    }

    public void guardar(Producto prod) throws SQLException {
        try {
            PreparedStatement consulta;
            if (prod.getIdProducto() == null) {
                consulta = Conexion.getConnection().prepareStatement("INSERT INTO producto(idproducto, descripcion) VALUES(?, ?)");
                consulta.setInt(1, prod.getIdProducto());
                consulta.setString(2, prod.getDescripcion());
            } else {
                consulta = Conexion.getConnection().prepareStatement("UPDATE producto SET descripcion = ? WHERE idproducto = ?");
                consulta.setString(1, prod.getDescripcion());
                consulta.setInt(2, prod.getIdProducto());
            }
            consulta.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public Producto recuperarPorId(int id_prod) throws SQLException {
        Producto prod = null;
        try {
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("SELECT idproducto, descripcion, precio FROM producto WHERE idproducto = ?");
            consulta.setInt(1, id_prod);
            ResultSet resultado = consulta.executeQuery();
            if (!resultado.isBeforeFirst()){
                 return prod;
            }
            while (resultado.next()) {
                prod = new Producto(id_prod, resultado.getString("descripcion"), resultado.getDouble("precio"));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return prod;
    }

    public Producto recuperarPorDescripcion(String desc) throws SQLException {
        Producto prod = null;
        try {
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("SELECT idproducto, descripcion, precio FROM producto WHERE descripcion like ? ;");
            consulta.setString(1, desc+"%");
            ResultSet resultado = consulta.executeQuery();
            if (!resultado.isBeforeFirst()){
                 return prod;
            }
            while (resultado.next()) {
                prod = new Producto(resultado.getInt("idproducto"), resultado.getString("descripcion"), resultado.getDouble("precio"));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return prod;
    }
//   public void eliminar(Connection conexion, Producto prod) throws SQLException{
//      try{
//         PreparedStatement consulta = conexion.prepareStatement("DELETE FROM " + this.tabla + " WHERE id_prod = ?");
//         consulta.setInt(1, prod.getId_prod());
//         consulta.executeUpdate();
//      }catch(SQLException ex){
//         throw new SQLException(ex);
//      }
//   }

    public List<Producto> recuperarTodas() throws SQLException {
        List<Producto> prods = new ArrayList<>();
        try {
            PreparedStatement consulta = Conexion.getConnection().prepareStatement("SELECT idproducto, descripcion, precio FROM producto ORDER BY idproducto");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                prods.add(new Producto(resultado.getInt("idproducto"), resultado.getString("descripcion"), resultado.getDouble("precio")));
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return prods;
    }
}
