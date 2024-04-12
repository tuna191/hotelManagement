package Controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import Model.Administrator.AdminInterface;
import Model.Bill.Bill;
import Model.room.Room;
import Model.room.RoomInterface;

import Model.Employees.Employee;
import Model.Employees.EmployeeInterface;

import Model.Customer.Customer;
import Model.Customer.CustomerInterface;
import Model.Customer.CustomerImplement;

import Model.Bill.Bill;
import Model.Bill.BillInterface;
import Model.Bill.BillImplement;

import Model.Bill.BillDetail;
import Model.Bill.BillDetailInterface;
import Model.Bill.BillDetailImplement;

public class Client {
    private AdminInterface AdminMana ;
    private RoomInterface RoomMana;
    private CustomerInterface CustomerMana;
    private BillInterface BillMana;
    private EmployeeInterface EmployeeManager;
    private BillDetailInterface BillDetailMana;
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
            BillMana = (BillInterface) registry.lookup("Bill");
            BillDetailMana = (BillDetailInterface) registry.lookup("BillDetail");
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
//Bill
public List<Bill> getAllBills() {
    try {
        // Gọi phương thức từ server để lấy danh sách sản phẩm
        return BillMana.getAllBill();
    } catch (RemoteException e) {
        e.printStackTrace();
    }
    return null;
}

public boolean addBills (Bill bill){
    try {
        return BillMana.addBill(bill);
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }
    return false;
}

public boolean updateBills(Bill bill){
    try {
        return BillMana.updateBill(bill);
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }
    return false;
}
public boolean deleteBills(String id){
    try {
        return BillMana.removeBill(id);
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }
    return false;
}
public List<Bill> getIdBills(String id){
    try {
        return BillMana.getBill(id);
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }
    return null;

}
// BillDetail
public List<BillDetail> getAllBillDetails() {
    try {
        // Gọi phương thức từ server để lấy danh sách sản phẩm
        return BillDetailMana.getAllBillDetail();
    } catch (RemoteException e) {
        e.printStackTrace();
    }
    return null;
}

public boolean addBillDetails (BillDetail billDetail){
    try {
        return BillDetailMana.addBillDetail(billDetail);
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }
    return false;
}

public boolean updateBillDetails(BillDetail billDetail){
    try {
        return BillDetailMana.updateBillDetail(billDetail);
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }
    return false;
}
public boolean deleteBillDetails(String id){
    try {
        return BillDetailMana.removeBillDetail(id);
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }
    return false;
}
public List<BillDetail> getIdBillDetails(String id){
    try {
        return BillDetailMana.getBillDetail(id);
    } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
    }
    return null;

}
}



