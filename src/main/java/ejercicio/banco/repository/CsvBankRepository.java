package main.java.ejercicio.banco.repository;

import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.util.CsvReader;

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
    public List<Bank> findAll(String filename) throws FileNotFoundException {
        // Creates list of Banks
        List<Bank> banks = new ArrayList<Bank>();
        List<String> in = reader.read(filename);

        for (int x = 0; x < in.size(); x++) {
            String line = in.get(x);
            System.out.println(line);

            // Assuming that the info is separated by "; " ,splits it
            String[] parts = line.split(";");
            int bankId = Integer.parseInt(parts[0]);
            String bankName = parts[1];
            String bankAddress = parts[2];

            //Prints bank info in concolse
            System.out.println("Our bank " + bankName + " has been processed");


            // Creates a new bank
            Bank bnk = new Bank(bankId, bankName, bankAddress);
            banks.add(bnk);
        }
        return banks;
    }

    @Override
    public Bank find(int id) throws FileNotFoundException {
        List<Bank> all = findAll(FILENAME);

        for (Bank bank : all) {
            if (id == bank.getId()) {
                return bank;
            }
        }
        //Method requires a return statement
        return all.get(0);
    }

    @Override
    public void store(Bank bank) throws IOException {
        //Adds account to file
        try {
            Files.write(Paths.get(FILENAME), bank.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Unable to read file " + FILENAME);
        }
    }

    @Override
    public void update(int id, Bank bank) throws FileNotFoundException {
        List<String> in = reader.read(FILENAME);

        String line = in.get(id);

        //For now, we just print "Updating"
        System.out.println("Updating bank " + line + " to be " + bank);
    }

    @Override
    public void delete(int id) throws FileNotFoundException {
        List<String> in = reader.read(FILENAME);

        String line = in.get(id);

        //For now, we just print "deleting"
        System.out.println("Deleting bank " + line);
    }
}
