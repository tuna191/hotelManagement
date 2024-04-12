package View;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Server;
import Controller.Client;

import Model.Bill.Bill;
import Model.Bill.BillDetail;
import Model.Customer.Customer;
import Model.Employees.Employee;
import Model.room.Room;

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

public class BillDetailForm extends JFrame {

    private JPanel render;
    private static JComboBox<String> bill;
    private static JComboBox<String> customer;
    private static JComboBox<String> room;
    private JDatePickerImpl datecheckin;
    
    private static JButton add;
    private static JButton delete;
    private static JButton select;
    private static JButton update;

    private Client client;

    public BillDetailForm(Client client) {
        this.client = client;
        initialize();
    }

    private void initialize() {
        // Khởi tạo JFrame hiện tại thay vì tạo một JFrame mới
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        GUIBILLDETAIL();
        updateList(null, false);
        setVisible(true);
    }

    public void GUIBILLDETAIL() {

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
        lblNewLabel.setBounds(24, 0, 86, 60);
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(lblNewLabel);

        String[] empArr; // Declare empArr outside the loop

        List<Bill> empList = client.getAllBills(); // Use empList for clarity

        empArr = new String[empList.size()]; // Allocate memory for empArr

        int i = 0;
        for (Bill bill : empList) {
            empArr[i++] = bill.getIdBill(); // Add ID to empArr and increment index
        }

        bill = new JComboBox<>(empArr);
        bill.setBounds(146, 11, 320, 40);
        input.add(bill);

        JLabel lblNewLabel_1 = new JLabel("Ma KH");
        lblNewLabel_1.setBounds(480, 65, 94, 60);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(lblNewLabel);

        String[] empArr_2; // Declare empArr outside the loop

        List<Customer> empList_2 = client.getALLCustomers(); // Use empList for clarity

        empArr_2 = new String[empList_2.size()]; // Allocate memory for empArr

        int i_2 = 0;
        for (Customer Customer : empList_2) {
            empArr_2[i++] = Customer.getIdcustomer(); // Add ID to empArr and increment index
        }
        customer = new JComboBox<>(empArr_2);
        customer.setBounds(585, 69, 344, 60);
        input.add(customer);
       //
        customer = new JComboBox<>(empArr_2);
        customer.setBounds(146, 104, 322, 60);
        input.add(customer);

        JLabel idEmplField = new JLabel("Ma Phong");
        idEmplField.setBounds(24, 102, 86, 53);
        input.add(idEmplField);

        String[] empArr_3; // Declare empArr outside the loop

        List<Room> empList_3 = client.getAllRooms(); // Use empList for clarity

        empArr_3 = new String[empList_3.size()]; // Allocate memory for empArr

        int i_3 = 0;
        for (Room room : empList_3) {
            empArr_3[i++] = room.getIdRoom(); // Add ID to empArr and increment index
        }

        room = new JComboBox<>(empArr_3);
        room.setBounds(146, 104, 322, 60);
        input.add(room);
        //
        JLabel lblNewLabel_2 = new JLabel("Ngay Di");
        lblNewLabel_2.setBounds(24, 60, 94, 40);
        input.add(lblNewLabel_2);

        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanel datePanel = new JDatePanelImpl(model, properties);
        datecheckin = new JDatePickerImpl((JDatePanelImpl) datePanel, new DateLabelFormatter());
        datecheckin.setBounds(146, 60, 322, 40); // Thiết lập vị trí và kích thước
        input.add(datecheckin);

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
                    String selectedEmpl = (String) bill.getSelectedItem();
                    String selectedEmpl_1 = (String) customer.getSelectedItem();
                    String selectedEmpl_2 = (String) room.getSelectedItem();
                    Date selectedDate = new Date(((java.util.Date) datecheckin.getModel().getValue()).getTime());
                   
                    BillDetail billDetail = new BillDetail (selectedEmpl,selectedDate,selectedEmpl_2, selectedEmpl_1);

                    boolean addMethod = client.addBillDetails(billDetail);
                    if (addMethod) {
                        JOptionPane.showMessageDialog(BillDetailForm.this, " thành công");
                        updateList(null, false);
                    } else {
                        JOptionPane.showMessageDialog(BillDetailForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BillDetailForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
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
                    String selectedEmpl = (String) bill.getSelectedItem();
                    String selectedEmpl_1 = (String) customer.getSelectedItem();
                    String selectedEmpl_2 = (String) room.getSelectedItem();
                    Date selectedDate = new Date(((java.util.Date) datecheckin.getModel().getValue()).getTime());
                   
                    BillDetail billDetail = new BillDetail (selectedEmpl,selectedDate,selectedEmpl_2, selectedEmpl_1);

                    boolean updateMethod = client.updateBillDetails(billDetail);
                    if (updateMethod) {
                        JOptionPane.showMessageDialog(BillDetailForm.this, " thành công");
                        updateList(null, false);
                    } else {
                        JOptionPane.showMessageDialog(BillDetailForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BillDetailForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
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
                    String selectedEmpl = (String) bill.getSelectedItem();
                    
                    boolean deleteMethod = client.deleteBillDetails(selectedEmpl);
                    if (deleteMethod) {
                        JOptionPane.showMessageDialog(BillDetailForm.this, " thành công");
                        updateList(null, false);
                    } else {
                        JOptionPane.showMessageDialog(BillDetailForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BillDetailForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
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
                    String selectedEmpl = (String) bill.getSelectedItem();

                    List<BillDetail>  data = client.getIdBillDetails(selectedEmpl);
                    if (data != null) {
                        updateList(data, true);
                      
                   }  else {
                        JOptionPane.showMessageDialog(BillDetailForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BillDetailForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
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
            List<BillDetail> billdetails;

            // Xóa hết các thành phần cũ trong render
            render.removeAll();

            if (!flat) {
                billdetails = client.getAllBillDetails();
            } else {
                billdetails = data;
            }

            // Thêm header vào render
            String[] columnNames = { "Ma HD", "Ngay Di", "Ma Phong", "Ma KH"};
            
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            JTable table = new JTable(tableModel);
            table.setFont(new Font("Dialog", Font.PLAIN, 15));
            
            // Set kích thước ưu tiên cho các cột
            table.getColumnModel().getColumn(0).setPreferredWidth(100); // Ma phong
            table.getColumnModel().getColumn(1).setPreferredWidth(200); // Kind
            table.getColumnModel().getColumn(2).setPreferredWidth(200); // Status
            table.getColumnModel().getColumn(3).setPreferredWidth(300); // Pric

            // Thêm các phòng vào render
            for (BillDetail billDetail : billdetails) {
                Object[] rowData = { billDetail.getIdBill(),billDetail.getDate(),billDetail.getIdRoom(),billDetail.getIdEmp()};
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
                    BillDetailForm window = new BillDetailForm(client);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

//


