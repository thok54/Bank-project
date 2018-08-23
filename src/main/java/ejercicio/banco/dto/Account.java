package main.java.ejercicio.banco.dto;

public class Account {

    private int id;
    private String userName;
    private float money;
    private String iban;


    //Constructor
    public Account(int id, String userName, float money, String iban) {
        this.id = id;
        this.userName = userName;
        this.money = money;
        this.iban = iban;
    }


    //Getters and Setters
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public float getMoney() {
        return money;
    }


    public void setMoney(float money) {
        this.money = money;
    }


    public String getIban() {
        return iban;
    }


    public void setIban(String iban) {
        this.iban = iban;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append("; ");
        sb.append(userName);
        sb.append("; ");
        sb.append(money);
        sb.append("; ");
        sb.append(iban);

        String line = sb.toString();
        return line;
    }
}