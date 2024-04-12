package view;
import javax.swing.*;
import java.awt.*;
import Controller.Client;


public class ResetPasswordDialog extends JFrame {
    public interface ResetPasswordListener {
        void onPasswordReset(boolean success);
    }

    private JLabel currentPasswordLabel, newPasswordLabel, confirmPasswordLabel;
    private JPasswordField currentPasswordField, newPasswordField, confirmPasswordField;
    private JButton resetButton, cancelButton;
    private Client client;
    private boolean passwordResetSuccessful = false;
    private ResetPasswordListener resetPasswordListener;
    public void setResetPasswordListener(ResetPasswordListener listener) {
        this.resetPasswordListener = listener;
    }
    
    public ResetPasswordDialog(Frame owner, Client client) {
        super("Reset Password");
        this.client = client;

        JPanel panel = new JPanel(new GridLayout(4, 2));

        currentPasswordLabel = new JLabel("Mật khẩu hiện tại: ");
        currentPasswordField = new JPasswordField();
        newPasswordLabel = new JLabel("Mật khẩu mới: ");
        newPasswordField = new JPasswordField();
        confirmPasswordLabel = new JLabel("Xác nhận mật khẩu mới: ");
        confirmPasswordField = new JPasswordField();

        panel.add(currentPasswordLabel);
        panel.add(currentPasswordField);
        panel.add(newPasswordLabel);
        panel.add(newPasswordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);

        resetButton = new JButton("Reset");
        cancelButton = new JButton("Cancel");

        resetButton.addActionListener(e -> {
            String currentPassword = client.getPassWord();
            char[] enteredPasswordChars = currentPasswordField.getPassword();
            String enteredPassword = new String(enteredPasswordChars);
            
            if (currentPassword.equals(enteredPassword)) {
                char[] newPasswordChars = newPasswordField.getPassword();
                String newPassword = new String(newPasswordChars);
                char[] confirmPasswordChars = confirmPasswordField.getPassword();
                String confirmPassword = new String(confirmPasswordChars);
                
                if (newPassword.equals(confirmPassword)) {
                    client.resetPassWord(confirmPassword);
                    passwordResetSuccessful = true;
                    JOptionPane.showMessageDialog(ResetPasswordDialog.this, "Thay đổi mật khẩu thành công!Vui lòng đăng nhập lại");
                    
                    dispose();
                    if (resetPasswordListener != null) {
                        resetPasswordListener.onPasswordReset(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(ResetPasswordDialog.this, "Mật khẩu mới không khớp với mật khẩu xác nhận!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(ResetPasswordDialog.this, "Mật khẩu hiện tại không chính xác!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelButton.addActionListener(e -> {
            dispose();
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(resetButton);
        buttonPanel.add(cancelButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 200);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public boolean isPasswordResetSuccessful() {
        return passwordResetSuccessful;
    }
}
