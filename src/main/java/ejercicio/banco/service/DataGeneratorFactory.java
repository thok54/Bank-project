package ejercicio.banco.service;

import ejercicio.banco.dto.Account;
import ejercicio.banco.dto.AccountName;
import ejercicio.banco.dto.Bank;
import ejercicio.banco.dto.BankName;
import ejercicio.banco.dto.DataType;
import ejercicio.banco.dto.InternalDto;
import ejercicio.banco.dto.Payment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGeneratorFactory implements DataGenerator {
    Random rand = new Random();

    @Override
    public InternalDto generate(DataType object, String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("Filename must not be null");
        }
        if (object == null) {
            throw new IllegalArgumentException("Datatype must not be null");
        }
        InternalDto item = getDataType(object, filename);
        return item;
    }

    public InternalDto getDataType(DataType type, String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("Filename must not be null");
        }

        if (type == null) {
            throw new IllegalArgumentException("Datatype must not be null");
        }

        switch (type) {
            case BANK:
                return getBank(filename);
            case ACCOUNT:
                return getAccount(filename);
            case PAYMENT:
                return getPayment(filename);
        }
        return null;
    }

    private Bank getBank(String filename) {
        int id = rand.nextInt(10);

        BankName randName = BankName.values()[rand.nextInt(BankName.values().length)];
        String name = randName.toString();

        final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

        int addressLength = rand.nextInt(5) + 10;
        StringBuilder addressBuilder = new StringBuilder();
        for (int i = 0; i < addressLength; i++) {
            addressBuilder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
        }
        String address = addressBuilder.toString();

        Bank randBank = new Bank(id, name, address);
        randBankWriter(filename, randBank);
        return randBank;
    }

    private Account getAccount(String filename) {
        int id = rand.nextInt(10);
        float money = rand.nextInt(99999);
        final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

        AccountName randName = AccountName.values()[rand.nextInt(AccountName.values().length)];
        String name = randName.toString();

        int IBANLength = rand.nextInt(5) + 5;
        StringBuilder IBANBuilder = new StringBuilder();
        for (int i = 0; i < IBANLength; i++) {
            IBANBuilder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
        }
        String iban = IBANBuilder.toString();

        Account randAccount = new Account(id, name, money, iban);
        randAccountWriter(filename, randAccount);
        return randAccount;
    }

    private Payment getPayment(String filename) {
        int id = rand.nextInt(10);
        int userID = rand.nextInt(10);
        int bankID = rand.nextInt(10);
        float money = rand.nextInt(99999);
        Payment randPayment = new Payment(id, userID, bankID, money);
        randPaymentWriter(filename, randPayment);
        return randPayment;
    }


    private void randBankWriter(String filename, Bank bank) {
        File file = new File(filename);
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            br.write(bank.toString());
            br.newLine();
            System.out.println("Generating random bank: "+bank.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to write in file " + file.toString());
        }
    }

    private void randAccountWriter(String filename, Account account) {
        File file = new File(filename);
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            br.write(account.toString());
            br.newLine();
            System.out.println("Generating random account: "+account.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to write in file " + file.toString());
        }
    }

    private void randPaymentWriter(String filename, Payment payment) {
        File file = new File(filename);
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            br.write(payment.toString());
            br.newLine();
            System.out.println("Generating random payment: "+payment.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to write in file " + file.toString());
        }
    }
}
