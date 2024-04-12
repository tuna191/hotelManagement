package View;

import javax.swing.JDialog;

import Controller.Client;
import Controller.Server;

public class ApplicationMain {
	public static void main(String[] args) {
		try {
			Server.StartServer();
			Client client = new Client(); // Khởi tạo đối tượng Client
        	LoginDialog dialog = new LoginDialog(client); // Truyền client vào LoginDialog
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
