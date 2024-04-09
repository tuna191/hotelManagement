package view;

import java.util.List;
import java.awt.*;
import java.rmi.RemoteException;

import model.room.Room;
import model.room.RoomImplement;
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
       RoomImplement roomService =  new RoomImplement(); // Your implementation;

        try {
            // Call the getAllRoom() method
            Room roomdetail = new Room("1222","normal","unvaliable",1223,"1234","1445");
            boolean room = roomService.deleteRoom("1222");
            
            // Check if any rooms were retrieved
            if (room) {
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





