package main.java.ejercicio.banco.service;

import main.java.ejercicio.banco.dto.Account;
import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.dto.Payment;
import main.java.ejercicio.banco.repository.PaymentRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository repository;

    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    // Process payment
    public List<Payment> processPayments(String filename, Bank bestBank) {
        // Creates list of payments
        List<Payment> payments = repository.findAll(filename);

        try {
            //Iterates through the list, processing payments
            for (int i = 0; i < payments.size(); i++) {

                // If our bank is involved in payment, resolves it
                if (bestBank.getId() == payments.get(i).getBankId()) {

                    try {
                        // Modifies the money from the user after payment(assuming user is paying)
                        Payment payment = payments.get(i);
                        float money = payment.getAmount();
                        Account user = bestBank.getUsers().get(payment.getUserId() - 1);
                        user.setMoney(user.getMoney() - money);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        catch(Exception e){e.printStackTrace();}

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
            e.printStackTrace();
            System.out.println("Unable to read file " + file.toString());
        }
    }


    // Find payment
    public Payment findPayment(int n) {
        // Creates list of Accounts
        Payment payment = null;
        try {
            payment = repository.find(n);
        }
        catch(Exception e){
            e.printStackTrace();
            payment = null;
        }
        return payment;
    }




    public void storePayment(Payment payment){
        repository.store(payment);
    }

    public void updatePayment(int id, Payment payment){
        repository.update(id,payment);
    }

    public void deletePayment(int id){
        repository.delete(id);
    }

}
