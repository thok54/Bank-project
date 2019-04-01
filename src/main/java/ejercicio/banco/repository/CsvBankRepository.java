package ejercicio.banco.repository;

import ejercicio.banco.dto.Bank;
import ejercicio.banco.util.CsvReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CsvBankRepository implements BankRepository {

    private static final String FILENAME = String.join(File.separator, "src", "main", "resources", "csv", "bank.csv");
    private CsvReader reader;

    public CsvBankRepository() {
        this.reader = new CsvReader();
    }

    @Override
    public List<Bank> findAll(String filename) {
        List<Bank> banks = new ArrayList<Bank>();
        List<String> in = new ArrayList<String>();
        try {
            in = reader.read(filename);

            for (int x = 0; x < in.size(); x++) {
                String line = in.get(x);
                System.out.println(line);

                try {
                    String[] parts = line.split(";");
                    int bankId = Integer.parseInt(parts[0]);
                    String bankName = parts[1];
                    String bankAddress = parts[2];

                    System.out.println("Our bank " + bankName + " has been processed");
                    Bank bnk = new Bank(bankId, bankName, bankAddress);
                    banks.add(bnk);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                    System.out.println("Unable to parse number " + line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("filename " + filename + " was not found");
        }
        return banks;
    }

    @Override
    public Bank find(int id) {
        List<Bank> all = findAll(FILENAME);

        for (Bank bank : all) {
            if (id == bank.getId()) {
                return bank;
            }
        }
        return null;
    }

    public List<Bank> findByName(String filename, String name) {
        List<Bank> all = findAll(filename);
        List<Bank> results = new ArrayList<>();
        for (Bank bank : all) {
            if (bank.getName().contains(name)) {
                results.add(bank);
            }
        }
        return results;
    }

    @Override
    public void store(Bank bank) {
        try {
            Files.write(Paths.get(FILENAME), bank.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Unable to read file " + FILENAME);
        }
    }

    @Override
    public void update(int id, Bank bank) {
        List<String> in = null;
        String line;
        try {
            in = reader.read(FILENAME);
            line = in.get(id);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.out.println("Failed to find file");
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
            System.out.println("Bank not in list");
            return;
        }
        System.out.println("Updating bank " + line + " to be " + bank);
    }

    @Override
    public void delete(int id) {
        List<String> in = null;
        try {
            in = reader.read(FILENAME);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.out.println("Failed to delete");
            return;
        }
        String line = in.get(id);
        System.out.println("Deleting bank " + line);
    }
}
