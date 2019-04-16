package ejercicio.banco.service;

import ejercicio.banco.dto.Bank;

import java.sql.SQLException;


public interface BankService {
    Bank processBank(String filename);
}