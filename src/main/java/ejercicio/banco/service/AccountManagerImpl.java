package main.java.ejercicio.banco.service;

import main.java.ejercicio.banco.dto.Account;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AccountManagerImpl implements AccountManager {
	// Process user
			public List<Account> manage(String filename) throws FileNotFoundException {
				// Creates list of Accounts
				List<Account> accounts = new ArrayList<Account>();

				// Creates an user ID starting at 1, auto-incrementing every new user
				int userId = 1;

				File AccountCsvFile = new File(filename);

				// Reads using Scanner
				Scanner in = new Scanner(AccountCsvFile);

				while (in.hasNextLine()) {
					String line = in.nextLine();
					System.out.println(line);

					// Assuming that the info is separated by "; " ,splits it
					String[] parts = line.split(";");
					String userName = parts[0];
					float money = Float.valueOf(parts[1]);
					String iban = parts[2];

					//Prints user into console
					System.out.println("User "+userId+" named "+userName+" has been processed");

					// Creates a new account and adds it to the list
					Account acc = new Account(userId, userName, money, iban);
					accounts.add(acc);
					// Increments Id counter
					userId++;
				}

				in.close();
				return accounts;
			}

}
