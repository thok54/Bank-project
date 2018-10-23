package ejercicio.banco.service;

import ejercicio.banco.dto.Account;
import ejercicio.banco.dto.AccountName;
import ejercicio.banco.dto.Bank;
import ejercicio.banco.dto.BankName;
import ejercicio.banco.dto.DataType;
import ejercicio.banco.dto.Payment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGeneratorFactory implements DataGenerator {
    Random rand = new Random();


    //Todo: UniTests


    @Override
    public Object generate(DataType object, String filename) {
        Object item = getDataType(object);
        randFileWriter(filename, object);
        return object;
    }


    //Creates an object based on the name of the string
    public Object getDataType (DataType type){
        switch (type) {
            case BANK:
                return getBank();
            case ACCOUNT:
                return getAccount();
            case PAYMENT:
                return getPayment();
        }
        return null;
    }

    private Payment getPayment() {
        int id = rand.nextInt(10);
        int userID = rand.nextInt(10);
        int bankID = rand.nextInt(10);
        float money = rand.nextInt(99999);
        Payment randPayment = new Payment(id, userID, bankID, money);
        return randPayment;
    }


    private Account getAccount() {
        int id = rand.nextInt(10);
        float money = rand.nextInt(99999);
        final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
        /*int nameLength = rand.nextInt(5) + 5;
        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 0; i < nameLength; i++) {
            nameBuilder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
        }
        String name = nameBuilder.toString();*/
        AccountName randName = AccountName.values()[rand.nextInt(AccountName.values().length)];
        String name = randName.toString();

        int IBANLength = rand.nextInt(5) + 5;
        StringBuilder IBANBuilder = new StringBuilder();
        for (int i = 0; i < IBANLength; i++) {
            IBANBuilder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
        }
        String iban = IBANBuilder.toString();

        Account randAccount = new Account(id, name, money, iban);
        return randAccount;
    }



    private Bank getBank() {
        int id = rand.nextInt(10);

        BankName randName = BankName.values()[rand.nextInt(BankName.values().length)];
        String name = randName.toString();

        final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
       /* int nameLength = rand.nextInt(5) + 5;
        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 0; i < nameLength; i++) {
            nameBuilder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
        }*/

        int addressLength = rand.nextInt(5) + 10;
        StringBuilder addressBuilder = new StringBuilder();
        for (int i = 0; i < addressLength; i++) {
            addressBuilder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
        }
        String address = addressBuilder.toString();

        Bank randBank = new Bank(id, name, address);
        return randBank;
    }


    public void randFileWriter(String filename, Object object) {

        // Access existing file or creates new one
        File file = new File(filename);

        // Tries to write in the file if it exists
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
                br.write(object.toString());
                br.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to write in file " + file.toString());
        }
    }
}
