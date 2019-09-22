package exercise.dto;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "BANKS")
public class Bank implements InternalDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String address;
    private List<Account> users;

    public Bank() {
    }

    public Bank(int id) {
        this.id = id;
    }

    //Constructor (When a bank is created for the first time, it does so without users)
    public Bank(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

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


    @Override
    public String toString() {
        return new StringJoiner(", ", Bank.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("address='" + address + "'")
                .add("users=" + users)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return id == bank.id &&
                Objects.equals(name, bank.name) &&
                Objects.equals(address, bank.address) &&
                Objects.equals(users, bank.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, users);
    }
}