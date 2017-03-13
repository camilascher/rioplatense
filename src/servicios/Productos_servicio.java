/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.Producto;
public class Productos_servicio {
   private final String tabla = "producto";
   public void guardar(Connection conexion, Producto prod) throws SQLException{
      try{
         PreparedStatement consulta;
         if(prod.getIdProducto() == null){
            consulta = conexion.prepareStatement("INSERT INTO " + this.tabla + "(idproducto, descripcion) VALUES(?, ?)");
            consulta.setInt(1, prod.getIdProducto());
            consulta.setString(2, prod.getDescripcion());
         }else{
            consulta = conexion.prepareStatement("UPDATE " + this.tabla + " SET descripcion = ? WHERE idproducto = ?");
            consulta.setString(1, prod.getDescripcion());
            consulta.setInt(2, prod.getIdProducto());
         }
         consulta.executeUpdate();
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
   }
   public Producto recuperarPorId(Connection conexion, int id_prod) throws SQLException {
      Producto prod = null;
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT idproducto, descripcion, precio FROM " + this.tabla + " WHERE idproducto = ?" );
         consulta.setInt(1, id_prod);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            prod = new Producto(id_prod, resultado.getString("descripcion"), resultado.getInt("precio"));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return prod;
   }
   
   public Producto recuperarPorDescripcion(Connection conexion, String desc) throws SQLException {
      Producto prod = null;
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT idproducto, descripcion, precio FROM " + this.tabla + " WHERE descripcion = ?" );
         consulta.setString(1, desc);
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            prod = new Producto(resultado.getInt("idproducto"), resultado.getString("descripcion"), resultado.getInt("precio"));
         }
      }catch(SQLException ex){
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
   public List<Producto> recuperarTodas(Connection conexion) throws SQLException{
      List<Producto> prods = new ArrayList<>();
      try{
         PreparedStatement consulta = conexion.prepareStatement("SELECT idproducto, descripcion, precio FROM " + this.tabla + " ORDER BY idproducto");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            prods.add(new Producto(resultado.getInt("idproducto"), resultado.getString("descripcion"), resultado.getInt("precio")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return prods;
   }
}