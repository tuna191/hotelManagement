package Model.Customer;

import java.io.Serializable;
import java.sql.Date;


public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idcustomer;
    private String name;
    private String address;
    private String sex;
    private int identify;
    private int phone;
    public Customer() {
    }
    public Customer(String idcustomer,String name, String address,String sex, Integer identify, Integer phone) {
        this.idcustomer = idcustomer;
        this.name = name;
        this.address = address;
        this.sex = sex;
        this.identify = identify;
        this.phone = phone;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getIdcustomer() {
        return idcustomer;
    }
    public void setIdcustomer(String idcustomer) {
        this.idcustomer = idcustomer;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name= name;
    }
    public String getAdress() {
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
    public int getIdentify() {
        return identify;
    }
    public void setIdentify(int identify) {
        this.identify = identify;
    }
    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
}


