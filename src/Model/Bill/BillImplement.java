package Model.Bill;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.JDBC;
public class BillImplement extends UnicastRemoteObject implements BillInterface {
    private static final long serialVersionUID = 1L;
    public BillImplement() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }

    /**
     * @param idBill
     * @return
     * @throws RemoteException
     */
    @Override
    public List<Bill> getBill(String idBill) throws RemoteException {
        List<Bill> bill = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBC.getConnection();
            String sql = "SELECT * FROM bill where idBill = " + idBill;
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Bill bills = new Bill();
                bills.setIdBill(rs.getString("idBill"));
                bills.setDateTT(rs.getDate("date"));
                bills.setPrice(rs.getDouble("price"));
                bills.setIdEmp(rs.getString("idEmp"));
               
                bill.add(bills);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }

    @Override
    public List<Bill> getAllBill() throws RemoteException {
        List<Bill> bill = new ArrayList<Bill>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = JDBC.getConnection();
            String sql = "SELECT * FROM bill ";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Bill bills = new Bill();
                bills.setIdBill(rs.getString("idBill"));
                bills.setDateTT(rs.getDate("date"));
                bills.setPrice(rs.getDouble("price"));
                bills.setIdEmp(rs.getString("idEmp"));
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return bill;
    }

    @Override
    public boolean addBill(Bill bill) throws RemoteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean result = false;
        
        try {
            conn = JDBC.getConnection();
            String sql = "INSERT INTO bill Values(?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,bill.getIdBill() );
            stmt.setDate(2,bill.getDateTT() );
            stmt.setDouble(3,bill.getPrice() );
            stmt.setString(4,bill.getIdEmp() );
            int rowsAffected = stmt.executeUpdate();
            result = rowsAffected > 0;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean removeBill(String idBill) throws RemoteException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            conn = JDBC.getConnection();
            String sql = "DELETE FROM bill WHERE idBill = " + idBill;
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
    public boolean updateBill(Bill bill) throws RemoteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean result = false;
        try {
            conn = JDBC.getConnection();
            String sql = "UPDATE bill SET date = ? , price = ?  WHERE idBill = ? "; 
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1,bill.getDateTT());
            stmt.setDouble(2,bill.getPrice());
            int rowsAffected = stmt.executeUpdate();
            result = rowsAffected > 0;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return result;
    }
    
}


