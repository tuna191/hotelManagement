package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MainForm extends JFrame {

    private static final long serialVersionUID = 1L;

    public MainForm() {
        setTitle("Chào mừng đến với trang chủ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        setResizable(false); // Không cho phép thay đổi kích thước cửa sổ

        // Tạo một nhãn để hiển thị thông báo chào mừng
        JLabel lblWelcome = new JLabel("Chào mừng đến với trang chủ!", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 20));
        getContentPane().add(lblWelcome); // Thêm nhãn vào nội dung của JFrame

        setVisible(true); // Hiển thị JFrame lên màn hình
    }

    // Phương thức main để kiểm tra MainForm
    // public static void main(String[] args) {
    //     new MainForm();
    // }
}
