package ejercicio.banco.service;

import ejercicio.banco.dto.Bank;
import ejercicio.banco.dto.Payment;

import java.util.List;

public interface PaymentService{

    public List<Payment> processPayments(String filename, Bank bestBank);

    public void fileWriter(String filename, Bank bestBank);
}