package main.java.ejercicio.banco.repository;

import main.java.ejercicio.banco.dto.Payment;
import main.java.ejercicio.banco.util.CsvReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CsvPaymentRepository implements PaymentRepository {

    private static final String FILENAME = String.join(File.separator, "src", "main", "resources", "csv", "payments.csv");

    private CsvReader reader;

    public CsvPaymentRepository() {
        this.reader = new CsvReader();
    }

    @Override
    public List<Payment> findAll(String filename) throws FileNotFoundException {
        // Creates list of payments
        List<Payment> payments = new ArrayList<Payment>();

        List<String> in = reader.read(filename);
        for (int x = 0; x < in.size(); x++) {
            String line = in.get(x);
            System.out.println(line);

            // Assuming that the info is separated by "; " ,splits it
            String[] parts = line.split(";");
            int paymentId = Integer.parseInt(parts[0]);
            int bankId = Integer.parseInt(parts[1]);
            int userId = Integer.parseInt(parts[2]);
            float amount = Float.valueOf(parts[3]);

            System.out.println("Processing payment " + paymentId + " of" + amount + "$");

            // Creates a new payment and adds it to the list
            Payment pay = new Payment(paymentId, bankId, userId, amount);
            payments.add(pay);
        }
        return payments;
    }

    @Override
    public Payment find(int id) throws FileNotFoundException {
        List<Payment> all = findAll(FILENAME);

        for (Payment payment : all) {
            if (id == payment.getPaymentId()) {
                return payment;
            }
        }
        //Method requires a return statement
        return null;
    }

    @Override
    public void store(Payment payment) throws IOException {
        //Adds account to file
        try {
            Files.write(Paths.get(FILENAME), payment.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Unable to read file " + FILENAME);
        }
    }

    @Override
    public void update(int id, Payment payment) throws FileNotFoundException {
        List<String> in = reader.read(FILENAME);

        String line = in.get(id);

        //For now, we just print "Updating"
        System.out.println("Updating payment " + line + " to be " + payment);
    }

    @Override
    public void delete(int id) throws FileNotFoundException {
        List<String> in = reader.read(FILENAME);

        String line = in.get(id);

        //For now, we just print "deleting"
        System.out.println("Deleting payment " + line);
    }
}
