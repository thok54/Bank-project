package main.java.ejercicio.banco.service;

import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.dto.Payment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PaymentManagerImpl implements PaymentManager {
	// Process payment
			public List<Payment> manage(String paymentFile, Bank bestBank) throws FileNotFoundException {
				// Creates list of payments
				List<Payment> payments = new ArrayList<Payment>();

				File paymentCsvFile = new File(paymentFile);

				// Reads using Scanner
				Scanner in = new Scanner(paymentCsvFile);

				while (in.hasNextLine()) {
					String line = in.nextLine();
					System.out.println(line);

					// Assuming that the info is separated by "; " ,splits it
					String[] parts = line.split(";");
					int paymentId = Integer.parseInt(parts[0]);
					int bankId = Integer.parseInt(parts[1]);
					int userId = Integer.parseInt(parts[2]);
					float amount = Float.valueOf(parts[3]);

					System.out.println("Processing payment "+paymentId+ " of"+amount+"$");

					// Creates a new payment and adds it to the list
					Payment pay = new Payment(paymentId, bankId, userId, amount);
					payments.add(pay);

					// If our bank is involved in payment, resolves it
					if (bestBank.getBankId() == bankId) {

						// Modifies the money from the user after payment(assuming user is paying)
						bestBank.getUsers().get(userId - 1).setMoney(bestBank.getUsers().get(userId - 1).getMoney() - amount);

					}
				}
				in.close();
				
				//Writes accounts after payments
				fwriter("src\\main\\resources\\csv\\AccountsAfterPayments", bestBank);
				
				return payments;
			}
				
			
			// File writer helper method.
			public void fwriter(String filename, Bank bestBank) {

				// Creates a file with specified name
				File file = new File(filename);

					// Tries to write in the file...
					try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {

						// ...for every user in our bank...
						for (int i = 0; i < bestBank.getUsers().size(); i++) {

							br.write(bestBank.getUsers().get(i).toString());
							br.newLine();
						}


					} catch (IOException e) {
						System.out.println("Unable to read file " + file.toString());
					}
				}

}
