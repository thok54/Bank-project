package main.java.ejercicio.banco.repository;

import main.java.ejercicio.banco.dto.Account;
import main.java.ejercicio.banco.util.CsvReader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvAccountRepository implements AccountRepository {

    private CsvReader reader;

    public CsvAccountRepository(){
        this.reader = new CsvReader();
    }

    @Override
    public List<Account> findAll(String filename) throws FileNotFoundException {


        // Creates list of Accounts
        List<Account> accounts = new ArrayList<Account>();

        // Creates an user ID starting at 1, auto-incrementing every new user
        int userId = 1;

        List<String> in = reader.read(filename);

        for (int x = 0; x<in.size(); x++) {
            String line = in.get(x);
            System.out.println(line);

            // Assuming that the info is separated by "; " ,splits it
            String[] parts = line.split(";");
            String userName = parts[0];
            float money = Float.valueOf(parts[1]);
            String iban = parts[2];

            //Prints user into console
            System.out.println("User " + userId + " named " + userName + " has been processed");

            // Creates a new account and adds it to the list
            Account acc = new Account(userId, userName, money, iban);
            accounts.add(acc);
            // Increments Id counter
            userId++;
        }
        return accounts;
    }


    @Override
    public Account find(int id) throws FileNotFoundException {
        //Since we dont want to give the method a file parameter, Im hardcoding the filename
        String filename = String.join(File.separator, "src", "main", "resources", "csv", "accounts.csv");
        List<String> in = reader.read(filename);

        String line = in.get(id);
        System.out.println(line);

        // Assuming that the info is separated by "; " ,splits it
        String[] parts = line.split(";");
        String userName = parts[0];
        float money = Float.valueOf(parts[1]);
        String iban = parts[2];

        //Prints user into console
        System.out.println("User " + id + " named " + userName + " has been processed");

        // Creates a new account and returns it
        Account acc = new Account(id, userName, money, iban);
        return acc;
    }

    @Override
    public void store(Account account) throws IOException {
        String filename = String.join(File.separator, "src", "main", "resources", "csv", "accounts.csv");

        //Adds account to file
        try {
            Files.write(Paths.get(filename), account.toString().getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            System.out.println("Unable to read file " + filename);
        }
    }

    @Override
    public void update(int id, Account account) throws FileNotFoundException {
        String filename = String.join(File.separator, "src", "main", "resources", "csv", "accounts.csv");
        List<String> in = reader.read(filename);

        String line = in.get(id);

        //For now, we just print "Updating"
        System.out.println("Updating account "+line+" to be "+ account);
    }

    @Override
    public void delete(int id) throws FileNotFoundException {
        String filename = String.join(File.separator, "src", "main", "resources", "csv", "accounts.csv");
        List<String> in = reader.read(filename);

        String line = in.get(id);

        //For now, we just print "deleting"
        System.out.println("Deleting account "+line);
    }
}
