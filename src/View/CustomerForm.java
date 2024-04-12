package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.util.Properties;

import Controller.Client;
import Controller.Server;
import Model.Customer.Customer;
import Model.room.Room;
import Model.Customer.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;


public class CustomerForm extends JFrame {

    private JPanel render;
    private static JTextField makh;
    private static JTextField tenkh;
    private static JTextField diachi;
    private static JTextField cmnd;
    private static JTextField phone;
    private static JComboBox<String> kind;
 //   private static JComboBox<String> status;
   // private static JComboBox<String> custommer;
   // private static JComboBox<String> employee;


    private static JButton add;
    private static JButton delete;
    private static JButton select;
    private static JButton update;

    private Client client;

    public CustomerForm(Client client) {
        this.client = client;
        initialize();
    }

    private void initialize() {
        // Khởi tạo JFrame hiện tại thay vì tạo một JFrame mới
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        GUICUSTOMER();
        updateList(null, false);
        setVisible(true);
    }

    public void GUICUSTOMER() {

        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(20, 20, 951, 297);
        inputPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
        getContentPane().add(inputPanel);
        inputPanel.setLayout(new GridLayout(2,1, 0, 0));

        // Code tạo các thành phần inputPanel ở đây

        JPanel input = new JPanel();
        inputPanel.add(input);
        input.setLayout(null);

        JLabel lblNewLabel = new JLabel("Ma KH");
        lblNewLabel.setBounds(24, 0, 86, 60);
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(lblNewLabel);

        makh = new JTextField();
        makh.setBounds(146, 11, 320, 40);
        input.add(makh);
        makh.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Tên KH");
        lblNewLabel_1.setBounds(480, 60, 94, 40);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(lblNewLabel_1);

        tenkh = new JTextField();
        tenkh.setBounds(585, 60, 344, 40);
        input.add(tenkh);
        tenkh.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Dia Chi");
        lblNewLabel_2.setBounds(480, 102, 94, 53);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(lblNewLabel_2);

        diachi = new JTextField();
        diachi.setBounds(585, 104, 344, 60);
        input.add(diachi);
        diachi.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Gioi Tinh");
        lblNewLabel_3.setBounds(24, 102, 86, 53);
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(lblNewLabel_3);

        String[] kinds = { "", "Nam", "Nu" };
        kind = new JComboBox<>(kinds);
        kind.setBounds(146, 104, 322, 60);
        input.add(kind);



        JLabel lblNewLabel_4 = new JLabel("CMND");
        lblNewLabel_4.setBounds(480, 102, 94, 53);
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(lblNewLabel_2);

        cmnd = new JTextField();
        cmnd.setBounds(584, 0, 344, 60);
        input.add(diachi);
        cmnd.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("SDT");
        lblNewLabel_5.setBounds(478, 0, 73, 60);
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(lblNewLabel_5);

        phone = new JTextField();
        phone.setBounds(583, 11, 344, 40);
        input.add(phone);
        phone.setColumns(10);

        JPanel button = new JPanel();
        inputPanel.add(button);
        button.setLayout(null);

        add = new JButton("Them moi");
        add.setBounds(65, 49, 130, 40);
        button.add(add);

        
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idkh = makh.getText();
                    String name = tenkh.getText();
                    String address = diachi.getText();
                    String gioitinh = (String) kind.getSelectedItem();
                    Integer cancuoc = Integer.parseInt(cmnd.getText());
                    Integer sdt = Integer.parseInt(phone.getText());
                    Customer customer = new Customer(idkh, name, address, gioitinh,cancuoc,sdt);

                    boolean addMethod = client.addCustomers(customer);
                    if (addMethod) {
                        JOptionPane.showMessageDialog(CustomerForm.this, " thành công");
                        updateList(null, false);
                    } else {
                        JOptionPane.showMessageDialog(CustomerForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CustomerForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        update = new JButton("Luu");
        update.setBounds(254, 49, 130, 40);
        button.add(update);

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idkh = makh.getText();
                    String name = tenkh.getText();
                    String address = diachi.getText();
                    String gioitinh = (String) kind.getSelectedItem();
                    Integer cancuoc = Integer.parseInt(cmnd.getText());
                    Integer sdt = Integer.parseInt(phone.getText());
                    Customer customer = new Customer(idkh, name, address, gioitinh,cancuoc,sdt);
                    boolean updateMethod = client.updateCustomers(customer);
                    if (updateMethod) {
                        JOptionPane.showMessageDialog(CustomerForm.this, " thành công");
                        updateList(null, false);
                    } else {
                        JOptionPane.showMessageDialog(CustomerForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CustomerForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        delete = new JButton("Xoa");
        delete.setBounds(459, 49, 130, 40);
        button.add(delete);

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idkh = makh.getText();

                    boolean deleteMethod = client.deleteCustomers(idkh);
                    if (deleteMethod) {
                        JOptionPane.showMessageDialog(CustomerForm.this, " thành công");
                        updateList(null, false);
                    } else {
                        JOptionPane.showMessageDialog(CustomerForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CustomerForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        select = new JButton("Tim kiem");
        select.setBounds(677, 49, 130, 40);
        button.add(select);

        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idkh = makh.getText();

                    List<Customer> data = client.getIdCustomer(idkh);
                    if (data != null) {
                        updateList(data, true);

                    } else {
                        JOptionPane.showMessageDialog(CustomerForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CustomerForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        JPanel renderPanel = new JPanel();
        renderPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, new Color(0, 0, 0), null));
        renderPanel.setBounds(20, 310, 950, 430);
        getContentPane().add(renderPanel);

        render = new JPanel();
        renderPanel.add(render);
        render.setLayout(new BorderLayout()); // Sửa layout của render thành GridLayout
        render.setPreferredSize(new Dimension(700, 370));
        // Make the frame visible
        setVisible(true);
    }

    private void updateList(List data, boolean flat) {
        try {
            List<Customer> customers;

            // Xóa hết các thành phần cũ trong render
            render.removeAll();

            if (!flat) {
                customers = client.getALLCustomers();
            } else {
                customers = data;
            }

            // Thêm header vào render
            String[] columnNames = { "Ma Nv", "Ten Nv", "Dia Chi", "Gioi Tinh", "CMND", "SDT" };
            
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            JTable table = new JTable(tableModel);
            table.setFont(new Font("Dialog", Font.PLAIN, 15));
            
            // Set kích thước ưu tiên cho các cột
            table.getColumnModel().getColumn(0).setPreferredWidth(100); // Ma phong
            table.getColumnModel().getColumn(1).setPreferredWidth(200); // Kind
            table.getColumnModel().getColumn(2).setPreferredWidth(200); // Status
            table.getColumnModel().getColumn(3).setPreferredWidth(300); // Price
            table.getColumnModel().getColumn(4).setPreferredWidth(100); // idCustomer
            table.getColumnModel().getColumn(5).setPreferredWidth(100); // idEmployee


            // Thêm các phòng vào render
            for (Customer customer : customers) {
                Object[] rowData = { customer.getIdcustomer(), customer.getName(), customer.getAdress(), customer.getSex(),
                        customer.getIdentify(), customer.getPhone() };
                tableModel.addRow(rowData);
            }
            // Thêm table vào renderPanel
            JScrollPane scrollPane = new JScrollPane(table);
            render.add(scrollPane, BorderLayout.CENTER); 
            render.revalidate();
            render.repaint();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Server.StartServer();
                    Client client = new Client(); // Tạo kết nối mới với máy chủ
                    CustomerForm window = new CustomerForm(client);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

//


