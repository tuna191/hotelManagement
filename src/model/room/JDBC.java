package model.room;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    // Thay đổi các thông số kết nối tương ứng với cấu hình của cơ sở dữ liệu MySQL của bạn
    private static final String DB_URL = "jdbc:mysql://localhost:3307/qlks";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Phương thức này được sử dụng để kết nối đến cơ sở dữ liệu
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
        return conn;
    }
    // Phương thức này được sử dụng để đóng kết nối cơ sở dữ liệu
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Failed to close the database connection.");
                e.printStackTrace();
            }
        }
    }
}
