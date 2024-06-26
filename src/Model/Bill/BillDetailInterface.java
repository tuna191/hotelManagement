package Model.Bill;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BillDetailInterface extends Remote {
    public List<BillDetail> getBillDetail(String idDetail) throws RemoteException;
    public List<BillDetail> getAllBillDetail() throws RemoteException;
    public boolean addBillDetail(BillDetail detail) throws RemoteException;
    public boolean removeBillDetail(String idDetail) throws RemoteException;
    public boolean updateBillDetail(BillDetail detail) throws RemoteException;
}

