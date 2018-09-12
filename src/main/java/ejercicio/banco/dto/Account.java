package ejercicio.banco.dto;

public class Account {

    private int id;
    private String name;
    private float money;
    private String iban;


    //Constructor
    public Account(int id, String name, float money, String iban) {
        this.id = id;
        this.name = name;
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


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
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
        sb.append(name);
        sb.append("; ");
        sb.append(money);
        sb.append("; ");
        sb.append(iban);

        String line = sb.toString();
        return line;
    }
}