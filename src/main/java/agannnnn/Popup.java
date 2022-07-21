package agannnnn;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class Popup {
  public static void throwError(String title, String message, Window window) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setResizable(false);
    alert.setTitle(title);
    alert.setContentText(message);
    alert.initOwner(window);
    alert.show();
  }

  public static void throwInfo(String title, String message, Window window) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setResizable(false);
    alert.setTitle(title);
    alert.setContentText(message);
    alert.initOwner(window);
    alert.show();
  }
}
