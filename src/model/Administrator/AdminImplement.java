package model.Administrator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.JDBC;

public class AdminImplement extends UnicastRemoteObject implements AdminInterface {
    private static final long serialVersionUID = 1L;
    public AdminImplement() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }
    @Override
    public String getPassWord() throws RemoteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String result = null;
    
        try {
            conn = JDBC.getConnection();
            String sql = "SELECT password FROM account";
            stmt = conn.prepareStatement(sql);
    
            rs = stmt.executeQuery();
            if (rs.next()) { // Di chuyển con trỏ tới dòng đầu tiên của kết quả (nếu có)
                result = rs.getString("password");
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return result;
    }
    @Override
    public boolean resetPassWord(String password) throws RemoteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean result = false;
        try {
            conn = JDBC.getConnection();
            String sql = "UPDATE account SET password = " + password;
            stmt = conn.prepareStatement(sql);
            int rowsAffected = stmt.executeUpdate();
            result = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean logIn(String name, String password) throws RemoteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;
    
        try {
            conn = JDBC.getConnection();
            String sql = "SELECT name, password FROM account WHERE name = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name); // Thiết lập tham số cho câu truy vấn
    
            rs = stmt.executeQuery();
    
            if (rs.next()) { // Di chuyển con trỏ tới dòng đầu tiên của kết quả (nếu có)
                String dbPassword = rs.getString("password");
                if (dbPassword.equals(password)) {
                    result = true; // Đăng nhập thành công nếu password khớp
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng ResultSet, PreparedStatement và Connection trong phần finally
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    
        return result;
    }
    

}
