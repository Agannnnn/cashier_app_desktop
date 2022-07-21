package agannnnn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
  private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/projek_kasir_java";
  private static final String DB_USERNAME = "root";
  private static final String DB_PASSWORD = "";

  public static boolean doQuery(String query) throws SQLException {
    Connection connection = getConnection();
    Statement statement = connection.createStatement();
    return statement.execute(query);
  }

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
  }
}
