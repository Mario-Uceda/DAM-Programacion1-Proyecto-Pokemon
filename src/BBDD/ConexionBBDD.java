package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mario y carlos
 */
public class ConexionBBDD {

  //variables
  Connection con;

  /**
   * Constructor
   */
  public ConexionBBDD() {
    try {
      con = DriverManager.getConnection("jdbc:mysql://localhost/Pokemon?serverTimezone=UTC", "root", "");
    } catch (SQLException ex) {
      Logger.getLogger(ConexionBBDD.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  /**
   * Getter para obtener la conexión del nuevo objeto
   *
   * @return conexion
   */
  public Connection getCon() {
    return con;
  }
}
