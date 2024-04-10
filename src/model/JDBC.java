package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static final String DB_URL = "jdbc:mysql://localhost:3309/qlks";
    private static final String DB_USER = "root2";
    private static final String DB_PASSWORD = "123";

    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
