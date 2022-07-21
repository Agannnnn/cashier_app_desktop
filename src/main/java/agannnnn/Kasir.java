package agannnnn;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Kasir {

  @FXML
  private Text companyAddress;

  @FXML
  private Text companyContact;

  @FXML
  private Text companyName;

  @FXML
  private Pane display;

  @FXML
  private TextField id;

  @FXML
  private ChoiceBox<String> jk;

  @FXML
  private TextField nama;

  @FXML
  private TextField password;

  @FXML
  private ChoiceBox<String> status;

  @FXML
  private TextField telp;

  @FXML
  void barangBtnHandler(ActionEvent event) {
    try {
      App.setRoot("barang", "Barang");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void pelangganBtnHandler(ActionEvent event) {
    try {
      App.setRoot("pelanggan", "Pelanggan");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void pesananBtnHandler(ActionEvent event) {
    try {
      App.setRoot("pesanan", "Pesanan");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void simpanHandler(ActionEvent event) {
    if (id.getText().isEmpty() || password.getText().isEmpty() || nama.getText().isEmpty()
        || telp.getText().isEmpty() || telp.getText().length() < 3) {
      Popup.throwError("Error", "Isikan semua form dengan benar", display.getScene().getWindow());
    } else {
      String inputId = id.getText();
      String inputPassword = password.getText();
      String inputNama = nama.getText();
      Character inputJk = jk.getItems().indexOf(jk.getValue()) == 0 ? 'L' : 'P';
      int inputStatus = status.getItems().indexOf(status.getValue());
      String inputTelp = telp.getText();
      if (!inputTelp.matches("[0-9]{3,}")) {
        Popup.throwError("Error", "Nomor telpon hanya berisikan angka", display.getScene().getWindow());
        return;
      }
      try {
        Database.doQuery(
            "INSERT INTO `kasir` (`ID`, `nama`, `password`, `telp`,`jk`, `status`) VALUES ('" + inputId + "', '"
                + inputNama + "', '" + inputPassword + "', '" + inputTelp + "','" + inputJk + "', '" + inputStatus
                + "')");
        Popup.throwInfo("Tersimpan", "Data kasir telah berhasil disimpan", display.getScene().getWindow());

      } catch (SQLException e) {
        Popup.throwError("Error", "Terjadi error internal", display.getScene().getWindow());
      }
    }
  }

  @FXML
  void batalHandler(ActionEvent event) {
    id.setText("");
    password.setText("");
    nama.setText("");
    telp.setText("");
  }

  @FXML
  void initialize() {
    companyName.setText(App.companyName);
    companyAddress.setText(App.companyAddress);
    companyContact.setText(App.companyContact);

    jk.getItems().addAll("Laki - laki", "Perempuan");
    status.getItems().addAll("Tidak Aktif", "Aktif");
  }
}
