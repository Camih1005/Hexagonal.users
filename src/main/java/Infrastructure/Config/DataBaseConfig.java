package Infrastructure.Config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DataBaseConfig {
private static final String URL = "jdbc:mysql://monorail.proxy.rlwy.net:41954/railway";
private static final String USER = "root";
private static final String PASSWORD = "yhIkDwltmfMmOhFbQcAumFtJVjzlPOyQ";


public static Connection getConecction()throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);

}
}
