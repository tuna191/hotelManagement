package View;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Server;
import Controller.Client;
import Model.Employees.Employee;
import Model.room.Room;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RoomForm extends JFrame {

    private JPanel render;
    private static JTextField maPhong;
    private static JTextField price;
    private static JComboBox<String> kind;
    private static JComboBox<String> status;
    private static JComboBox<String> custommer;
    private static JComboBox<String> employee;

    private static JButton add;
    private static JButton delete;
    private static JButton select;
    private static JButton update;

    private Client client;

    public RoomForm(Client client) {
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

        JLabel lblNewLabel = new JLabel("Ma Phong");
        lblNewLabel.setBounds(24, 0, 86, 60);
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(lblNewLabel);

        maPhong = new JTextField();
        maPhong.setBounds(146, 11, 320, 40);
        input.add(maPhong);
        maPhong.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Loai Phong");
        lblNewLabel_1.setBounds(480, 65, 94, 60);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        input.add(lblNewLabel_1);

        String[] kinds = { "", "Normal", "VIP" };
        kind = new JComboBox<>(kinds);
        kind.setBounds(585, 69, 344, 60);
        input.add(kind);

        JLabel idCusField = new JLabel("Ma khac hang");
        idCusField.setBounds(480, 102, 94, 53);
        input.add(idCusField);

        String[] cusArr = { "", "1234", "222" }; // đang code cứng
        custommer = new JComboBox<>(cusArr);
        custommer.setBounds(585, 104, 344, 60);
        input.add(custommer);

        JLabel idEmplField = new JLabel("Ma nhan vien");
        idEmplField.setBounds(24, 102, 86, 53);
        input.add(idEmplField);

        // String[] empArr; // Declare empArr outside the loop

        // List<Employee> empList = client.getEmployeeList(); // Use empList for clarity

        // empArr = new String[empList.size()]; // Allocate memory for empArr

        // int i = 0;
        // for (Employee employee : empList) {
        //     empArr[i++] = employee.getIdEmp(); // Add ID to empArr and increment index
        // }

        String[] emplArr = { "", "1445", "222" };
        employee = new JComboBox<>(emplArr);
        employee.setBounds(146, 104, 322, 60);
        input.add(employee);

        JLabel lblNewLabel_2 = new JLabel("Trang Thai");
        lblNewLabel_2.setBounds(24, 72, 86, 53);
        input.add(lblNewLabel_2);

        String[] statuses = { "", "Available", "Unavailable" };
        status = new JComboBox<>(statuses);
        status.setBounds(146, 65, 322, 60);
        input.add(status);

        JLabel lblNewLabel_3 = new JLabel("Gia Phong");
        lblNewLabel_3.setBounds(478, 0, 73, 60);
        input.add(lblNewLabel_3);

        price = new JTextField();
        price.setBounds(583, 11, 344, 40);
        input.add(price);
        price.setColumns(10);

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
                    String idPhong = maPhong.getText();
                    String selectedKind = (String) kind.getSelectedItem();
                    String selectedStatus = (String) status.getSelectedItem();
                    String selectedCus = (String) custommer.getSelectedItem();
                    String selectedEmpl = (String) employee.getSelectedItem();
                    double priceData = Double.parseDouble(price.getText());

                    Room room = new Room(idPhong, selectedKind, selectedStatus, priceData, selectedCus, selectedEmpl);

                    boolean addMethod = client.addRoomClient(room);
                    if (addMethod) {
                        JOptionPane.showMessageDialog(RoomForm.this, " thành công");
                        updateList(null, false);
                    } else {
                        JOptionPane.showMessageDialog(RoomForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RoomForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
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
                    String idPhong = maPhong.getText();
                    String selectedKind = (String) kind.getSelectedItem();
                    String selectedStatus = (String) status.getSelectedItem();
                    String selectedCus = (String) custommer.getSelectedItem();
                    String selectedEmpl = (String) employee.getSelectedItem();
                    double priceData = Double.parseDouble(price.getText());

                    Room room = new Room(idPhong, selectedKind, selectedStatus, priceData, selectedCus, selectedEmpl);

                    boolean updateMethod = client.updateRoomClient(room);
                    if (updateMethod) {
                        JOptionPane.showMessageDialog(RoomForm.this, " thành công");
                        updateList(null, false);
                    } else {
                        JOptionPane.showMessageDialog(RoomForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RoomForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
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
                    String idPhong = maPhong.getText();

                    boolean deleteMethod = client.deleteRoomClient(idPhong);
                    if (deleteMethod) {
                        JOptionPane.showMessageDialog(RoomForm.this, " thành công");
                        updateList(null, false);
                    } else {
                        JOptionPane.showMessageDialog(RoomForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RoomForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
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
                    String idPhong = maPhong.getText();

                    List<Room> data = client.getRoomClient(idPhong);
                    if (data != null) {
                        updateList(data, true);

                    } else {
                        JOptionPane.showMessageDialog(RoomForm.this, " thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RoomForm.this, "Vui lòng nhập đúng định dạng dữ liệu", "Lỗi",
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
            List<Room> rooms;

            // Xóa hết các thành phần cũ trong render
            render.removeAll();

            if (!flat) {
                rooms = client.getAllRooms();
            } else {
                rooms = data;
            }

            // Thêm header vào render
            String[] columnNames = { "Ma phong", "Kind", "Status", "Price", "idCustommer", "idEmployee" };

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
            for (Room room : rooms) {
                Object[] rowData = { room.getIdRoom(), room.getKind(), room.getStatus(), room.getPrice(),
                        room.getIdCustomer(), room.getIdEmp() };
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
                    RoomForm window = new RoomForm(client);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

//
