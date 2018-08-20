package main.java.ejercicio.banco.service;

import main.java.ejercicio.banco.dto.Account;

import java.io.FileNotFoundException;
import java.util.List;

public interface AccountService {
	
	public List<Account> manage(String filename) throws FileNotFoundException;
}