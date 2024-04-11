package Controller;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import Model.Administrator.AdminInterface;
import Model.Employees.Employee;
import Model.Employees.EmployeeInterface;
public class Client {
    private AdminInterface AdminMana ;
    private EmployeeInterface EmployeeManager;
    public boolean isConnected() {
        return AdminMana != null;
    }
    public Client(){
        try {
            Registry registry = LocateRegistry.getRegistry("localhost",1009);
            AdminMana = (AdminInterface) registry.lookup("Admin");
            EmployeeManager = (EmployeeInterface) registry.lookup("employee");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public boolean addEmployees(Employee employee){
        try {
            return EmployeeManager.addEmployee(employee);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }


        return false;
    }
    public boolean updateEmployees(Employee employee){
        try {
            return EmployeeManager.updateEmployee(employee);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }


        return false;
    }
    public boolean deleteEmployees(String idImp){
        try {
            return EmployeeManager.removeEmployee(idImp);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }


        return false;
    }
    public List<Employee> getAllEmployees(){
        try {
            return EmployeeManager.getAllEmployees();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }
    public List<Employee> getEmployees(String idImp){
        try {
            return EmployeeManager.getEmployees(idImp);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
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

