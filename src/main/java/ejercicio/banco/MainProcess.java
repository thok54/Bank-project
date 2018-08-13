package main.java.ejercicio.banco;
import main.java.ejercicio.banco.dto.Account;
import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.dto.Payment;
import main.java.ejercicio.banco.service.*;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;

public class MainProcess {

    // Read csv, process info, and write result in new csv
    public static void main(String[] args) throws FileNotFoundException {
        //This will identify wether to repeat process or not
        Boolean yesNo = true;

        BankManager bankManager = new BankManagerImpl();
        AccountManager accountManager = new AccountManagerImpl();
        PaymentManager paymentManager = new PaymentManagerImpl();
        // File names might change depending on file location
        String bankFile = String.join(File.separator, "src", "main", "resources", "csv", "bank.csv");
        String accountFile = String.join(File.separator, "src", "main", "resources", "csv", "accounts.csv");
        String paymentFile = String.join(File.separator, "src", "main", "resources", "csv", "payments.csv");

        // Creates the bank
        Bank bestBank = bankManager.manage(bankFile);

        // Creates account list with Account manager
        List<Account> accounts = accountManager.manage(accountFile);

        // Stores all the accounts into our bank before payments
        bestBank.setUsers(accounts);


        //Writes accounts before payments
        paymentManager.fwriter(String.join(File.separator, "src", "main", "resources", "csv", "accountsBeforePayments.csv"), bestBank);


        //This is the code that will be repeated if user wants
        while (yesNo) {
            List<Payment> payments = paymentManager.manage(paymentFile, bestBank);


            Scanner scan = new Scanner(System.in);


            Boolean repeat = true;
            while (repeat) {

                System.out.println("Do you wish to continue?(y/n)");

                //Waits for input
                String answer = scan.nextLine();

                //For comparing strings, use "equals()" rather than "=="
                if (answer.equals("y")) {
                    yesNo = true;
                    repeat = false;
                } else if (answer.equals("n")) {
                    yesNo = false;
                    repeat = false;
                }
            }
        }
    }


}
