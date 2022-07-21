package agannnnn;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Pelanggan {

  @FXML
  private TextArea alamat;

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
  private TextField nama;

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
  void kasirBtnHandler(ActionEvent event) {
    try {
      App.setRoot("kasir", "Kasir");
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
    if (id.getText().isEmpty() || nama.getText().isEmpty() || alamat.getText().isEmpty() || telp.getText().isEmpty()
        || telp.getText().length() < 3) {
      Popup.throwError("Error", "Isikan semua form dengan benar", display.getScene().getWindow());
    } else {
      String inputId = id.getText();
      String inputNama = nama.getText();
      String inputAlamat = alamat.getText();
      int inputStatus = status.getItems().indexOf(status.getValue());
      String inputTelp = telp.getText();
      if (!inputTelp.matches("[0-9]{3,}")) {
        Popup.throwError("Error", "Nomor telpon hanya berisikan angka", display.getScene().getWindow());
        return;
      }
      try {
        Database.doQuery(
            "INSERT INTO `pelanggan` (`ID`, `nama`, `telp`, `status`, `alamat`) VALUES ('" + inputId + "', '"
                + inputNama + "', '" + inputTelp + "', '" + inputStatus + "', '" + inputAlamat + "')");
        Popup.throwInfo("Tersimpan", "Data pelanggan telah berhasil disimpan", display.getScene().getWindow());
      } catch (SQLException e) {
        Popup.throwError("Error", "Terjadi error internal",
            display.getScene().getWindow());
      }
    }
  }

  @FXML
  void batalHandler(ActionEvent event) {
    nama.setText("");
    alamat.setText("");
    telp.setText("");
  }

  @FXML
  void initialize() {
    companyName.setText(App.companyName);
    companyAddress.setText(App.companyAddress);
    companyContact.setText(App.companyContact);

    status.getItems().setAll("Non-Member", "Member");

    String randomId = "";
    final String huruf = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    for (int i = 0; i < 12; i++) {
      randomId += huruf.charAt((int) (Math.random() * huruf.length()));
    }
    id.setText(randomId);
  }
}
