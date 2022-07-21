module agannnnn {
    opens agannnnn to javafx.fxml;

    exports agannnnn;

    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;

}
