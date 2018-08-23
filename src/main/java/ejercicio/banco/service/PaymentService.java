package main.java.ejercicio.banco.service;

import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.dto.Payment;

import java.io.FileNotFoundException;
import java.util.List;

public interface PaymentService {

    public List<Payment> manage(String filename, Bank bestBank) throws FileNotFoundException;

    public void fwriter(String filename, Bank bestBank);
}