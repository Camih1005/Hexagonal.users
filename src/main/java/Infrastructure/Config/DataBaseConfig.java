package Infrastructure.Config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DataBaseConfig {
private static final String URL = "jdbc:mysql://localhost:3306/ventasSafe";
private static final String USER = "campus2023";
private static final String PASSWORD = "campus2023";


public static Connection getConecction()throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);

}
}
