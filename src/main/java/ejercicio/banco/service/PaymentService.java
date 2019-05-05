package ejercicio.banco.service;

import ejercicio.banco.dto.Bank;
import ejercicio.banco.dto.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> processPayments(Bank bestBank);

    void fileWriter(String filename, Bank bestBank);
}