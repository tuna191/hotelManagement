package model.room;

import java.io.Serializable;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idRoom;
    private String kind;
    private String state;
    private double price;
    private String idCustomer;
    private String idEmp;

    public Room() {}

    public Room(String idRoom, String kind, String state, double price, String idCustomer, String idEmp) {
        this.idRoom = idRoom;
        this.kind = kind;
        this.state = state;
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

    public void setState(String state) {
        this.state = state;
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

    public String getState() {
        return state;
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

