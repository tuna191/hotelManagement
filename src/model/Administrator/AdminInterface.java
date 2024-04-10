package Model.Administrator;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AdminInterface extends Remote {
    public boolean resetPassWord(String password) throws RemoteException;
    public boolean logIn(String name,String password) throws RemoteException;
    public String getPassWord() throws RemoteException;
}
