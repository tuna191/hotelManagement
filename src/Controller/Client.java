package Controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import Model.Administrator.AdminInterface;
import Model.room.Room;
import Model.room.RoomInterface;

import Model.Employees.Employee;
import Model.Employees.EmployeeInterface;

import Model.Customer.Customer;
import Model.Customer.CustomerInterface;
import Model.Customer.CustomerImplement;
public class Client {
    private AdminInterface AdminMana ;
    private RoomInterface RoomMana;
    private CustomerInterface CustomerMana;

    private EmployeeInterface EmployeeManager;
    public boolean isConnected() {
        return RoomMana != null;
        
    }

    public Client(){
        try {
            Registry registry = LocateRegistry.getRegistry("localhost",1009);
            CustomerMana = (CustomerInterface) registry.lookup("Customer");
            RoomMana = (RoomInterface) registry.lookup("Room");
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


// rooms
public List<Room> getAllRooms() {
    try {
        // Gọi phương thức từ server để lấy danh sách sản phẩm
        return RoomMana.getAllRoom();
    } catch (RemoteException e) {
        e.printStackTrace();
    }
    return null;
}

public boolean addRoomClient(Room s){
    try {
        return RoomMana.addRoom(s);
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }
    return false;
}

public boolean updateRoomClient(Room s){
    try {
        return RoomMana.updateData(s);
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }
    return false;
}
public boolean deleteRoomClient(String id){
    try {
        return RoomMana.deleteRoom(id);
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }
    return false;
}

public List<Room> getRoomClient(String id){
    try {
        return RoomMana.getRoom(id);
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }

    return null;
} 

        public List<Customer> getALLCustomers() {
        try {
            // Gọi phương thức từ server để lấy danh sách sản phẩm
            return CustomerMana.getAllCustomer();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addCustomers (Customer customer){
        try {
            return CustomerMana.addCustomer(customer);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCustomers(Customer customer){
        try {
            return CustomerMana.updateCustomer(customer);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteCustomers(String id){
        try {
            return CustomerMana.removeCustomer(id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }
    public List<Customer> getIdCustomer(String id){
        try {
            return CustomerMana.getCustomer(id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;

}
}



