package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    public static Connection getConnection;
    static {
        try{
            Class.forName("org.postgresql.Driver");
            DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","123456");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
