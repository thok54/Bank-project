package main.java.ejercicio.banco.dto;

import java.util.List;

public class Bank {

    private int id;
    private String bankName;
    private String bankAddress;
    private List<Account> users;


    //Constructor (When a bank is created for the first time, it does so without users)
    public Bank(int id, String bankName, String bankAddress) {
        this.id = id;
        this.bankName = bankName;
        this.bankAddress = bankAddress;
    }


    //Getters and Setters
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getBankName() {
        return bankName;
    }


    public void setBankName(String bankName) {
        this.bankName = bankName;
    }


    public String getBankAddress() {
        return bankAddress;
    }


    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }


    public List<Account> getUsers() {
        return users;
    }


    public void setUsers(List<Account> users) {
        this.users = users;
    }


}