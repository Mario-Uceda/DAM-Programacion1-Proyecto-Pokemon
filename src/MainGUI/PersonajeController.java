package MainGUI;

import BBDD.ConexionBBDD;
import static MainGUI.MenuController.inicializarTablaPersonas;
import static MainGUI.MenuController.moverPersonaje;
import Personaje.Personaje;
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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author mario y carlos
 */
public class PersonajeController implements Initializable {

  @FXML
  private TextField NOMBRE;
  @FXML
  private TextField DIA;
  @FXML
  private TextField MES;
  @FXML
  private TextField AÑO;
  @FXML
  private Button CREAR;
  @FXML
  private ToggleGroup genero;
  @FXML
  private ToggleButton Hombre;
  @FXML
  private ToggleButton Mujer;
  @FXML
  private Pane error1;
  @FXML
  private Pane error2;

  /**
   * Este metodo es para inicializar ciertos aspectos de la ventana.
   *
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    Hombre.setStyle("-fx-base: lightgreen;");
    Mujer.setStyle("-fx-base: White;");
    error1.setVisible(false);
    error2.setVisible(false);
  }

  /**
   * Metodo para crear el personaje cuando pulsas el boton
   *
   * @param event
   */
  @FXML
  private void crearPersonaje(ActionEvent event) {
    //comprobamos si es un chico, una chica, y si no selecciona nada un helicoptero de comabate.
    String genero = "Helicóptero de Combate";
    if (Hombre.isSelected()) {
      genero = "Hombre";
    } else if (Mujer.isSelected()) {
      genero = "Mujer";
    }
    //comprobamos los datos de los textfield.
    //si algun textfield esta vacio, salta un error.
    if ((NOMBRE.getText().equals("")) || (DIA.getText().equals("")) || (MES.getText().equals(""))
      || (AÑO.getText().equals(""))) {
      error1.setVisible(true);
      //comprobamos que la fecha es correcta.
    } else if (Integer.parseInt(AÑO.getText()) < 1900 || Integer.parseInt(AÑO.getText()) > 2019
      || Integer.parseInt(MES.getText()) < 1 || Integer.parseInt(MES.getText()) > 12
      || Integer.parseInt(DIA.getText()) < 1 || Integer.parseInt(DIA.getText()) > 31
      || ((Integer.parseInt(AÑO.getText()) % 4 == 0) && (Integer.parseInt(MES.getText()) == 2)
      && (Integer.parseInt(DIA.getText()) > 29)) || ((Integer.parseInt(AÑO.getText()) % 4 != 0)
      && (Integer.parseInt(MES.getText()) == 2) && (Integer.parseInt(DIA.getText()) > 28))
      || (((Integer.parseInt(MES.getText()) == 4) || (Integer.parseInt(MES.getText()) == 6)
      || (Integer.parseInt(MES.getText()) == 9) || (Integer.parseInt(MES.getText()) == 11))
      && (Integer.parseInt(DIA.getText()) > 30))) {
      error2.setVisible(true);
      //si todos los datos son correctos procedemos a crear el personaje
    } else {
      //pasamos los datos de los textfield a variables.
      String nombre = NOMBRE.getText();
      int dia = Integer.parseInt(DIA.getText());
      int mes = Integer.parseInt(MES.getText());
      int año = Integer.parseInt(AÑO.getText());
      //creamos el personaje.
      Personaje personaje = new Personaje(nombre, genero, dia, mes, año);
      //inicializo todos los personajes de la base ded datos, esto sirve para despues poder cargar partidas.
      inicializarTablaPersonas(new ConexionBBDD());
      //este metodo es un método estatico creado en el menu, sirve para poder mover el personaje en cualquier ventana.
      moverPersonaje(personaje);
      //estas lineas son para cambiar la ventana
      Stage stage = (Stage) CREAR.getScene().getWindow();
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
  }

  /**
   * Este metodo es para saber si esta seleccionado el boton del chico
   *
   * @param event
   */
  @FXML
  private void FondoBotonHombre(ActionEvent event) {
    if (Hombre.isSelected()) {
      Hombre.setStyle("-fx-base: lightgreen;");
      Mujer.setStyle("-fx-base: white;");
    } else {
      Hombre.setStyle("-fx-base: white;");
    }
  }

  /**
   * Este metodo es para saber si esta seleccionado el boton de la chica
   *
   * @param event
   */
  @FXML
  private void FondoBotonMujer(ActionEvent event) {
    if (Mujer.isSelected()) {
      Mujer.setStyle("-fx-base: lightgreen;");
      Hombre.setStyle("-fx-base: white;");
    } else {
      Mujer.setStyle("-fx-base: white;");
    }
  }

  /**
   * Este metodo es para cerrar las advertencias que salen cuando no hemos introducido los datos del
   * personaje correctamente
   *
   * @param event
   */
  @FXML
  private void cerrar(ActionEvent event) {
    error1.setVisible(false);
    error2.setVisible(false);
  }

}