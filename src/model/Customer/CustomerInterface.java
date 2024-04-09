package model.Customer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CustomerInterface extends Remote {
    public List<Customer> getCustomer(String idcustomer) throws RemoteException;
    public List<Customer> getAllCustomer() throws RemoteException;
    public boolean addCustomer(Customer customer) throws RemoteException;
    public boolean removeCustomer(Customer idcustomer) throws RemoteException;
    public boolean updateCustomer(Customer customer) throws RemoteException;
}
