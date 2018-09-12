package ejercicio.banco.repository;

import ejercicio.banco.dto.Payment;
import ejercicio.banco.util.CsvReader;

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
    public List<Payment> findAll(String filename){
        // Creates list of payments
        List<Payment> payments = new ArrayList<Payment>();

        List<String> in = null;
        try {
            in = reader.read(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            in = null;
        }


        for (int x = 0; x < in.size(); x++) {
            String line = in.get(x);
            System.out.println(line);
            Payment pay;

            try {
                // Assuming that the info is separated by "; " ,splits it
                String[] parts = line.split(";");
                int paymentId = Integer.parseInt(parts[0]);
                int bankId = Integer.parseInt(parts[1]);
                int userId = Integer.parseInt(parts[2]);
                float amount = Float.valueOf(parts[3]);
                System.out.println("Processing payment " + paymentId + " of" + amount + "$");
                // Creates a new payment and adds it to the list
                pay = new Payment(paymentId, bankId, userId, amount);
                payments.add(pay);
            }
            catch (NumberFormatException e){
                e.printStackTrace();
            }
        }

        return payments;
    }

    @Override
    public Payment find(int id) {
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
    public void store(Payment payment) {
        //Adds account to file
        try {
            Files.write(Paths.get(FILENAME), payment.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to read file " + FILENAME);
        }
    }

    @Override
    public void update(int id, Payment payment)  {
        List<String> in = null;
        String line;
        try {
            in = reader.read(FILENAME);
            line = in.get(id);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to find file");
            return;
        }
        catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("Payment not in list");
            line = "ERROR";
        }

        //For now, we just print "Updating"
        System.out.println("Updating payment " + line + " to be " + payment);
    }

    @Override
    public void delete(int id){
        List<String> in = null;
        try {
            in = reader.read(FILENAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to delete payment");
            return;
        }

        String line = in.get(id);

        //For now, we just print "deleting"
        System.out.println("Deleting payment " + line);
    }
}
