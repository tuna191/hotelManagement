package Model.room;

import java.io.Serializable;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idRoom;
    private String kind;
    private String status;
    private double price;
    private String idCustomer;
    private String idEmp;

    public Room() {}

    public Room(String idRoom, String kind, String status, double price, String idCustomer, String idEmp) {
        this.idRoom = idRoom;
        this.kind = kind;
        this.status = status;
        this.price = price;
        this.idCustomer = idCustomer;
        this.idEmp = idEmp;
    }

    // Setter methods
    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setIdEmp(String idEmp) {
        this.idEmp = idEmp;
    }

    // Getter methods
    public String getIdRoom() {
        return idRoom;
    }

    public String getKind() {
        return kind;
    }

    public String getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public String getIdEmp() {
        return idEmp;
    }
}

