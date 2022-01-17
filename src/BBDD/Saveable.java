package BBDD;

/**
 *
 * @author MARIO Y CARLOS
 */
public interface Saveable {

  void readFromDB(ConexionBBDD con);

  boolean writeToDB(ConexionBBDD con);

}
