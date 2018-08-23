package main.java.ejercicio.banco;

import main.java.ejercicio.banco.dto.Account;
import main.java.ejercicio.banco.dto.Bank;
import main.java.ejercicio.banco.dto.Payment;
import main.java.ejercicio.banco.repository.CsvAccountRepository;
import main.java.ejercicio.banco.repository.CsvBankRepository;
import main.java.ejercicio.banco.repository.CsvPaymentRepository;
import main.java.ejercicio.banco.repository.MySqlAccountRepository;
import main.java.ejercicio.banco.repository.MySqlBankRepository;
import main.java.ejercicio.banco.repository.MySqlPaymentRepository;
import main.java.ejercicio.banco.service.AccountService;
import main.java.ejercicio.banco.service.AccountServiceImpl;
import main.java.ejercicio.banco.service.BankService;
import main.java.ejercicio.banco.service.BankServiceImpl;
import main.java.ejercicio.banco.service.PaymentService;
import main.java.ejercicio.banco.service.PaymentServiceImpl;


import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;

public class MainProcess {

    // Read csv, process info, and write result in new csv
    public static void main(String[] args) throws FileNotFoundException {
        //This will identify wether to repeat process or not
        Boolean doAgain = true;


        BankService bankService;
        AccountService accountService;
        PaymentService paymentService;

        Scanner scann = new Scanner(System.in);
        System.out.println("Do you wish to work with csv or sql?(type either csv or sql)");

        //Waits for input
        String type = scann.nextLine();

        //If using sql
        if (type.equals("sql")) {

            //Because sql repositories are not implemented yet, it will just terminate
            System.out.println("You choosed sql, you choosed wrong");

            bankService = new BankServiceImpl(new MySqlBankRepository());
            accountService = new AccountServiceImpl(new MySqlAccountRepository());
            paymentService = new PaymentServiceImpl(new MySqlPaymentRepository());
        }

        //If using csv
        else {

            bankService = new BankServiceImpl(new CsvBankRepository());
            accountService = new AccountServiceImpl(new CsvAccountRepository());
            paymentService = new PaymentServiceImpl(new CsvPaymentRepository());


            // File names might change depending on file location
            String bankFile = String.join(File.separator, "src", "main", "resources", "csv", "bank.csv");
            String accountFile = String.join(File.separator, "src", "main", "resources", "csv", "accounts.csv");
            String paymentFile = String.join(File.separator, "src", "main", "resources", "csv", "payments.csv");

            // Creates the bank
            Bank bestBank = bankService.manage(bankFile);

            // Creates account list with Account manager
            List<Account> accounts = accountService.manage(accountFile);

            // Stores all the accounts into our bank before payments
            bestBank.setUsers(accounts);


            //Writes accounts before payments
            paymentService.fwriter(String.join(File.separator, "src", "main", "resources", "csv", "accountsBeforePayments.csv"), bestBank);


            //This is the code that will be repeated if user wants
            while (doAgain) {
                List<Payment> payments = paymentService.manage(paymentFile, bestBank);


                Scanner scan = new Scanner(System.in);


                Boolean repeat = true;
                while (repeat) {

                    System.out.println("Do you wish to continue?(y/n)");

                    //Waits for input
                    String answer = scan.nextLine();

                    //For comparing strings, use "equals()" rather than "=="
                    if (answer.equals("y")) {
                        doAgain = true;
                        repeat = false;
                    } else if (answer.equals("n")) {
                        doAgain = false;
                        repeat = false;
                    }
                }
            }
        }

    }
}
