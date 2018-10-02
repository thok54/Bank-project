package main.java.ejercicio.banco.service;

import main.java.ejercicio.banco.dto.Account;
import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.dto.Payment;

public class DataGeneratorFactory implements DataGenerator {

    @Override
    public Object generate(Object object) {
        int type = getDataType(object);

        //Generates random bank
        if(type==1){

        }

        //Generates random account
        if(type == 2){

        }

        //Generates random payment
        if(type==3){

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
