package agannnnn;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {

  @FXML
  private Button login;

  @FXML
  private PasswordField password;

  @FXML
  private TextField username;

  @FXML
  private void loginHandler() {
    if (username.getText().isEmpty()) {
      Popup.throwError("Error", "Isikan field username", username.getScene().getWindow());
      return;
    } else if (password.getText().isEmpty()) {
      Popup.throwError("Error", "Isikan field password", password.getScene().getWindow());
      return;
    } else {
      try {
        if (Database.doQuery("SELECT `nama` FROM `kasir` WHERE `ID` = '" + username.getText() + "' AND `password` = '"
            + password.getText() + "'")) {
          try {
            App.setRoot("dashboard", "Dashboard");
          } catch (IOException e) {
            Popup.throwError("Terjadi Error Internal", "Coba tutup lalu buka kembali aplikasi",
                login.getScene().getWindow());
          }
        }
      } catch (SQLException e1) {
        e1.printStackTrace();
        Popup.throwError("Gagal Login", "Username atau Password Salah!",
            login.getScene().getWindow());
      }
    }
  }

}
