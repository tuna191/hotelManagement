package controller;

import java.rmi.registry.Registry;

import Model.Administrator.AdminImplement;
import Model.Administrator.AdminInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
public class Server {
    public static void StartServer(){
        try {
            Registry registry = LocateRegistry.createRegistry(1009);
            AdminInterface AdminManager = new AdminImplement();
            registry.rebind("Admin", AdminManager);
            System.out.println("server running ....");
        } catch (RemoteException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}

