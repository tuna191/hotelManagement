package Model.Bill;

import java.io.Serializable;
import java.sql.Date;

public class BillDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idDetail;
    private Date date;
    private String idRoom;
    private String idBill;

    public BillDetail() {
    }
    public BillDetail(String idDetail,Date date, String idRoom,String idBill) {
        this.idDetail = idDetail;
        this.date = date;
        this.idRoom = idRoom;
        this.idBill = idBill;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getIdDetail() {
        return idDetail;
    }
    public void setIdDetail(String idDetail) {
        this.idDetail =  idDetail;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getIdRoom() {
        return idRoom;
    }
    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }
    public String getIdBill() {
        return idBill;
    }
    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }
}

