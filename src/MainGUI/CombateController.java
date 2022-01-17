package MainGUI;

import Pokemon.Charmander;
import Pokemon.Mewtwo;
import Pokemon.Pikachu;
import Pokemon.Pokemon;
import Pokemon.Rattata;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mario y carlos
 */
public class CombateController implements Initializable {

  @FXML
  private Button VOLVER;
  @FXML
  private Pane PANEL;
  @FXML
  private TextField POKEBALLS;
  @FXML
  private TextField ESPECIE;
  @FXML
  private TextField NIVEL;
  @FXML
  private Pane SINPOKEBALLS;
  @FXML
  private Pane CAPTURADO;
  private Pokemon enemigo;
  @FXML
  private Pane PONERMOTE;
  @FXML
  private TextField escribir;
  @FXML
  private Pane NOCAPTURADO;
  @FXML
  private ImageView FOTO;

  /**
   * metodo para inicializar la pantalla
   *
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
    cerrarVentanas();
  }

  /**
   * metodo para volver al menu
   *
   * @param event
   */
  @FXML
  private void VOLVER(ActionEvent event) {
    Stage stage = (Stage) VOLVER.getScene().getWindow();
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
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
   * metodo para que cuando pulses al boton capturar llame a la funcion capturarpokemon
   *
   * @param event
   */
  @FXML
  private void CAPTURAR(ActionEvent event) {
    capturarPokemon();
  }

  /**
   * metodo para crear un pokemon enemigo
   *
   * @return pokemon enemigo
   */
  private Pokemon PokemonEnemigo() {
    Pokemon enemigo = null;
    switch ((int) (Math.random() * 4) + 1) {
      case (1):
        enemigo = new Charmander((int) (Math.random() * 100) + 1);
        break;
      case (2):
        enemigo = new Mewtwo((int) (Math.random() * 100) + 1);
        break;
      case (3):
        enemigo = new Pikachu((int) (Math.random() * 100) + 1);
        break;
      case (4):
        enemigo = new Rattata((int) (Math.random() * 100) + 1);
        break;
    }
    return enemigo;
  }

  /**
   * Guarda el pokemon enemigo en una variable para utilizarlo cuando sea necesario actualiza el
   * numero de pokeballs de tu textfield Muestra la especie el nivel y una foto del pokemon
   * encontrado
   *
   * @param event
   */
  @FXML
  private void BUSCARPOKEMON(ActionEvent event) {
    setEnemigo(PokemonEnemigo());
    actualizarPokeballs();
    ESPECIE.setText(enemigo.getEspecie());
    ESPECIE.setEditable(false);
    NIVEL.setText(Integer.toString(enemigo.getNivel()));
    NIVEL.setEditable(false);
    PANEL.setVisible(true);
    switch (enemigo.getEspecie()) {
      case ("Charmander"):
        FOTO.setImage(new Image("imagenes/Charmander.png"));
        break;
      case ("Mewtwo"):
        FOTO.setImage(new Image("imagenes/Mewtwo.png"));
        break;
      case ("Pikachu"):
        FOTO.setImage(new Image("imagenes/Pikachu.png"));
        break;
      case ("Rattata"):
        FOTO.setImage(new Image("imagenes/Rattata.png"));
        break;
    }
  }

  /**
   * metodo para que se actualice la cantidad de pokeballs que tengo en el textfield
   */
  private void actualizarPokeballs() {
    POKEBALLS.setText(Integer.toString(MenuController.obtenerPersonaje().getMochila().getPokeballs()));
    POKEBALLS.setEditable(false);
  }

  /**
   * metodo para que cuando salga el panel de que no tengo pokeballs si pulso el boton cerrar cierre
   * el panel
   *
   * @param event
   */
  @FXML
  private void cerrar(ActionEvent event) {
    SINPOKEBALLS.setVisible(false);
  }

  /**
   * metodo para que si deseas ponerle un mote al pokemon te abra una ventana con un textfield
   *
   * @param event
   */
  @FXML
  private void MOTE(ActionEvent event) {
    PONERMOTE.setVisible(true);
  }

  /**
   * metodo para obtener el pokemon enemigo
   *
   * @return pokemon enemigo
   */
  public Pokemon getEnemigo() {
    return enemigo;
  }

  /**
   * metodo para guardar el pokemon enemigo
   *
   * @param enemigo
   */
  public void setEnemigo(Pokemon enemigo) {
    this.enemigo = enemigo;
  }

  /**
   * metodo para una vez capturado el pokemon no quieras ponerle mote
   *
   * @param event
   */
  @FXML
  private void no(ActionEvent event) {
    MenuController.obtenerPersonaje().getMochila().capturarPokemon(getEnemigo());
    cerrarVentanas();
  }

  /**
   * metodo para cuando quieres ponerle mote al pokemon guarde el mote del textfield si está vacio
   * que le ponga como mote el nombre de la especie
   *
   * @param event
   */
  @FXML
  private void ACEPTAR(ActionEvent event) {
    if (escribir.getText().equals("")) {
      MenuController.obtenerPersonaje().getMochila().capturarPokemon(getEnemigo());
      cerrarVentanas();
    } else {
      getEnemigo().setMote(escribir.getText());
      MenuController.obtenerPersonaje().getMochila().capturarPokemon(getEnemigo());
      cerrarVentanas();
    }
  }

  /**
   * metodo para cerrar todas las ventanas
   */
  private void cerrarVentanas() {
    PANEL.setVisible(false);
    SINPOKEBALLS.setVisible(false);
    CAPTURADO.setVisible(false);
    PONERMOTE.setVisible(false);
    NOCAPTURADO.setVisible(false);
  }

  /**
   * metodo para reintentar capturar el pokemon
   *
   * @param event
   */
  @FXML
  private void REINTENTAR(ActionEvent event) {
    NOCAPTURADO.setVisible(false);
    capturarPokemon();
  }

  /**
   * metodo que te resta una pokeball cada vez que lo uilizas comprueba si tienes pokeballs para
   * poder intentar capturar un pokemon si tienes pokeballs intenta capturar el pokemon y
   * dependiendo de si lo hace o no muestra una ventana u otra
   */
  private void capturarPokemon() {
    if (MenuController.obtenerPersonaje().getMochila().getPokeballs() > 0) {
      MenuController.obtenerPersonaje().getMochila().setPokeballs(-1);
      actualizarPokeballs();
      if ((int) (Math.random() * 3) + 1 == 1) {
        CAPTURADO.setVisible(true);
        MenuController.obtenerPersonaje().setPokecuartos(500);
      } else {
        NOCAPTURADO.setVisible(true);
      }
    } else {
      SINPOKEBALLS.setVisible(true);
    }
  }

  /**
   * metodo para que si no capturas el pokemon y quieres huir se cierre la ventana de captura
   *
   * @param event
   */
  @FXML
  private void HUIR(ActionEvent event) {
    cerrarVentanas();
  }

}