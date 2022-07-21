package agannnnn;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Dashboard {

  @FXML
  private Text companyAddress;

  @FXML
  private Text companyContact;

  @FXML
  private Text companyName;

  @FXML
  private Pane display;

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
  void initialize() {
    companyName.setText(App.companyName);
    companyAddress.setText(App.companyAddress);
    companyContact.setText(App.companyContact);
  }

}
