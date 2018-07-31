package main.java.ejercicio.banco.service;

import main.java.ejercicio.banco.dto.Bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BankManagerImpl implements BankManager {

	// Process Bank. If I understand correctly, there should be only one bank,
			// so we only need to read one line from this file
			public Bank manage(String bankFile) throws FileNotFoundException {

				File bankCsvFile = new File(bankFile);

				// Reads using Scanner
				Scanner in = new Scanner(bankCsvFile);

				String line = in.nextLine();
				System.out.println(line);

				// Assuming that the info is separated by "; " ,splits it
				String[] parts = line.split(";");
				int bankId = Integer.parseInt(parts[0]);
				String bankName = parts[1];
				String bankAddress = parts[2];

				//Prints bank info in concolse
				System.out.println("Our bank "+bankName+" has been processed");


				// Creates a new bank
				Bank bnk = new Bank(bankId, bankName, bankAddress);

				in.close();
				return bnk;
			}
}
