package View;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Server;
import Controller.Client;

import Model.Bill.Bill;
import Model.Employees.Employee;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.Properties;

import org.jdatepicker.JDatePanel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class BillForm extends JFrame {

    private JPanel render;
    private static JTextField mahd;
    private JDatePickerImpl datepay;
    private static JTextField sotien;
    private static JComboBox<String> manv;
    private static JComboBox<String> employee;
    
    private static JButton add;
    private static JButton delete;
    private static JButton select;
    private static JButton update;

    private Client client;

    public BillForm(Client client) {
        this.client = client;
        initialize();
    }

    private void initialize() {
        // Khởi tạo JFrame hiện tại thay vì tạo một JFrame mới
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        GUIBILL();
        updateList(null, false);
        setVisible(true);
    }

    public void GUIBILL() {

        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(20, 20, 951, 297);
        inputPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
        getContentPane().add(inputPanel);
        inputPanel.setLayout(new GridLayout(2,1, 0, 0));

        // Code tạo các thành phần inputPanel ở đây

        JPanel input = new JPanel();
        inputPanel.add(input);
        input.setLayout(null);

        JLabel lblNewLabel = new JLabel("Ma HD");
        lblNewLabel.setBounds(24, 5, 86, 60);
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(lblNewLabel);

        mahd = new JTextField();
        mahd.setBounds(146, 16, 320, 40);
        input.add(mahd);
        mahd.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Ngay thanh toán");
        lblNewLabel_1.setBounds(478, 5, 73, 60);
        input.add(lblNewLabel_1);

        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanel datePanel = new JDatePanelImpl(model, properties);
        datepay = new JDatePickerImpl((JDatePanelImpl) datePanel, new DateLabelFormatter());
        datepay.setBounds(583, 16, 344, 40); // Thiết lập vị trí và kích thước
        input.add(datepay);

        JLabel lblNewLabel_2 = new JLabel("So Tien");
        lblNewLabel_2.setBounds(480, 102, 94, 53);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(lblNewLabel_2);

        sotien = new JTextField();
        sotien.setBounds(585, 104, 344, 60);
        input.add(sotien);
        sotien.setColumns(10);

        JLabel idEmplField = new JLabel("Ma nhan vien");
        idEmplField.setBounds(24, 102, 86, 53);
        input.add(idEmplField);

        String[] empArr; // Declare empArr outside the loop

        List<Employee> empList = client.getAllEmployees(); // Use empList for clarity

        empArr = new String[empList.size()]; // Allocate memory for empArr

        int i = 0;
        for (Employee employee : empList) {
            empArr[i++] = employee.getIdEmp(); // Add ID to empArr and increment index
        }

       
        employee = new JComboBox<>(empArr);
        employee.setBounds(146, 104, 322, 60);
        input.add(employee);
        
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
                    String idHD = mahd.getText();
                    Date selectedDate = new Date(((java.util.Date) datepay.getModel().getValue()).getTime());
                    double priceData = Double.parseDouble(sotien.getText());
                    String selectedEmpl = (String) employee.getSelectedItem();
                    Bill bill = new Bill(idHD,selectedDate,priceData,selectedEmpl);

                    boolean addMethod = client.addBills(bill);
                    if (addMethod) {
                        JOptionPane.showMessageDialog(BillForm.this, " thành công");
                        updateList(null, false);
                    } else {
                        JOptionPane.showMessageDialog(BillForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BillForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
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
                    String idHD = mahd.getText();
                    Date selectedDate = new Date(((java.util.Date) datepay.getModel().getValue()).getTime());
                    double priceData = Double.parseDouble(sotien.getText());
                    String selectedEmpl = (String) employee.getSelectedItem();
                    Bill bill = new Bill (idHD,selectedDate,priceData,selectedEmpl);

                    boolean updateMethod = client.updateBills(bill);
                    if (updateMethod) {
                        JOptionPane.showMessageDialog(BillForm.this, " thành công");
                        updateList(null, false);
                    } else {
                        JOptionPane.showMessageDialog(BillForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BillForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
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
                    String idHD = mahd.getText();
                    
                    boolean deleteMethod = client.deleteBills(idHD);
                    if (deleteMethod) {
                        JOptionPane.showMessageDialog(BillForm.this, " thành công");
                        updateList(null, false);
                    } else {
                        JOptionPane.showMessageDialog(BillForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BillForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
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
                    String idHD = mahd.getText();

                    List<Bill>  data = client.getIdBills(idHD);
                    if (data != null) {
                        updateList(data, true);
                      
                   }  else {
                        JOptionPane.showMessageDialog(BillForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BillForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
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
            List<Bill> bills;

            // Xóa hết các thành phần cũ trong render
            render.removeAll();

            if (!flat) {
                bills = client.getAllBills();
            } else {
                bills = data;
            }

            // Thêm header vào render
            String[] columnNames = { "Ma HD", "Ngay TT", "So Tien", "Ma NV"};
            
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            JTable table = new JTable(tableModel);
            table.setFont(new Font("Dialog", Font.PLAIN, 15));
            
            // Set kích thước ưu tiên cho các cột
            table.getColumnModel().getColumn(0).setPreferredWidth(100); // Ma phong
            table.getColumnModel().getColumn(1).setPreferredWidth(200); // Kind
            table.getColumnModel().getColumn(2).setPreferredWidth(200); // Status
            table.getColumnModel().getColumn(3).setPreferredWidth(300); // Pric

            // Thêm các phòng vào render
            for (Bill bill : bills) {
                Object[] rowData = { bill.getIdBill(),bill.getDateTT(),bill.getPrice(),bill.getIdEmp()};
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
                    BillForm window = new BillForm(client);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

//

