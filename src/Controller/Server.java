package Controller;

import java.rmi.registry.Registry;

import Model.Administrator.AdminImplement;
import Model.Administrator.AdminInterface;
import Model.room.Room;
import Model.room.RoomInterface;
import Model.room.RoomImplement;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
public class Server {
    public static void StartServer(){
        try {
            Registry registry = LocateRegistry.createRegistry(1009);
            RoomInterface RoomManager = new RoomImplement();
            AdminInterface AdminManager = new AdminImplement();

            registry.rebind("Admin", AdminManager);
            registry.rebind("Room", RoomManager);
            System.out.println("server running ....");
        } catch (RemoteException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
