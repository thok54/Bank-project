package main.java.ejercicio.banco.service;

import main.java.ejercicio.banco.dto.Bank;

import java.io.FileNotFoundException;

public interface BankManager {
	public Bank manage(String filename) throws FileNotFoundException;
}