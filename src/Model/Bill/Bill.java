package Model.Bill;

import java.io.Serializable;
import java.sql.Date;

public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idBill;
    private Date date;
    private Double price;
    private String idEmp;
    public Bill() {
    }
    public Bill(String idBill,Date date, Double price,String idEmp) {
        this.idBill = idBill;
        this.date = date;
        this.price = price;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getIdBill() {
        return idBill;
    }
    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getIdEmp() {
        return idEmp;
    }
    public void setIdEmp(String idEmp) {
        this.idEmp = idEmp;
    }
}

