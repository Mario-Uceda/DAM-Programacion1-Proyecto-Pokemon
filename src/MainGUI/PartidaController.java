package MainGUI;

import BBDD.ConexionBBDD;
import Personaje.Personaje;
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
import javafx.stage.Stage;
/**
 * 
 * @author mario y carlos
 */
public class PartidaController implements Initializable {

  @FXML
  private Button VOLVER;
  @FXML
  private TableColumn<Personaje, String> GENERO;
  @FXML
  private TableColumn<Personaje, String> NOMBRE;
  @FXML
  private TableColumn<Personaje, Integer> POKECUARTOS;
  @FXML
  private TableView<Personaje> tabla;
  private ObservableList<Personaje> datos;
  private int posicionTabla;
  @FXML
  private TextField nombre;
  @FXML
  private TextField genero;
  @FXML
  private TextField dia;
  @FXML
  private TextField mes;
  @FXML
  private TextField año;
  @FXML
  private TextField pokeball;
  @FXML
  private TextField pocion;
  @FXML
  private TextField pokecuarto;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    rellenarTabla();
    actualizardatos();
  }

  /**
   * Metodo para guardar la partida
   *
   * @param event
   */
  @FXML
  private void guardarpartida(ActionEvent event) {
    MenuController.obtenerPersonaje().writeToDB(new ConexionBBDD());
    MenuController.getPartidas().add(MenuController.obtenerPersonaje());
    rellenarTabla();
  }

  /**
   * Metodo para volver al menu
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
   * Metodo para cargar partida
   *
   * @param event
   */
  @FXML
  private void cargarpartida(ActionEvent event) {
    posicionTabla = datos.indexOf(getTablaPersonajesSeleccionada());
    int Id = MainGUI.MenuController.getPartidas().get(posicionTabla).getIdPartida();
    String Nombre = MainGUI.MenuController.getPartidas().get(posicionTabla).getNombre();
    String Genero = MainGUI.MenuController.getPartidas().get(posicionTabla).getGenero();
    int Dia = MainGUI.MenuController.getPartidas().get(posicionTabla).getDia();
    int Mes = MainGUI.MenuController.getPartidas().get(posicionTabla).getMes();
    int Año = MainGUI.MenuController.getPartidas().get(posicionTabla).getAño();
    int Pokecuartos = MainGUI.MenuController.getPartidas().get(posicionTabla).getPokecuartos();
    int Pociones = MainGUI.MenuController.getPartidas().get(posicionTabla).getMochila().getPociones();
    int Pokeballs = MainGUI.MenuController.getPartidas().get(posicionTabla).getMochila().getPokeballs();
    MainGUI.MenuController.moverPersonaje(new Personaje(Id, Nombre, Genero, Dia, Mes, Año, Pokecuartos, Pociones, Pokeballs));
    MainGUI.MenuController.obtenerPersonaje().readFromDB(new ConexionBBDD());
    actualizardatos();
    System.out.println("Partida cargada");
  }

  /**
   * Metodo para rellenar la tabla
   */
  private void rellenarTabla() {
    datos = FXCollections.observableArrayList(MainGUI.MenuController.getPartidas());
    tabla.setItems(datos);
    NOMBRE.setCellValueFactory(new PropertyValueFactory<Personaje, String>("nombre"));
    GENERO.setCellValueFactory(new PropertyValueFactory<Personaje, String>("genero"));
    POKECUARTOS.setCellValueFactory(new PropertyValueFactory<Personaje, Integer>("Pokecuartos"));
  }

  /**
   * metodo para obtener la posicion de la tabla que estoy clicando
   *
   * @return
   */
  public Personaje getTablaPersonajesSeleccionada() {
    if (tabla != null) {
      List<Personaje> tablaPersonajes = tabla.getSelectionModel().getSelectedItems();
      if (tablaPersonajes.size() == 1) {
        final Personaje competicionSeleccionada = tablaPersonajes.get(0);
        return competicionSeleccionada;
      }
    }
    return null;
  }

  /**
   * Metodo para mostrar los datos del personaje
   */
  public void actualizardatos() {
    nombre.setText(MenuController.obtenerPersonaje().getNombre());
    nombre.setEditable(false);
    genero.setText(MenuController.obtenerPersonaje().getGenero());
    genero.setEditable(false);
    dia.setText(Integer.toString(MenuController.obtenerPersonaje().getDia()));
    dia.setEditable(false);
    mes.setText(Integer.toString(MenuController.obtenerPersonaje().getMes()));
    mes.setEditable(false);
    año.setText(Integer.toString(MenuController.obtenerPersonaje().getAño()));
    año.setEditable(false);
    pokeball.setText(Integer.toString(MenuController.obtenerPersonaje().getMochila().getPokeballs()));
    pokeball.setEditable(false);
    pocion.setText(Integer.toString(MenuController.obtenerPersonaje().getMochila().getPociones()));
    pocion.setEditable(false);
    pokecuarto.setText(Integer.toString(MenuController.obtenerPersonaje().getPokecuartos()));
    pokecuarto.setEditable(false);
  }
}