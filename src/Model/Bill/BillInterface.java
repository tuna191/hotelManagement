
package Model.Bill;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BillInterface extends Remote {
    public List<Bill> getBill(String idBill) throws RemoteException;
    public List<Bill> getAllBill() throws RemoteException;
    public boolean addBill(Bill bill) throws RemoteException;
    public boolean removeBill(String idBill) throws RemoteException;
    public boolean updateBill(Bill bill) throws RemoteException;
}

