package Controller;

import java.rmi.registry.Registry;

import Model.Administrator.AdminImplement;
import Model.Administrator.AdminInterface;
import Model.room.Room;
import Model.room.RoomInterface;
import Model.room.RoomImplement;
import Model.Bill.Bill;
import Model.Bill.BillInterface;
import Model.Bill.BillImplement;
import Model.Employees.EmployeeImplement;
import Model.Employees.EmployeeInterface;
import Model.Customer.Customer;
import Model.Customer.CustomerInterface;
import Model.Customer.CustomerImplement;
import Model.Bill.BillDetail;
import Model.Bill.BillDetailInterface;
import Model.Bill.BillDetailImplement;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
public class Server  {
    public static void StartServer(){
        try {
            Registry registry = LocateRegistry.createRegistry(1009);
            RoomInterface RoomManager = new RoomImplement();
            AdminInterface AdminManager = new AdminImplement();
            EmployeeInterface employeeManager = new EmployeeImplement();
            CustomerInterface CustomerManager = new CustomerImplement();
            BillInterface BillManager = new BillImplement();
            BillDetailInterface BillDetailManager = new BillDetailImplement();

            registry.rebind("Room", RoomManager);
            registry.rebind("Admin", AdminManager);
            registry.rebind("employee", employeeManager);
            registry.rebind("Customer", CustomerManager );
            registry.rebind("Bill", BillManager );
            registry.rebind("BillDetail", BillDetailManager );
            
            System.out.println("server running ....");
        } catch (RemoteException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}

