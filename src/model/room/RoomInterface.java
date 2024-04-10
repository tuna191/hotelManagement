package model.room;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RoomInterface extends Remote {
    List<Room> getAllRoom() throws RemoteException;
    List<Room> getRoom(String idRoom) throws RemoteException;
    boolean addRoom(Room Room) throws RemoteException;
    boolean updateData(Room Room) throws RemoteException;
    boolean deleteRoom(String idRoom) throws RemoteException;
}


