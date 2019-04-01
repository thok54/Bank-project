package ejercicio.banco.service;

import ejercicio.banco.dto.Bank;

import java.sql.SQLException;


public interface BankService {
    public Bank processBank(String filename);
}