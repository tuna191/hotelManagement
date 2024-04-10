package model.room;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class RoomImplement extends UnicastRemoteObject implements RoomInterface {
    private static final long serialVersionUID = 1L;

    public RoomImplement() throws RemoteException{

    }
    public static RoomImplement getInstance() throws RemoteException{
        return new RoomImplement();
    }

    @Override
    public List<Room> getAllRoom() throws RemoteException {
        List<Room> roomList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBC.getConnection();
            String sql = "SELECT * FROM room ";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Room room = new Room();
                room.setIdRoom(rs.getString("idRoom"));
                room.setKind(rs.getString("kind"));
                room.setState(rs.getString("state"));
                room.setPrice(rs.getDouble("price"));
                room.setIdCustomer(rs.getString("idCustomer"));
                room.setIdEmp(rs.getString("idEmp"));

                roomList.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    @Override
    public List<Room> getRoom(String idRoom) throws RemoteException {
        List<Room> roomList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBC.getConnection();
            String sql = "SELECT * FROM room where idRoom = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idRoom);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Room room = new Room();
                room.setIdRoom(rs.getString("idRoom"));
                room.setKind(rs.getString("kind"));
                room.setState(rs.getString("state"));
                room.setPrice(rs.getDouble("price"));
                room.setIdCustomer(rs.getString("idCustomer"));
                room.setIdEmp(rs.getString("idEmp"));

                roomList.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }
    @Override
    public boolean addRoom(Room room) throws RemoteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            conn = JDBC.getConnection();
            String sql = "INSERT INTO room(idRoom, kind, state, price, idCustomer, idEmp) VALUES (?,?, ?, ?, ? ,?)";
            stmt = conn.prepareStatement(sql);
                stmt.setString(1,room.getIdRoom());
                stmt.setString(2,room.getKind());
                stmt.setString(3,room.getState());
                stmt.setDouble(4,room.getPrice());
                stmt.setString(5,room.getIdCustomer());
                stmt.setString(6,room.getIdEmp());
            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public boolean updateData(Room s) throws RemoteException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            conn = JDBC.getConnection();
            String sql = "UPDATE room SET kind= ?,state= ?,price= ?,idCustomer= ?,idEmp= ? WHERE idRoom = ? ";
            stm = conn.prepareStatement(sql);
            stm.setString(1, s.getKind());
            stm.setString(2, s.getState());
            stm.setDouble(3, s.getPrice());
            stm.setString(4, s.getIdCustomer());
            stm.setString(5, s.getIdEmp());
            stm.setString(6, s.getIdRoom());


            int rowsAffected = stm.executeUpdate();
            result = rowsAffected > 0;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteRoom(String idRoom) throws RemoteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean success = false;

        try {
            conn = JDBC.getConnection();
            String sql = "DELETE FROM room WHERE idRoom = ? ";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, idRoom);
            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

}
