package MainGUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mario y carlos
 */
public class PokemonMarioCarlos extends Application {

  /**
   * metodo para inicializar una ventana y despues abrirla cuando deseemos en el main
   *
   * @param Stage
   * @throws Exception
   */
  @Override
  public void start(Stage Stage) throws Exception {
    try {
      Parent root = FXMLLoader.load(getClass().getResource("Personaje.fxml"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("Wallpaper.css").toExternalForm());
      Stage.setScene(scene);
      Stage.show();
    } catch (IOException ex) {
      System.err.println("Error al acceder al fichero: " + ex.toString());
    }
  }

  /**
   * main del programa
   *
   * @param args
   */
  public static void main(String[] args) {
    //abrimos la ventana que hemos guardado en el metodo start
    launch(args);
  }
}
