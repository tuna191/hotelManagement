package Controller;

import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) throws RemoteException {
        // Khởi chạy máy chủ
        Server.StartServer();

        // Khởi chạy máy khách và kiểm tra kết nối
        Client client = new Client();
        if (client.isConnected()) {
            System.out.println("Client connected to server successfully.");
        } else {
            System.out.println("Failed to connect to server.");
        }
    }
}

