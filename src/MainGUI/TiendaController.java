/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainGUI;

//import static Personaje.Personaje.mostrarPersonaje;
import static MainGUI.MenuController.obtenerPersonaje;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mario
 */
public class TiendaController implements Initializable {

  @FXML
  private TextField CANTIDADPOKEBALL;
  @FXML
  private TextField CANTIDADPOCIONES;
  @FXML
  private Button VOLVER;
  @FXML
  private TextField POKECUARTOS;
  Personaje personaje; 
  @FXML
  private Pane ERROR1;
  @FXML
  private Pane ERROR2;
  int pokeball;
  int pociones;
  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    personaje = obtenerPersonaje();
    POKECUARTOS.setText(Integer.toString(personaje.getPokecuartos()));
    POKECUARTOS.setEditable(false);
    ERROR1.setVisible(false);
    ERROR2.setVisible(false);
    pokeball=0;
    pociones=0;
  }  

  @FXML
  private void VOLVER(ActionEvent event) {
    Stage stage = (Stage) VOLVER.getScene().getWindow();
    try{
      FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("Menu.fxml"));
      Parent menu= (Parent)fxmlLoader.load();
      Scene scene = new Scene(menu);
      scene.getStylesheets().add(getClass().getResource("Wallpaper.css").toExternalForm());
      stage.setScene(scene);
      stage.show();
    } catch (IOException ex) {
      Logger.getLogger(PersonajeController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  @FXML
  private void COMPRAR(ActionEvent event) {
    try{
      if(CANTIDADPOKEBALL.getText().equals("")){
        pokeball=0;
      }else{
      pokeball=Integer.parseInt(CANTIDADPOKEBALL.getText());
      }
      if(CANTIDADPOCIONES.getText().equals("")){
        pociones=0;
      }else{
      pociones=Integer.parseInt(CANTIDADPOCIONES.getText());
      }
      int total=(pokeball*100)+(pociones*50);
      if( total<=personaje.getPokecuartos() ) {
        personaje.setPokecuartos(-total);
        personaje.getMochila().setPokeballs(pokeball);
        personaje.getMochila().setPociones(pociones);
        POKECUARTOS.setText(Integer.toString(personaje.getPokecuartos()));
        POKECUARTOS.setEditable(false);
      }else{
        ERROR2.setVisible(true);
      }
    }catch(Exception e){
      ERROR1.setVisible(true);
    }
  }

  @FXML
  private void cerrar(ActionEvent event) {
    ERROR1.setVisible(false);
    ERROR2.setVisible(false);
  }
}
