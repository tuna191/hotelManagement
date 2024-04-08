package controller;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Model.Administrator.AdminInterface;
public class Client {
    private AdminInterface AdminMana ;
    public boolean isConnected() {
        return AdminMana != null;
    }
    public Client(){
        try {
            Registry registry = LocateRegistry.getRegistry("localhost",1009);
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
}

