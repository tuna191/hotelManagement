package Controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import Model.Administrator.AdminInterface;
import Model.room.Room;
import Model.room.RoomInterface;
public class Client {
    private AdminInterface AdminMana ;
    private RoomInterface RoomMana;


    public boolean isConnected() {
        return RoomMana != null;
        
    }
    public Client(){
        try {
            Registry registry = LocateRegistry.getRegistry("localhost",1009);
            RoomMana = (RoomInterface) registry.lookup("Room");
            AdminMana = (AdminInterface) registry.lookup("Admin");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public boolean logIn(String name,String password){
        try {
            return AdminMana.logIn(name,password);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

    public boolean resetPassWord(String password){
        try {
            return AdminMana.resetPassWord(password);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }
    public String getPassWord(){
        try {
            return AdminMana.getPassWord();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }


    // rooms
        public List<Room> getAllRooms() {
        try {
            // Gọi phương thức từ server để lấy danh sách sản phẩm
            return RoomMana.getAllRoom();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addRoomClient(Room s){
        try {
            return RoomMana.addRoom(s);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateRoomClient(Room s){
        try {
            return RoomMana.updateData(s);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteRoomClient(String id){
        try {
            return RoomMana.deleteRoom(id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

    public List<Room> getRoomClient(String id){
        try {
            return RoomMana.getRoom(id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return null;
    } 
}

