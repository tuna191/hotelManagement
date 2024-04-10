package model.Administrator;

import java.io.Serializable;

public class Admin implements Serializable {
    private static final long serialVersionUID =1L;

    private String name;
    private String password;
    public Admin() {
    }
    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}
