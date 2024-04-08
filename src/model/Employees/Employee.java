package model.Employees;

import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idEmp;
    private String nameEmp;
    private String address;
    private String sex;
    private String birth;
    private int phone;
    public Employee() {
    }
    public Employee(String idEmp, String nameEmp, String address, String sex, String birth, int phone) {
        this.idEmp = idEmp;
        this.nameEmp = nameEmp;
        this.address = address;
        this.sex = sex;
        this.birth = birth;
        this.phone = phone;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getIdEmp() {
        return idEmp;
    }
    public void setIdEmp(String idEmp) {
        this.idEmp = idEmp;
    }
    public String getNameEmp() {
        return nameEmp;
    }
    public void setNameEmp(String nameEmp) {
        this.nameEmp = nameEmp;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getbirth() {
        return birth;
    }
    public void setbirth(String birth) {
        this.birth = birth;
    }
    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }

    
}
