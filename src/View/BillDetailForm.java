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
    private static JTextField maHd;
    private static JComboBox<String> billBox;
    private static JComboBox<String> customer;
    private static JComboBox<String> employee;

    private static JComboBox<String> room;
    private JDatePickerImpl dateCheckout;
    
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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        JLabel hddLabel = new JLabel("Ma HD deltail");
        hddLabel.setBounds(24, 11, 86, 40);
        hddLabel.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(hddLabel);

        maHd = new JTextField();
        maHd.setBounds(146, 11, 322, 30);
        input.add(maHd);

        JLabel checkoutLabel = new JLabel("Ngay Di");
        checkoutLabel.setBounds(24, 60, 94, 40);
        input.add(checkoutLabel);

        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanel datePanel = new JDatePanelImpl(model, properties);
        dateCheckout = new JDatePickerImpl((JDatePanelImpl) datePanel, new DateLabelFormatter());
        dateCheckout.setBounds(146, 60, 322, 40); // Thiết lập vị trí và kích thước
        input.add(dateCheckout);


        JLabel mpLabel = new JLabel("Ma Phong");
        mpLabel.setBounds(24, 102, 86, 53);
        input.add(mpLabel);

        String[] roomArr; // Declare empArr outside the loop

        List<Room> List3 = client.getAllRooms(); // Use empList for clarity

        roomArr = new String[List3.size()]; // Allocate memory for empArr

        int z = 0;
        for (Room room : List3) {
            roomArr[z++] = room.getIdRoom(); // Add ID to empArr and increment index
        }

        room = new JComboBox<>(roomArr);
        room.setBounds(146, 104, 322, 60);
        input.add(room);

        JLabel khLabel = new JLabel("Ma KH");
        khLabel.setBounds(480, 0, 94, 60);
        khLabel.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(khLabel);

        String[] cusArr; // Declare empArr outside the loop

        List<Customer> List2 = client.getALLCustomers(); // Use empList for clarity

        cusArr = new String[List2.size()]; // Allocate memory for empArr

        int y = 0;
        for (Customer Customer : List2) {
            cusArr[y++] = Customer.getIdcustomer(); // Add ID to empArr and increment index
        }
        customer = new JComboBox<>(cusArr);
        customer.setBounds(585, 69, 344, 60);
        input.add(customer);


        JLabel nvLabel = new JLabel("Ma NV");
        nvLabel.setBounds(480, 60, 94, 60);
        nvLabel.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(nvLabel);

        String[] empArr; // Declare empArr outside the loop

        List<Employee> List4 = client.getAllEmployees(); // Use empList for clarity

        empArr = new String[List4.size()]; // Allocate memory for empArr

        int t = 0;
        for (Employee Empl : List4) {
            empArr[t++] = Empl.getIdEmp(); // Add ID to empArr and increment index
        }
        employee = new JComboBox<>(empArr);
        employee.setBounds(585, 0, 344, 60);
        input.add(employee);
        
        JLabel hdLabel = new JLabel("Ma HD");
        hdLabel.setBounds(480, 106, 86, 60);
        hdLabel.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(hdLabel);

        String[] billArr ; // Declare empArr outside the loop

        List<Bill> List_bill = client.getAllBills(); // Use empList for clarity

        billArr = new String[List_bill.size()]; // Allocate memory for empArr

        int s = 0;
        for (Bill bill : List_bill) {
            billArr[s++] = bill.getIdBill();
        }
        billBox = new JComboBox<>(billArr);
        billBox.setBounds(585, 113, 320, 40);
        input.add(billBox);



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
                    String idDetail = maHd.getText();
                    Date selectedDate = new Date(((java.util.Date) dateCheckout.getModel().getValue()).getTime());
                    String idPhong = (String) room.getSelectedItem();
                    String idBill = (String) billBox.getSelectedItem();
                    String idCustomer = (String) customer.getSelectedItem();
                    String selectedEmpl = (String) employee.getSelectedItem();
                   
                    BillDetail billDetail = new BillDetail (idDetail,selectedDate,idPhong,idBill,idCustomer,selectedEmpl);

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
                    String idDetail = maHd.getText();
                    Date selectedDate = new Date(((java.util.Date) dateCheckout.getModel().getValue()).getTime());
                    String idPhong = (String) room.getSelectedItem();
                    String idBill = (String) billBox.getSelectedItem();
                    String idCustomer = (String) customer.getSelectedItem();
                    String selectedEmpl = (String) employee.getSelectedItem();

                    BillDetail billDetail = new BillDetail (idDetail,selectedDate,idPhong,idBill,idCustomer,selectedEmpl);

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
                    String idDetail = maHd.getText();

                    
                    boolean deleteMethod = client.deleteBillDetails(idDetail);
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
                    String idDetail = maHd.getText();

                    List<BillDetail> data = client.getIdBillDetails(idDetail);
                   
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
            String[] columnNames = { "Ma CTHD", "Ma HD", "Ngay Di", "Ma Phong", "Ma KH","Ma NV"};
            
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            JTable table = new JTable(tableModel);
            table.setFont(new Font("Dialog", Font.PLAIN, 15));
            
            // Set kích thước ưu tiên cho các cột
            table.getColumnModel().getColumn(0).setPreferredWidth(100); // Ma phong
            table.getColumnModel().getColumn(1).setPreferredWidth(200); // Kind
            table.getColumnModel().getColumn(2).setPreferredWidth(200); // Status
            table.getColumnModel().getColumn(3).setPreferredWidth(300); // Pric
            table.getColumnModel().getColumn(4).setPreferredWidth(300); // Pric
            table.getColumnModel().getColumn(5).setPreferredWidth(300); // Pric


            // Thêm các phòng vào render
            for (BillDetail billDetail : billdetails) {
                Object[] rowData = { billDetail.getIdDetail() ,billDetail.getIdBill(),billDetail.getDate(),billDetail.getIdRoom(),billDetail.getIdCustomer(),billDetail.getIdEmp()};
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


