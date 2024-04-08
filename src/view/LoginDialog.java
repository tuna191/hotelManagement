package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Client;

public class LoginDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField text_tenDangNhap;
    private JPasswordField passwordField;
    private JCheckBox chkHienThiMatKhau;
    private Client client;
    
    public LoginDialog(Client client) {
        this.client = client;
        setTitle("Đăng nhập hệ thống quản lí khách sạn");
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginDialog.class.getResource("/icons/loginImg.png")));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1150, 639);
        getContentPane().setLayout(new BorderLayout());
        
        // Panel chứa nội dung
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(new BorderLayout(0, 0));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Panel bên trái chứa hình ảnh đăng nhập
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(238, 238, 238));
        contentPanel.add(leftPanel, BorderLayout.WEST);
        leftPanel.setLayout(new BorderLayout(0, 0));

        JLabel lbl_backgrond = new JLabel("");
        lbl_backgrond.setIcon(new ImageIcon(LoginDialog.class.getResource("/icons/loginImg.png")));
        leftPanel.add(lbl_backgrond, BorderLayout.CENTER);

        // Panel bên phải chứa form đăng nhập
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(238, 238, 238));
        contentPanel.add(rightPanel, BorderLayout.CENTER);
        rightPanel.setLayout(null);

        JLabel lbl_titleDangNhapHeThong = new JLabel("ĐĂNG NHẬP HỆ THỐNG");
        lbl_titleDangNhapHeThong.setForeground(new Color(0, 128, 255));
        lbl_titleDangNhapHeThong.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_titleDangNhapHeThong.setFont(new Font("DialogInput", Font.BOLD, 28));
        lbl_titleDangNhapHeThong.setBounds(135, 50, 330, 79);
        rightPanel.add(lbl_titleDangNhapHeThong);

        JLabel lbl_tenDangNhap = new JLabel("Tên đăng nhập");
        lbl_tenDangNhap.setIcon(new ImageIcon(LoginDialog.class.getResource("/icons/Apps-preferences-desktop-user-password-icon-24.png")));
        lbl_tenDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lbl_tenDangNhap.setBounds(50, 150, 200, 40);
        rightPanel.add(lbl_tenDangNhap);

        text_tenDangNhap = new JTextField();
        text_tenDangNhap.setFont(new Font("Dialog", Font.PLAIN, 18));
        text_tenDangNhap.setBounds(250, 150, 250, 40);
        rightPanel.add(text_tenDangNhap);
        text_tenDangNhap.setColumns(10);

        JLabel lblmatKhau = new JLabel("Mật khẩu");
        lblmatKhau.setIcon(new ImageIcon(LoginDialog.class.getResource("/icons/gpa-icon-24.png")));
        lblmatKhau.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblmatKhau.setBounds(50, 220, 200, 40);
        rightPanel.add(lblmatKhau);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 18));
        passwordField.setBounds(250, 220, 250, 40);
        rightPanel.add(passwordField);

        chkHienThiMatKhau = new JCheckBox("Hiển thị mật khẩu");
        chkHienThiMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 14));
        chkHienThiMatKhau.setBounds(250, 270, 180, 25);
        rightPanel.add(chkHienThiMatKhau);
        char defaultEchoChar = passwordField.getEchoChar();
        chkHienThiMatKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chkHienThiMatKhau.isSelected()) {
                    // Nếu checkbox được chọn, hiển thị mật khẩu
                    passwordField.setEchoChar((char) 0); // Đặt ký tự hiển thị thành rỗng để hiển thị văn bản
                } else {
                    // Nếu checkbox không được chọn, ẩn mật khẩu
                    passwordField.setEchoChar(defaultEchoChar); // Đặt ký tự hiển thị mật khẩu thành dấu chấm đen
                }
                passwordField.repaint();
            }
        });
        
        JButton btn_dangNhap = new JButton("Đăng nhập");
        btn_dangNhap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        btn_dangNhap.setBackground(new Color(255, 123, 60));
        btn_dangNhap.setFont(new Font("Dialog", Font.BOLD, 25));
        btn_dangNhap.setBounds(150, 350, 250, 50);
        rightPanel.add(btn_dangNhap);

        addEnterKeyListeners();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public boolean checkValForm() {
        return !text_tenDangNhap.getText().isEmpty() && passwordField.getPassword().length > 0;
    }

    private void addEnterKeyListeners() {
        text_tenDangNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.requestFocusInWindow();
            }
        });

        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    private void login() {
        
        if (checkValForm()) {
            String name = text_tenDangNhap.getText();
            String pass = new String(passwordField.getPassword());
            
            if (client.logIn(name, pass)) {
                MainForm main = new MainForm();
                main.setVisible(true);
                dispose();
            } else {
                showCanhBaoSaiThongTin();
            }
        } else {
            showCanhBao();
        }
    }

    public void showCanhBao() {
        JOptionPane.showMessageDialog(this, "Bạn chưa nhập đủ thông tin");
    }

    public void showCanhBaoSaiThongTin() {
        JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu! Vui lòng kiểm tra lại.");
    }
}
