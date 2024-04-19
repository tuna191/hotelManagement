package Model.Bill;

import java.io.Serializable;
import java.sql.Date;

public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idBill;
    private Date dateTT;
    private Double price;
    private String idEmp;
    public Bill() {
    }
    public Bill(String idBill,Date dateTT, Double price,String idEmp) {
        this.idBill = idBill;
        this.dateTT = dateTT;
        this.price = price;
        this.idEmp = idEmp;
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
    public Date getDateTT() {
        return dateTT;
    }
    public void setDateTT(Date dateTT) {
        this.dateTT = dateTT;
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

