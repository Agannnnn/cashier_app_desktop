package agannnnn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Pesanan {

  private ArrayList<CartItem> orderArray = new ArrayList<CartItem>();
  @FXML
  private Text companyAddress;

  @FXML
  private Text companyContact;

  @FXML
  private Text companyName;

  @FXML
  private ChoiceBox<String> customerList;

  @FXML
  private DatePicker date;

  @FXML
  private Pane display;

  @FXML
  private TextField id;

  @FXML
  private ChoiceBox<String> itemList;

  @FXML
  private VBox orderList;

  @FXML
  private TextField qty;

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
  void tambahHandler(ActionEvent event) {
    if (itemList.getValue() == null) {
      Popup.throwError("Error", "Isikan semua form dengan benar", display.getScene().getWindow());
      return;
    } else {
      String[] item = itemList.getValue().split("\\|");
      try {
        orderArray.add(new CartItem(item[0].trim(), item[1].trim(),
            Integer.parseInt(qty.getText())));
      } catch (NumberFormatException e) {
        Popup.throwError("Error", "Qty hanya dapat diisi dengan nomor", display.getScene().getWindow());
      }
      orderList.getChildren().clear();
      orderArray.forEach(order -> {
        orderList.getChildren().add(new HBox(new Label(order.name), new Label(String.valueOf(order.qty))));
      });
    }
  }

  @FXML
  void simpanHandler(ActionEvent event) {
    if (date.getValue() == null || id.getText() == null || orderArray.size() < 1 || customerList.getValue() == null) {
      Popup.throwError("Error", "Isikan semua form dengan benar", display.getScene().getWindow());
      return;
    } else {
      String inputId = id.getText();
      String inputDate = date.getValue().toString();
      String inputCustomer = customerList.getValue().split("\\|")[0].trim();
      try (Connection connection = Database.getConnection()) {
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO `pesanan` (`ID`, `tanggal`, `pelanggan`) VALUES ('" + inputId + "', '"
            + inputDate + "', '" + inputCustomer + "')");
        System.out.println('A');
        for (CartItem order : orderArray) {
          statement = connection.createStatement();
          statement
              .execute("INSERT INTO `detail_pesanan` (`id_pesanan`, `barang`, `jumlah`) VALUES ('" + inputId
                  + "', '" + order.id + "', ' " + order.qty + " ')");
        }
        Popup.throwInfo("Berhasil", "Data pemesanan telah berhasil ditambahkan", display.getScene().getWindow());
      } catch (SQLException e) {
        e.printStackTrace();
        Popup.throwError("Error", "Terjadi error internal", display.getScene().getWindow());
      }
    }

  }

  @FXML
  void batalHandler(ActionEvent event) {
    date.setValue(null);
    qty.setText("0");
  }

  @FXML
  void initialize() {
    companyName.setText(App.companyName);
    companyAddress.setText(App.companyAddress);
    companyContact.setText(App.companyContact);

    try {
      Connection connection = Database.getConnection();
      Statement statement = connection.createStatement();
      ResultSet barang = statement.executeQuery("SELECT `ID`, `nama` FROM `barang` WHERE `status` = 1;");
      while (barang.next()) {
        itemList.getItems().add(barang.getString(1) + " | " + barang.getString(2));
      }
      ResultSet pelanggan = statement.executeQuery("SELECT `ID`, `nama` FROM `pelanggan`;");
      while (pelanggan.next()) {
        customerList.getItems().add(pelanggan.getString(1) + " | " + pelanggan.getString(2));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    String randomId = "";
    final String huruf = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    for (int i = 0; i < 12; i++) {
      randomId += huruf.charAt((int) (Math.random() * huruf.length()));
    }
    id.setText(randomId);
  }
}

class CartItem {
  String id;
  String name;
  int qty;

  public CartItem(String id, String name, int qty) {
    this.id = id;
    this.name = name;
    this.qty = qty;
  }
}