package ejercicio.banco.service;

import ejercicio.banco.dto.Account;
import ejercicio.banco.dto.Bank;
import ejercicio.banco.dto.Payment;
import ejercicio.banco.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository repository;

    public List<Payment> processPayments(Bank bestBank) {
        List<Payment> payments = repository.findAll();

        try {
            for (int i = 0; i < payments.size(); i++) {
                if (bestBank.getId() == payments.get(i).getBankId()) {
                    try {
                        Payment payment = payments.get(i);
                        float money = payment.getAmount();
                        Account user = bestBank.getUsers().get(payment.getUserId() - 1);
                        user.setMoney(user.getMoney() - money);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (bestBank != null) {
            fileWriter(String.join(File.separator, "src", "main", "resources", "csv", "AccountsAfterPayments.csv"), bestBank);
        }
        return payments;
    }


    public void fileWriter(String filename, Bank bestBank) {
        if (filename == null) {
            throw new IllegalArgumentException("Filename must not be null");
        }
        File file = new File(filename);

        if (bestBank.getUsers() == null) {
            System.out.println("Unable to read from Bank with no users");
            return;
        }
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < bestBank.getUsers().size(); i++) {
                br.write(bestBank.getUsers().get(i).toString());
                br.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to read file " + file.toString());
        }
    }


    public Payment findPayment(int n) {
        Payment payment = null;
        try {
            payment = repository.find(n);
        } catch (Exception e) {
            e.printStackTrace();
            payment = null;
        }
        return payment;
    }

    public List<Payment> findPayments(String name) {
        int id = Integer.parseInt(name);
        return repository.findByBankId(id);
    }

    public void storePayment(Payment payment) {
        repository.store(payment);
    }

    public void updatePayment(int id, Payment payment) {
        repository.update(id, payment);
    }

    public void deletePayment(int id) {
        repository.delete(id);
    }

}
