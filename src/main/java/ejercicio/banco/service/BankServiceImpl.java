package main.java.ejercicio.banco.service;

import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.repository.BankRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class BankServiceImpl implements BankService {

	private BankRepository repository;
	public BankServiceImpl(BankRepository repository){
		this.repository = repository;
	}
			public Bank manage(String filename) throws FileNotFoundException {
				List<Bank> banks = repository.findAll(filename);
				return banks.get(0);
			}
}
