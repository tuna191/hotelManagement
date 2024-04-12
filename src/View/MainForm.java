package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Controller.Client;
public class MainForm extends JFrame {
    private Client client;
    public MainForm(Client client) {
        this.client = client;
        setTitle("Quản lý khách sạn");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1150, 640);
        setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        setResizable(false);

        // Tạo menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Tạo menu "Chức Năng" và các mục trong menu
        JMenu menuChucNang = new JMenu("Chức Năng");
        menuChucNang.setIcon(new ImageIcon(resizeImage("/icons/banhrang.png"))); // Thêm biểu tượng cho menu
        menuBar.add(menuChucNang);
        createPopupMenu(menuChucNang, new String[]{"Logout", "Reset Password"}, 
                        new String[]{"logout.png", "reset.png"});

        // Tạo menu "Danh mục" và các mục trong menu
        JMenu menuDanhMuc = new JMenu("Danh mục");
        menuDanhMuc.setIcon(new ImageIcon(resizeImage("/icons/folder.png"))); // Thêm biểu tượng cho menu
        menuBar.add(menuDanhMuc);
        createPopupMenu(menuDanhMuc, new String[]{"Nhân Viên", "Khách Hàng", "Phòng"}, 
                        new String[]{"employee.png", "customer.png", "phong.png"});

        // Tạo menu "Thanh toán" và các mục trong menu
        JMenu menuThanhToan = new JMenu("Thanh toán");
        menuThanhToan.setIcon(new ImageIcon(resizeImage("/icons/money.png"))); // Thêm biểu tượng cho menu
        menuBar.add(menuThanhToan);
        createPopupMenu(menuThanhToan, new String[]{"Hóa đơn", "Chi tiết hóa đơn"}, 
                        new String[]{"bill.png", "billdetail.png"});

        // Tạo menu "Tìm kiếm" và các mục trong menu
        JMenu menuTimKiem = new JMenu("Tìm kiếm");
        menuTimKiem.setIcon(new ImageIcon(resizeImage("/icons/glass.png"))); // Thêm biểu tượng cho menu
        menuBar.add(menuTimKiem);
        

        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        // Panel dưới cùng chiếm toàn bộ giao diện
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/icons/loginImg.png"));
                Image image = backgroundImage.getImage();
                if (image != null) {
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } else {
                    // Handle case where image is not loaded
                    System.out.println("Could not load image: /icons/loginImg.png");
                }
            }
        };

        mainPanel.add(imagePanel, BorderLayout.CENTER);

        // Hiển thị giao diện
        setVisible(true);
    }

    // Phương thức tạo JPopupMenu từ một mảng các tên mục và gắn chúng vào menu cha
    private void createPopupMenu(JMenu parentMenu, String[] items, String[] imagePaths) {
        for (int i = 0; i < items.length; i++) {
            JMenuItem menuItem = new JMenuItem(items[i]);
            menuItem.setIcon(new ImageIcon(resizeImage("/icons/" + imagePaths[i]))); 
            // Thêm biểu tượng cho mỗi mục trong menu
            int j = i;
            menuItem.addActionListener(new ActionListener() {
                @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý sự kiện khi chọn một mục trong menu con
                switch(items[j]) {
                    case "Logout":
                        // Thêm code xử lý cho chức năng logout ở đây
                        // Ví dụ:
                        // JOptionPane.showMessageDialog(MainForm.this, "Đăng xuất thành công!");
                        LoginDialog dialog = new LoginDialog(client);
                        dialog.setVisible(true);
                        dispose();
                        // Sau khi đăng xuất, bạn có thể hiển thị cửa sổ đăng nhập mới hoặc thực hiện các thao tác khác
                        break;
                    case "Reset Password":
                        // Thêm code xử lý cho chức năng reset password ở đây
                        // Ví dụ:
                        ResetPasswordDialog resetDialog = new ResetPasswordDialog(MainForm.this,client);
                        resetDialog.setVisible(true);
                        resetDialog.setResetPasswordListener(success -> {
                            if (success) {
                                LoginDialog login = new LoginDialog(client);
                                login.setVisible(true);
                                dispose();
                            }
                        });
                        //JOptionPane.showMessageDialog(MainForm.this, "Reset mật khẩu thành công!");
                        break;
                    case "Phòng":
                        // Thêm code xử lý cho chức năng reset password ở đây
                        // Ví dụ:
                        RoomForm RoomLog = new RoomForm(client);
                        RoomLog.setVisible(true);
                        break;
                    case "Nhân Viên":
                        EmployeeForm employeeFrame = new EmployeeForm(client);
                        employeeFrame.setVisible(true);
                        break;
                    default:
                        JOptionPane.showMessageDialog(MainForm.this, "Đã chọn: " + items[j]);
                        break;
                }
            }
            });
            parentMenu.add(menuItem);
        }
    }

    // Phương thức để thay đổi kích thước hình ảnh
    private Image resizeImage(String imagePath) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        return newImage;
    }
    
}


