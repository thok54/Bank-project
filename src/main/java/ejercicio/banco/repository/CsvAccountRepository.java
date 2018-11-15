package ejercicio.banco.repository;

import ejercicio.banco.dto.Account;
import ejercicio.banco.util.CsvReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CsvAccountRepository implements AccountRepository {

    private static final String FILENAME = String.join(File.separator, "src", "main", "resources", "csv", "accounts.csv");

    private CsvReader reader;

    public CsvAccountRepository() {
        this.reader = new CsvReader();
    }

    @Override
    public List<Account> findAll(String filename) {
        List<Account> accounts = new ArrayList<Account>();
        int userId = 1;
        List<String> in = null;
        try {
            in = reader.read(filename);

            for (int x = 0; x < in.size(); x++) {
                String line = in.get(x);
                System.out.println(line);

                String[] parts = line.split(";");
                String userName;
                float money;
                String iban;
                Account acc;

                try {
                    userName = parts[0];
                    money = Float.valueOf(parts[1]);
                    iban = parts[2];
                    System.out.println("User " + userId + " named " + userName + " has been processed");
                    acc = new Account(userId, userName, money, iban);
                    accounts.add(acc);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.println("Unable to parse number " + line);
                }
                userId++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("filename " + filename + " was not found");
        }
        return accounts;
    }


    @Override
    public Account find(int id) throws AccountNotFoundException {
        List<Account> all = null;
        all = findAll(FILENAME);

        for (Account account : all) {
            if (id == account.getId()) {
                return account;
            }
        }
        return null;
    }

    public List<Account> findByName(String filename, String name) {
        List<Account> all = findAll(filename);
        List<Account> results = null;
        for (Account account : all) {
            if (account.getName().contains(name)) {
                results.add(account);
            }
        }
        return results;
    }

    @Override
    public void store(Account account) {

        //Adds account to file
        try {
            Files.write(Paths.get(FILENAME), account.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to read file " + FILENAME);
        }
    }

    @Override
    public void update(int id, Account account) {

        List<String> in = null;
        String line;
        try {
            in = reader.read(FILENAME);
            line = in.get(id);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to find file");
            return;
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Account not in list");
            return;
        }

        //For now, we just print "Updating"
        System.out.println("Updating account " + line + " to be " + account);
    }

    @Override
    public void delete(int id) {
        List<String> in = null;
        try {
            in = reader.read(FILENAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            in = null;
        }

        String line = in.get(id);

        //For now, we just print "deleting"
        System.out.println("Deleting account " + line);
    }
}
