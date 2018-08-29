package main.java.ejercicio.banco.service;

import main.java.ejercicio.banco.dto.Bank;

import java.io.FileNotFoundException;

public interface BankService {
    public Bank processBank(String filename) throws FileNotFoundException;
}