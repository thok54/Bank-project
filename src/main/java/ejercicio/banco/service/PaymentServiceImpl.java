package main.java.ejercicio.banco.service;

import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.dto.Payment;
import main.java.ejercicio.banco.repository.PaymentRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository repository;

    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    // Process payment
    public List<Payment> processPayments(String filename, Bank bestBank) throws FileNotFoundException {
        // Creates list of payments
        List<Payment> payments = repository.findAll(filename);

        //Iterates through the list, processing payments
        for (int i = 0; i < payments.size(); i++) {

            // If our bank is involved in payment, resolves it
            if (bestBank.getId() == payments.get(i).getBankId()) {

                // Modifies the money from the user after payment(assuming user is paying)
                bestBank.getUsers().get(payments.get(i).getUserId() - 1).setMoney(bestBank.getUsers().get(payments.get(i).getUserId() - 1).getMoney() - payments.get(i).getAmount());
            }
        }

        //Writes accounts after payments
        fileWriter(String.join(File.separator, "src", "main", "resources", "csv", "AccountsAfterPayments.csv"), bestBank);

        return payments;
    }


    // File writer helper method.
    public void fileWriter(String filename, Bank bestBank) {

        // Creates a file with specified name
        File file = new File(filename);

        // Tries to write in the file...
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {

            // ...for every user in our bank...
            for (int i = 0; i < bestBank.getUsers().size(); i++) {

                br.write(bestBank.getUsers().get(i).toString());
                br.newLine();
            }


        } catch (IOException e) {
            System.out.println("Unable to read file " + file.toString());
        }
    }

}
