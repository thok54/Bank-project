package ejercicio.banco.search;

import ejercicio.banco.dto.Account;
import ejercicio.banco.dto.Bank;
import ejercicio.banco.dto.DataType;
import ejercicio.banco.dto.Payment;
import ejercicio.banco.repository.CsvAccountRepository;
import ejercicio.banco.repository.CsvBankRepository;
import ejercicio.banco.repository.CsvPaymentRepository;
import ejercicio.banco.service.AccountService;
import ejercicio.banco.service.AccountServiceImpl;
import ejercicio.banco.service.BankService;
import ejercicio.banco.service.BankServiceImpl;
import ejercicio.banco.service.PaymentService;
import ejercicio.banco.service.PaymentServiceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO: Make everything return InternalDto

public class ToolSearchEngine implements SearchEngine{
    @Override
    public List<Object> search(DataType type, String stuff) {
         String file;

        //Usando switch
        switch (type) {
            case BANK:
                //Searches bank name
                file = String.join(File.separator, "src", "main", "resources", "csv", "randomBank.csv");
                BankService bankService = new BankServiceImpl(new CsvBankRepository());
                List<Bank> banks = ((BankServiceImpl) bankService).findBanks(file, stuff);
                return Collections.singletonList(banks);
            case ACCOUNT:
                //Searchers user name
                file = String.join(File.separator, "src", "main", "resources", "csv", "randomAccounts.csv");
                AccountService accountService = new AccountServiceImpl(new CsvAccountRepository());
                List<Account> accounts = ((AccountServiceImpl) accountService).findAccounts(file, stuff);
                return Collections.singletonList(accounts);
            case PAYMENT:
                //Searches payment ID
                file = String.join(File.separator, "src", "main", "resources", "csv", "randomPayments.csv");
                PaymentService paymentService = new PaymentServiceImpl(new CsvPaymentRepository());
                List<Payment> payments = ((PaymentServiceImpl) paymentService).findPayments(file, stuff);
                return Collections.singletonList(payments);
        }
        return new ArrayList<>();
    }
}
