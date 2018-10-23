package ejercicio.banco.dto;

import java.util.List;

public class Bank implements InternalDto {

    private int id;
    private String name;
    private String address;
    private List<Account> users;


    //Constructor (When a bank is created for the first time, it does so without users)
    public Bank(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }


    //Getters and Setters
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public List<Account> getUsers() {
        return users;
    }


    public void setUsers(List<Account> users) {
        this.users = users;
    }



    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append("; ");
        sb.append(name);
        sb.append("; ");
        sb.append(address);

        String line = sb.toString();
        return line;
    }
}