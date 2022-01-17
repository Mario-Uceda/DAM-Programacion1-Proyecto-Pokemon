package MainGUI;

import Pokemon.Pokemon;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mario y carlos
 */
public class PokemonsController implements Initializable {

  @FXML
  private TableView<Pokemon> Tabla;
  @FXML
  private TableColumn<Pokemon, String> ESPECIE;
  @FXML
  private TableColumn<Pokemon, String> MOTE;
  @FXML
  private TableColumn<Pokemon, Integer> NIVEL;
  private ObservableList<Pokemon> datos;
  private int posicionTabla;
  @FXML
  private TextField PSMAXIMO;
  @FXML
  private TextField PSACTUAL;
  @FXML
  private TextField ATAQUE;
  @FXML
  private TextField DEFENSA;
  @FXML
  private TextField ATAQUEESPECIAL;
  @FXML
  private TextField DEFENSAACTUAL;
  @FXML
  private TextField VELOCIDAD;
  @FXML
  private TextField Mote;
  @FXML
  private TextField Especie;
  @FXML
  private TextField Nivel;
  @FXML
  private ImageView GIF;
  @FXML
  private Button VOLVER;

  /**
   * metodo para inicializar datos de la ventana
   *
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    datos = FXCollections.observableArrayList(MainGUI.MenuController.obtenerPersonaje().getMochila().getMochilaPokemons());
    Tabla.setItems(datos);
    ESPECIE.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("especie"));
    MOTE.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("mote"));
    NIVEL.setCellValueFactory(new PropertyValueFactory<Pokemon, Integer>("nivel"));
    Mote.setEditable(false);
    Especie.setEditable(false);
    Nivel.setEditable(false);
    PSMAXIMO.setEditable(false);
    PSACTUAL.setEditable(false);
    ATAQUE.setEditable(false);
    ATAQUEESPECIAL.setEditable(false);
    DEFENSA.setEditable(false);
    DEFENSAACTUAL.setEditable(false);
    VELOCIDAD.setEditable(false);
  }

  /**
   * metodo para mostrar las estadistcas de un pokemon
   *
   * @param event
   */
  @FXML
  private void MOSTRARPOKEMON(ActionEvent event) {
    final Pokemon pokemon = getTablaPokemonsSeleccionada();
    posicionTabla = datos.indexOf(pokemon);
    Mote.setText(pokemon.getMote());
    Mote.setEditable(false);
    Especie.setText(pokemon.getEspecie());
    Especie.setEditable(false);
    Nivel.setText(Integer.toString(pokemon.getNivel()));
    Nivel.setEditable(false);
    PSMAXIMO.setText(Integer.toString(pokemon.getPs()));
    PSMAXIMO.setEditable(false);
    PSACTUAL.setText(Integer.toString(pokemon.getPsActual()));
    PSACTUAL.setEditable(false);
    ATAQUE.setText(Integer.toString(pokemon.getAtaque()));
    ATAQUE.setEditable(false);
    ATAQUEESPECIAL.setText(Integer.toString(pokemon.getAtaqueEspecial()));
    ATAQUEESPECIAL.setEditable(false);
    DEFENSA.setText(Integer.toString(pokemon.getDefensa()));
    DEFENSA.setEditable(false);
    DEFENSAACTUAL.setText(Integer.toString(pokemon.getDefensaEspecial()));
    DEFENSAACTUAL.setEditable(false);
    VELOCIDAD.setText(Integer.toString(pokemon.getVelocidad()));
    VELOCIDAD.setEditable(false);
    switch (pokemon.getEspecie()) {
      case ("Charmander"):
        GIF.setImage(new Image("imagenes/Charmander.png"));
        break;
      case ("Mewtwo"):
        GIF.setImage(new Image("imagenes/Mewtwo.png"));
        break;
      case ("Pikachu"):
        GIF.setImage(new Image("imagenes/Pikachu.png"));
        break;
      case ("Rattata"):
        GIF.setImage(new Image("imagenes/Rattata.png"));
        break;
    }
  }

  /**
   * metodo para eliminar un pokemon de la tabla y de la mochila
   *
   * @param event
   */
  @FXML
  private void ELIMINARPOKEMON(ActionEvent event) {
    posicionTabla = datos.indexOf(getTablaPokemonsSeleccionada());
    datos.remove(posicionTabla);
    MainGUI.MenuController.obtenerPersonaje().getMochila().eliminarPokemon(posicionTabla);
  }

  /**
   * metodo para obtener la posicion de la tabla que estoy clicando
   *
   * @return posicion tabla
   */
  public Pokemon getTablaPokemonsSeleccionada() {
    if (Tabla != null) {
      List<Pokemon> tablaPokemon = Tabla.getSelectionModel().getSelectedItems();
      if (tablaPokemon.size() == 1) {
        final Pokemon competicionSeleccionada = tablaPokemon.get(0);
        return competicionSeleccionada;
      }
    }
    return null;
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

}
