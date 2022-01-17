package MainGUI;

import BBDD.ConexionBBDD;
import Personaje.Personaje;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mario y carlos
 */
public class MenuController {

  @FXML
  private Button CombatePokemon;
  @FXML
  private Button Tienda;
  @FXML
  private Button Pokemons;
  @FXML
  private Button Partida;
  private static Personaje apollo;
  static ArrayList<Personaje> partidas = new ArrayList<Personaje>();

  /**
   * Metodo para abrir la pantalla de combate pokemon
   *
   * @param event
   */
  @FXML
  private void combatePokemon(ActionEvent event) {
    Stage stage = (Stage) CombatePokemon.getScene().getWindow();
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Combate.fxml"));
      Parent menu = (Parent) fxmlLoader.load();
      Scene scene = new Scene(menu);
      scene.getStylesheets().add(getClass().getResource("Wallpaper.css").toExternalForm());
      stage.setScene(scene);
      stage.show();
    } catch (IOException ex) {
      Logger.getLogger(PersonajeController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Metodo para abrir la pantalla de la tienda
   *
   * @param event
   */
  @FXML
  private void tienda(ActionEvent event) {
    Stage stage = (Stage) Tienda.getScene().getWindow();
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Tienda.fxml"));
      Parent menu = (Parent) fxmlLoader.load();
      Scene scene = new Scene(menu);
      scene.getStylesheets().add(getClass().getResource("Wallpaper.css").toExternalForm());
      stage.setScene(scene);
      stage.show();
    } catch (IOException ex) {
      Logger.getLogger(PersonajeController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Metodo para abrir la pantalla de listar pokemons
   *
   * @param event
   */
  @FXML
  private void pokemons(ActionEvent event) {
    Stage stage = (Stage) Pokemons.getScene().getWindow();
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Pokemons.fxml"));
      Parent menu = (Parent) fxmlLoader.load();
      Scene scene = new Scene(menu);
      scene.getStylesheets().add(getClass().getResource("Wallpaper.css").toExternalForm());
      stage.setScene(scene);
      stage.show();

    } catch (IOException ex) {
      Logger.getLogger(PersonajeController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Metodo para abrir la pantalla de cargar y guardar partidas
   *
   * @param event
   */
  @FXML
  private void partida(ActionEvent event) {
    Stage stage = (Stage) Partida.getScene().getWindow();
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Partida.fxml"));
      Parent menu = (Parent) fxmlLoader.load();
      Scene scene = new Scene(menu);
      scene.getStylesheets().add(getClass().getResource("Wallpaper.css").toExternalForm());
      stage.setScene(scene);
      stage.show();
    } catch (IOException ex) {
      Logger.getLogger(PersonajeController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Metodo para una vez creado el personaje moverlo a una variable auxiliar la cual es estatica y
   * así poder mover el personaje entre ventanas
   *
   * @param personaje
   */
  public static void moverPersonaje(Personaje personaje) {
    apollo = personaje;
  }

  /**
   * Metodo para una vez creado el personaje poder moverlo a cualquier ventana
   *
   * @return
   */
  public static Personaje obtenerPersonaje() {
    return apollo;
  }

  /**
   * * Metodo para guardar en un arraylist todos los personajes que hay en la base de datos para
   * poder mostralos luego en la tabla.
   *
   * @param con
   */
  public static void inicializarTablaPersonas(ConexionBBDD con) {
    Statement query;
    ResultSet result = null;
    try {
      query = con.getCon().createStatement();
      result = query.executeQuery("SELECT IdPartida,Pokecuartos,Pociones,Pokeballs,NombreJugador,GeneroJugador,DiaJugador,MesJugador,AñoJugador FROM Partida");
      while (result.next()) {
        int IdPartida = result.getInt("IdPartida");
        int pokecuartos = result.getInt("Pokecuartos");
        int pociones = result.getInt("Pociones");
        int pokeballs = result.getInt("Pokeballs");
        String nombre = result.getString("NombreJugador");
        String genero = result.getString("GeneroJugador");
        int dia = result.getInt("DiaJugador");
        int mes = result.getInt("MesJugador");
        int año = result.getInt("AñoJugador");
        partidas.add(new Personaje(IdPartida, nombre, genero, dia, mes, año, pokecuartos, pociones, pokeballs));
      }
      result.close();
      query.close();
      con.getCon().close();
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }

  /**
   * metodo para obtener el arraylist con todos los personajes de la base de datos
   *
   * @return
   */
  public static ArrayList<Personaje> getPartidas() {
    return partidas;
  }

  /**
   * metodo para añadir nuevos personajes al arraylist
   *
   * @param nuevo
   */
  public static void setPartidas(Personaje nuevo) {
    MenuController.partidas.add(nuevo);
  }

}
