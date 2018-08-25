package main.java.ejercicio.banco.service;

import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.dto.Payment;

import java.io.FileNotFoundException;
import java.util.List;

public interface PaymentService {

    public List<Payment> processPayments(String filename, Bank bestBank) throws FileNotFoundException;

    public void fileWriter(String filename, Bank bestBank);
}