package MainGUI;

import javafx.scene.layout.Pane;

public class FechaException extends Exception{
  public FechaException(Pane error){
    error.setVisible(true);
  }
}
