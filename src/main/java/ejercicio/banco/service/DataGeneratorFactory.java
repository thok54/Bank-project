package main.java.ejercicio.banco.service;

import main.java.ejercicio.banco.dto.Account;
import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.dto.Payment;

import java.util.Random;

public class DataGeneratorFactory implements DataGenerator {

    @Override
    public Object generate(Object object) {
        Random rand = new Random();
        int type = getDataType(object);

        //Generates random bank
        if(type==1){
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

        //Generates random account
        if(type == 2){
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

        //Generates random payment
        if(type==3){
            int id = rand.nextInt(9999);
            int userID = rand.nextInt(9999);
            int bankID = rand.nextInt(9999);
            float  money = rand.nextInt(99999);
            Payment randPayment = new Payment(id, userID, bankID, money);
            return randPayment;
        }
        return null;
    }


    //Checks the type of object and returns an int based on it. If neither of our objects, returns 0.
    public int getDataType (Object object){
        int n = 0;
        if(object instanceof Bank) {
            n = 1;
        }
        else if(object instanceof Account) {
            n = 2;
        }
        else if(object instanceof Payment) {
            n = 3;
        }
        return n;
    }
}
