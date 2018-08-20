package main.java.ejercicio.banco.dto;

import java.util.List;

public class Bank {

    //Variables
    private int bankId;
    private String bankName;
    private String bankAddress;
    private List<Account> users;


    //Constructor (When a bank is created for the first time, it does so without users)
    public Bank(int bankId, String bankName, String bankAddress) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.bankAddress = bankAddress;
    }


    //Getters and Setters
    public int getBankId() {
        return bankId;
    }


    public void setBankId(int bankId) {
        this.bankId = bankId;
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