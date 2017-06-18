package DBConn; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
/**  *  * Java program to connect to MySQL Server database running on localhost, 
 * using JDBC type 4 driver.  *  * @author http://java67.blogspot.com  */ 

public class DBConn{ 
    public static void main(String args[]) { 
        String dbURL = "jdbc:mysql://localhost:3306/rioplatense";
        String username ="root"; 
        String password = "Camila88"; 
        Connection dbCon = null; 
        Statement stmt = null; 
        ResultSet rs = null;
       
        try {
            // String query ="select count(*) from rioplatense.producto";
            //try {
//getting database connection to MySQL server
dbCon = DriverManager.getConnection(dbURL, username, password);
//getting PreparedStatment to execute query
//stmt = dbCon.prepareStatement(query);
//Resultset returned by query
//rs = stmt.executeQuery(query); while(rs.next())
//{ int count = rs.getInt(1);
//System.out.println("count of stock : " + count); 
//}
//       }
//       catch (SQLException ex) {
//           System.out.println("ERROR");
// Logger.getLogger(CollectionTest.class.getName()).log(Level.SEVERE, null, ex);
//} 
        } catch (SQLException ex) {
            Logger.getLogger(DBConn.class.getName()).log(Level.SEVERE, null, ex);
        }
} 

} 
