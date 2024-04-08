package model.Employees;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeImplement extends UnicastRemoteObject implements EmployeeInterface {
    private static final long serialVersionUID = 1L;
    public EmployeeImplement() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }

    @Override
    public List<Employee> getEmployees(String idEmp) throws RemoteException {
        List<Employee> employees = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBC.getConnection();
            String sql = "SELECT * FROM employee where idEmp = " + idEmp;
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setIdEmp(rs.getString("idEmp"));
                employee.setNameEmp(rs.getString("nameEmp"));
                employee.setAddress(rs.getString("address"));
                employee.setSex(rs.getString("sex"));
                employee.setbirth(rs.getString("birth"));
                employee.setPhone(rs.getInt("phone"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public List<Employee> getAllEmployees() throws RemoteException {
        List<Employee> employees = new ArrayList<Employee>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = JDBC.getConnection();
            String sql = "SELECT * FROM employee";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setIdEmp(rs.getString("idEmp"));
                employee.setNameEmp(rs.getString("nameEmp"));
                employee.setAddress(rs.getString("address"));
                employee.setSex(rs.getString("sex"));
                employee.setbirth(rs.getString("birth"));
                employee.setPhone(rs.getInt("phone"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public boolean addEmployee(Employee employee) throws RemoteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean result = false;
        
        try {
            conn = JDBC.getConnection();
            String sql = "INSERT INTO employee Values(?,?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,employee.getNameEmp() );
            stmt.setString(2,employee.getAddress() );
            stmt.setString(3,employee.getSex() );
            stmt.setString(4,employee.getbirth() );
            stmt.setInt(5,employee.getPhone() );
            stmt.setString(6,employee.getIdEmp() );
            int rowsAffected = stmt.executeUpdate();
            result = rowsAffected > 0;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean removeEmployee(String idEmp) throws RemoteException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            conn = JDBC.getConnection();
            String sql = "DELETE FROM employee WHERE idEmp = " + idEmp;
            stm = conn.prepareStatement(sql);
            int rowsAffected = stm.executeUpdate();
            result = rowsAffected > 0;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
        return result;
    }

    @Override
    public boolean updateEmployee(Employee employee) throws RemoteException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean result = false;
        try {
            conn = JDBC.getConnection();
            String sql = "UPDATE employee SET nameEmp = ? , address = ? , sex = ?,birth = ?,phone = ? WHERE idEmp = ? "; 
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,employee.getNameEmp() );
            stmt.setString(2,employee.getAddress() );
            stmt.setString(3,employee.getSex() );
            stmt.setString(4,employee.getbirth() );
            stmt.setInt(5,employee.getPhone() );
            stmt.setString(6,employee.getIdEmp() );
            int rowsAffected = stmt.executeUpdate();
            result = rowsAffected > 0;
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return result;
    }
    
}
