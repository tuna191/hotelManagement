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
public class BillDetailImplement extends UnicastRemoteObject implements BillDetailInterface {
    private static final long serialVersionUID = 1L;
    public BillDetailImplement() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }

    /**
     * @param idBill
     * @return
     * @throws RemoteException
     */
    @Override
    public List<BillDetail> getBillDetail(String idDetail) throws RemoteException {
        List<BillDetail> detailList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBC.getConnection();
            String sql = "SELECT * FROM detailBill where idDetail = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idDetail);
            rs = stmt.executeQuery();
            System.out.println(idDetail);
            while (rs.next()) {
                BillDetail details = new BillDetail();
             
              details.setIdDetail(rs.getString("idDetail"));
              details.setDate(rs.getDate("dateCheckin"));
              details.setIdRoom(rs.getString("idRoom"));
              details.setIdBill(rs.getString("idBill"));
              details.setIdCustomer(rs.getString("idCustomer"));
              details.setIdEmp(rs.getString("idEmp"));
               
                detailList.add(details);
            }
            System.out.println(detailList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detailList;
    }

    @Override
    public List<BillDetail> getAllBillDetail() throws RemoteException {
        List<BillDetail> detail = new ArrayList<BillDetail>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = JDBC.getConnection();
            String sql = "SELECT * FROM detailBill ";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                BillDetail details = new BillDetail();
                details.setIdDetail(rs.getString("idDetail"));
                details.setDate(rs.getDate("dateCheckin"));
                details.setIdRoom(rs.getString("idRoom"));
                details.setIdBill(rs.getString("idBill"));
                details.setIdCustomer(rs.getString("idCustomer"));
                details.setIdEmp(rs.getString("idEmp"));
              detail.add(details);
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return detail;
    }

    @Override
    public boolean addBillDetail(BillDetail detail) throws RemoteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean result = false;
        
        try {
            conn = JDBC.getConnection();
            String sql = "INSERT INTO detailBill Values(?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,detail.getIdDetail());
            stmt.setDate(2,detail.getDate());
            stmt.setString(3,detail.getIdRoom());
            stmt.setString(4,detail.getIdBill());
            stmt.setString(5,detail.getIdCustomer());
            stmt.setString(6,detail.getIdEmp());

            int rowsAffected = stmt.executeUpdate();
            result = rowsAffected > 0;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean removeBillDetail(String idDetail) throws RemoteException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            conn = JDBC.getConnection();
            String sql = "DELETE FROM detailBill WHERE idDetail = " + idDetail;
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
    public boolean updateBillDetail(BillDetail detail) throws RemoteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean result = false;
        try {
            conn = JDBC.getConnection();
            String sql = "UPDATE detailBill SET dateCheckin = ?  WHERE idDetail = ? "; 
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1,detail.getDate() );
            stmt.setString(2,detail.getIdDetail() );

            int rowsAffected = stmt.executeUpdate();
            result = rowsAffected > 0;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return result;
    }
    
}
