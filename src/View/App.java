package View;

import java.util.List;

import Model.Bill.Bill;
import Model.Bill.BillImplement;

import java.awt.*;
import java.rmi.RemoteException;
public class App {

   


        // public static void main(String[] args) {
        // EventQueue.invokeLater(new Runnable() {
        //     public void run() {
        //         // try {
        //         //             List<Room> data = roomMana.getAllRoom();
        //         //             System.out.println(data);
        //         // } catch (Exception e) {
        //         //     e.printStackTrace();
        //         // }
        //         System.out.println("hellowwww");
        //     }
        // });


    public static void main(String[] args) throws Exception {
       BillImplement roomService =  new BillImplement(); // Your implementation;

        try {
            // Call the getAllRoom() method
            // Bill roomdetail = new Bill("1222","normal","unvaliable",1223,"1234","1445");
            List <Bill> room = roomService.getAllBill();
            
            // Check if any rooms were retrieved
            if (room != null) {
                System.out.println("Successfully retrieved rooms:");
                
                
            } else {
                System.out.println("No rooms found.");
            }
        } catch (RemoteException e) {
            System.err.println("Error retrieving rooms: " + e.getMessage());
        }
    }

    // }
}





