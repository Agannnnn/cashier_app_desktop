package agannnnn;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Barang {

  @FXML
  private Text companyAddress;

  @FXML
  private Text companyContact;

  @FXML
  private Text companyName;

  @FXML
  private TextArea description;

  @FXML
  private Pane display;

  @FXML
  private TextField id;

  @FXML
  private TextField name;

  @FXML
  private TextField price;

  @FXML
  private CheckBox status;

  @FXML
  void kasirBtnHandler(ActionEvent event) {
    try {
      App.setRoot("kasir", "Kasir");
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
    if (id.getText().isEmpty() || name.getText().isEmpty() || price.getText().isEmpty()
        || description.getText().isEmpty()) {
      Popup.throwError("Error", "Isikan semua field", display.getScene().getWindow());
    } else {
      String inputId = id.getText();
      String inputName = name.getText();
      String inputPrice = price.getText();
      String inputDescription = description.getText();
      int inputAvailable = status.isSelected() ? 1 : 0; // 1 = Tersedia; 0 = Tidak

      try {
        Database.doQuery("INSERT INTO `barang` (`ID`, `nama`, `harga`, `deskripsi`, `status`) VALUES ('" + inputId
            + "', '" + inputName + "', '" + inputPrice + "', '" + inputDescription + "', '" + inputAvailable + "')");
        Popup.throwInfo("Berhasil", "Data telah berhasil ditambahkan", display.getScene().getWindow());
      } catch (SQLException e) {
        e.printStackTrace();
        Popup.throwError("Error", "Terjadi kesalahan internal", display.getScene().getWindow());
      }
    }
  }

  @FXML
  void batalHandler(ActionEvent event) {
    name.setText("");
    price.setText("");
    description.setText("");
  }

  @FXML
  void initialize() {
    companyName.setText(App.companyName);
    companyAddress.setText(App.companyAddress);
    companyContact.setText(App.companyContact);

    String randomId = "";
    final String huruf = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    for (int i = 0; i < 12; i++) {
      randomId += huruf.charAt((int) (Math.random() * huruf.length()));
    }
    id.setText(randomId);
  }
}
