package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.JDatePanel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.util.Properties;

import Controller.Client;
import Model.Employees.Employee;

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

public class EmployeeForm extends JFrame {

    private JPanel render;
    private static JTextField tenNv;
    private static JTextField maNV;
    private static JTextField DiaChi;
    private static JTextField GIoiTinh;
    private static JTextField SDT;
    private static JTextField dateField;
    private static JComboBox<String> kind;
    private static JComboBox<String> status;
    private static JComboBox<String> custommer;
    private static JComboBox<String> employee;
    private JDatePickerImpl datePicker;
    private static JButton add;
    private static JButton delete;
    private static JButton select;
    private static JButton update;

    private Client client;

    public EmployeeForm(Client client) {
        this.client = client;
        initialize();
    }

    private void initialize() {
        // Khởi tạo JFrame hiện tại thay vì tạo một JFrame mới
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        GUIROOM();
        updateList(null, false);
        setVisible(true);
    }

    public void GUIROOM() {

        JPanel inputPanel = new JPanel();
        inputPanel.setBounds(20, 20, 951, 297);
        inputPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
        getContentPane().add(inputPanel);
        inputPanel.setLayout(new GridLayout(2, 1, 0, 0));

        // Code tạo các thành phần inputPanel ở đây

        JPanel input = new JPanel();
        inputPanel.add(input);
        input.setLayout(null);
        //1
        JLabel lblNewLabel = new JLabel("Ma NV");
        lblNewLabel.setBounds(24, 0, 86, 60);
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(lblNewLabel);

        maNV = new JTextField();
        maNV.setBounds(146, 11, 320, 40);
        input.add(maNV);
        maNV.setColumns(10);
        //2
        JLabel lblNewLabel_1 = new JLabel("Tên NV");
        lblNewLabel_1.setBounds(480, 60, 94, 40);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(lblNewLabel_1);

        tenNv = new JTextField();
        tenNv.setBounds(585, 60, 344, 40);
        input.add(tenNv);
        tenNv.setColumns(10);
        //3
        JLabel lblNewLabel_2 = new JLabel("Địa Chỉ");
        lblNewLabel_2.setBounds(480, 102, 94, 53);
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(lblNewLabel_2);

        DiaChi = new JTextField();
        DiaChi.setBounds(585, 104, 344, 60);
        input.add(DiaChi);
        DiaChi.setColumns(10);

        //4
        JLabel lblNewLabel_3 = new JLabel("Giới Tính");
        lblNewLabel_3.setBounds(24, 102, 86, 53);
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(lblNewLabel_3);

        String[] kinds = { "", "Nam", "Nữ" };
        kind = new JComboBox<>(kinds);
        kind.setBounds(146, 104, 322, 60);
        input.add(kind);
        //5
        JLabel lblNewLabel_4 = new JLabel("Ngày sinh");
        lblNewLabel_4.setBounds(24, 60, 94, 40);
        input.add(lblNewLabel_4);

        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanel datePanel = new JDatePanelImpl(model, properties);
        datePicker = new JDatePickerImpl((JDatePanelImpl) datePanel, new DateLabelFormatter());
        datePicker.setBounds(146, 60, 322, 40); // Thiết lập vị trí và kích thước
        input.add(datePicker);

        //6

        JLabel lblNewLabel_5 = new JLabel("SDT");
        lblNewLabel_5.setBounds(478, 0, 73, 60);
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(lblNewLabel_5);

        SDT = new JTextField();
        SDT.setBounds(583, 11, 344, 40);
        input.add(SDT);
        SDT.setColumns(10);

        // String[] empArr; // Declare empArr outside the loop

        // List<Employee> empList = client.getEmployeeList(); // Use empList for clarity

        // empArr = new String[empList.size()]; // Allocate memory for empArr

        // int i = 0;
        // for (Employee employee : empList) {
        //     empArr[i++] = employee.getIdEmp(); // Add ID to empArr and increment index
        // }

        

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
                    List<Employee> employees = new ArrayList<Employee>();
                    String gioiTinh = (String) kind.getSelectedItem();
                    Employee employee = new Employee();
                    employee.setIdEmp(maNV.getText());
                    employee.setNameEmp(tenNv.getText());
                    employee.setSex(gioiTinh);
                    
                    // Lấy giá trị ngày tháng năm từ datePicker và chuyển đổi sang định dạng phù hợp
                    Date selectedDate = new Date(((java.util.Date) datePicker.getModel().getValue()).getTime());
                    
                    employee.setbirth(selectedDate);
                    employee.setAddress(DiaChi.getText());
                    employee.setPhone(Integer.parseInt(SDT.getText()));
                    employees.add(employee);
                    boolean addMethod = client.addEmployees(employee);

                    if (addMethod) {
                        JOptionPane.showMessageDialog(EmployeeForm.this, " thành công");
                        updateList(null, false);
                    } else {
                        JOptionPane.showMessageDialog(EmployeeForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(EmployeeForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
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
                    List<Employee> employees = new ArrayList<Employee>();
                    String gioiTinh = (String) kind.getSelectedItem();
                    Employee employee = new Employee();
                    employee.setIdEmp(maNV.getText());
                    employee.setNameEmp(tenNv.getText());
                    employee.setSex(gioiTinh);
                    
                    // Lấy giá trị ngày tháng năm từ datePicker và chuyển đổi sang định dạng phù hợp
                    Date selectedDate = new Date(((java.util.Date) datePicker.getModel().getValue()).getTime());
                    
                    employee.setbirth(selectedDate);
                    employee.setAddress(DiaChi.getText());
                    employee.setPhone(Integer.parseInt(SDT.getText()));
                    employees.add(employee);
                    boolean updateMethod = client.updateEmployees(employee);
                    if (updateMethod) {
                        JOptionPane.showMessageDialog(EmployeeForm.this, " thành công");
                        updateList(null, false);
                    } else {
                        JOptionPane.showMessageDialog(EmployeeForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(EmployeeForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
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
                    String idnv = maNV.getText();

                    boolean deleteMethod = client.deleteEmployees(idnv);
                    if (deleteMethod) {
                        JOptionPane.showMessageDialog(EmployeeForm.this, " thành công");
                        updateList(null, false);
                    } else {
                        JOptionPane.showMessageDialog(EmployeeForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(EmployeeForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
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
                    String idPhong = maNV.getText();

                    List<Employee> data = client.getEmployees(idPhong);
                    if (data != null) {
                        updateList(data, true);

                    } else {
                        JOptionPane.showMessageDialog(EmployeeForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(EmployeeForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
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
            List<Employee> employees;

            // Xóa hết các thành phần cũ trong render
            render.removeAll();

            if (!flat) {
                employees = client.getAllEmployees();
            } else {
                employees = data;
            }

            // Thêm header vào render
            String[] columnNames = { "Ma Nv", "Ten Nv", "Dia Chi", "Gioi Tinh", "Ngay Sinh", "SDT" };

            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            JTable table = new JTable(tableModel);
            table.setFont(new Font("Dialog", Font.PLAIN, 15));

            // Set kích thước ưu tiên cho các cột
            table.getColumnModel().getColumn(0).setPreferredWidth(100); // Ma phong
            table.getColumnModel().getColumn(1).setPreferredWidth(200); // Kind
            table.getColumnModel().getColumn(2).setPreferredWidth(200); // Status
            table.getColumnModel().getColumn(3).setPreferredWidth(300); // tenNv
            table.getColumnModel().getColumn(4).setPreferredWidth(100); // idCustomer
            table.getColumnModel().getColumn(5).setPreferredWidth(100); // idEmployee

            // Thêm các phòng vào render
            for (Employee employee : employees) {
                Object[] rowData = { employee.getIdEmp(), employee.getNameEmp(), employee.getAddress(), employee.getSex(),
                        employee.getbirth(), employee.getPhone() };
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
}
