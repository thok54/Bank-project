package ejercicio.banco;

import ejercicio.banco.dto.Account;
import ejercicio.banco.dto.Bank;
import ejercicio.banco.dto.DataType;
import ejercicio.banco.dto.Payment;
import ejercicio.banco.repository.CsvAccountRepository;
import ejercicio.banco.repository.CsvBankRepository;
import ejercicio.banco.repository.CsvPaymentRepository;
import ejercicio.banco.repository.MySqlAccountRepository;
import ejercicio.banco.repository.MySqlBankRepository;
import ejercicio.banco.repository.MySqlPaymentRepository;
import ejercicio.banco.search.ToolSearchEngine;
import ejercicio.banco.service.AccountService;
import ejercicio.banco.service.AccountServiceImpl;
import ejercicio.banco.service.BankService;
import ejercicio.banco.service.BankServiceImpl;
import ejercicio.banco.service.DataGeneratorFactory;
import ejercicio.banco.service.PaymentService;
import ejercicio.banco.service.PaymentServiceImpl;


import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;

import static ejercicio.banco.dto.DataType.ACCOUNT;
import static ejercicio.banco.dto.DataType.BANK;
import static ejercicio.banco.dto.DataType.PAYMENT;

public class MainProcess {

    public static void main(String[] args) {
        Boolean doAgain = true;

        while (doAgain) {

            BankService bankService;
            AccountService accountService;
            PaymentService paymentService;

            Scanner scann = new Scanner(System.in);
            System.out.println("Do you wish to work with existing files or create random new ones?(type either old or new)");
            String type1 = scann.nextLine();
            if (type1.equals("new")) {
                // File names might change depending on file location
                String randBankFile = String.join(File.separator, "src", "main", "resources", "csv", "randomBank.csv");
                String randAccountFile = String.join(File.separator, "src", "main", "resources", "csv", "randomAccounts.csv");
                String randPaymentFile = String.join(File.separator, "src", "main", "resources", "csv", "randomPayments.csv");

                DataGeneratorFactory factory = new DataGeneratorFactory();
                factory.generate(BANK, randBankFile);
                factory.generate(ACCOUNT, randAccountFile);
                factory.generate(PAYMENT, randPaymentFile);

            } else {
                System.out.println("Do you wish to work with csv or sql?(type either csv or sql)");

                //Waits for input
                String type = scann.nextLine();

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
                    Bank bestBank = bankService.processBank(bankFile);

                    // Creates account list with Account manager
                    List<Account> accounts = accountService.processAccounts(accountFile);

                    // Stores all the accounts into our bank before payments
                    bestBank.setUsers(accounts);

                    //Writes accounts before payments
                    paymentService.fileWriter(String.join(File.separator, "src", "main", "resources", "csv", "accountsBeforePayments.csv"), bestBank);

                    //Process payments
                    List<Payment> payments = paymentService.processPayments(paymentFile, bestBank);
                }
            }
            //Ask for search
            Scanner searchQuestion = new Scanner(System.in);
            System.out.println("Do you wish to search anything?(y/n)");
            String searchQanswer = searchQuestion.nextLine();
            if (searchQanswer.equals("y")) {
                Scanner typeParameter = new Scanner(System.in);
                System.out.println("Are you looking for a bank, an account, or a payment?");
                DataType dType = DataType.fromValue(typeParameter.nextLine());

                if (dType != null) {
                    Scanner searchParameter = new Scanner(System.in);
                    System.out.println("Write a name to search");
                    String searchString = searchParameter.nextLine();

                    ToolSearchEngine searchEngine = new ToolSearchEngine();
                    System.out.println(searchEngine.search(dType, searchString));
                } else {
                    System.out.println("Could not identify search data type");
                }
            }

            Scanner scan = new Scanner(System.in);
            Boolean repeat = true;
            while (repeat) {

                System.out.println("Do you wish to continue?(y/n)");
                //Waits for input
                String answer = scan.nextLine();

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
