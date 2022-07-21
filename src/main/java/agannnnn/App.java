package agannnnn;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static String companyName = "Restoran Do'a Emak";
    public static String companyAddress = "Jl. Merpati No.18, Bintaro, Jakarta";
    public static String companyContact = "021-7488888";

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        scene = new Scene(loadFXML("pesanan"), 600, 400);
        stage.setTitle("Aplikasi Kasir | Login");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml, String title) throws IOException {
        stage.setTitle("Aplikasi Kasir | " + title);
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}