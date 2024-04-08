package model.Employees;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface EmployeeInterface extends Remote {
    public List<Employee> getEmployees(String idEmp) throws RemoteException;
    public List<Employee> getAllEmployees() throws RemoteException;
    public boolean addEmployee(Employee employee) throws RemoteException;
    public boolean removeEmployee(String idEmp) throws RemoteException;
    public boolean updateEmployee(Employee employee) throws RemoteException;
}
