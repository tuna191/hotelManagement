package Model.Customer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.JDBC;
public class CustomerImplement extends UnicastRemoteObject implements CustomerInterface{
    private static final long serialVersionUID = 1L;
    public CustomerImplement() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }

    @Override
    public List<Customer> getCustomer(String idcustomer) throws RemoteException {
        List<Customer> customer = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBC.getConnection();
            String sql = "SELECT * FROM customer where idcustomer = " + idcustomer;
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Customer customers = new Customer();
                customers.setIdcustomer(rs.getString("idcustomer"));
                customers.setName(rs.getString("name"));
                customers.setAddress(rs.getString("address"));
                customers.setSex(rs.getString("sex"));
                customers.setIdentify(rs.getInt("identify"));
                customers.setPhone(rs.getInt("phone"));
                customer.add(customers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomer() throws RemoteException {
        List<Customer> customer = new ArrayList<Customer>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = JDBC.getConnection();
            String sql = "SELECT * FROM customer";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Customer customers = new Customer();
                customers.setIdcustomer(rs.getString("idcustomer"));
                customers.setName(rs.getString("name"));
                customers.setAddress(rs.getString("address"));
                customers.setSex(rs.getString("sex"));
                customers.setIdentify(rs.getInt("identify"));
                customers.setPhone(rs.getInt("phone"));
                customer.add(customers);
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return customer;
    }

    @Override
    public boolean addCustomer(Customer customer) throws RemoteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean result = false;
        
        try {
            conn = JDBC.getConnection();
            String sql = "INSERT INTO customer Values(?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,customer.getIdcustomer() );
            stmt.setString(2,customer.getName() );
            stmt.setString(3,customer.getAdress() );
            stmt.setString(4,customer.getSex() );
            stmt.setInt(5,customer.getIdentify() );
            stmt.setInt(6,customer.getPhone() );
            int rowsAffected = stmt.executeUpdate();
            result = rowsAffected > 0;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return result;
    }

    public boolean removeCustomer(String idcustomer) throws RemoteException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            conn = JDBC.getConnection();
            String sql = "DELETE FROM customer WHERE idcustomer = " + idcustomer;
            stm = conn.prepareStatement(sql);
            int rowsAffected = stm.executeUpdate();
            result = rowsAffected > 0;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
        return result;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws RemoteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean result = false;
        try {
            conn = JDBC.getConnection();
            String sql = "UPDATE customer SET name = ? , address = ? , sex = ?,identify = ?,phone = ? WHERE idcustomer = ? "; 
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,customer.getName());
            stmt.setString(2,customer.getAdress());
            stmt.setString(3,customer.getSex());
            stmt.setInt(4,customer.getIdentify());
            stmt.setInt(5,customer.getPhone());
            stmt.setString(6,customer.getIdcustomer());

            int rowsAffected = stmt.executeUpdate();
            result = rowsAffected > 0;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return result;
    }
    
}


