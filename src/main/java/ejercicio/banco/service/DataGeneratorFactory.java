package main.java.ejercicio.banco.service;

import main.java.ejercicio.banco.dto.Account;
import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.dto.Payment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGeneratorFactory implements DataGenerator {

    @Override
    public Object generate(String object, String filename) {
        Object item = getDataType(object);
        randFileWriter(filename, object);
        return object;
    }


    //Creates an object based on the name of the string
    public Object getDataType (String type){

        Random rand = new Random();

        if ("Bank".equalsIgnoreCase(type)) {
            int  n = rand.nextInt(9999) + 1;
            final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
            int nameLength = rand.nextInt(5)+5;
            StringBuilder nameBuilder = new StringBuilder();
            for(int i = 0; i < nameLength; i++) {
                nameBuilder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            String name = nameBuilder.toString();

            int addressLength = rand.nextInt(5)+10;
            StringBuilder addressBuilder = new StringBuilder();
            for(int i = 0; i < addressLength; i++) {
                addressBuilder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            String address = addressBuilder.toString();

            Bank randBank = new Bank(n, name, address);
            return randBank;

        }

        if ("Account".equalsIgnoreCase(type)) {
            int id = 0;

            float  money = rand.nextInt(99999);
            final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
            int nameLength = rand.nextInt(5)+5;
            StringBuilder nameBuilder = new StringBuilder();
            for(int i = 0; i < nameLength; i++) {
                nameBuilder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            String name = nameBuilder.toString();

            int IBANLength = rand.nextInt(5)+5;
            StringBuilder IBANBuilder = new StringBuilder();
            for(int i = 0; i < IBANLength; i++) {
                IBANBuilder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            String iban = IBANBuilder.toString();

            Account randAccount = new Account(id, name, money, iban);
            return randAccount;
        }

        if ("Payment".equalsIgnoreCase(type)) {
            int id = rand.nextInt(9999);
            int userID = rand.nextInt(9999);
            int bankID = rand.nextInt(9999);
            float  money = rand.nextInt(99999);
            Payment randPayment = new Payment(id, userID, bankID, money);
            return randPayment;
        }
        return null;
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
