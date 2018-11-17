package ejercicio.banco.search;

import ejercicio.banco.dto.Account;
import ejercicio.banco.dto.Bank;
import ejercicio.banco.dto.DataType;
import ejercicio.banco.dto.InternalDto;
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

//TODO: Returning Internal dto causes Collections to no longer work

public class ToolSearchEngine implements SearchEngine {

    private BankService bankService;
    private AccountService accountService;
    private PaymentService paymentService;

    public ToolSearchEngine() {
        this.bankService = new BankServiceImpl(new CsvBankRepository());
        this.accountService = new AccountServiceImpl(new CsvAccountRepository());
        this.paymentService = new PaymentServiceImpl(new CsvPaymentRepository());
    }

    @Override
    public List<Object> search(DataType type, String stuff) {
        String file;
        if (type == null) {
            throw new IllegalArgumentException("Type must not be null");
        }
        if (stuff == null) {
            throw new IllegalArgumentException("Searching Null String");
        }
        switch (type) {
            case BANK:
                file = String.join(File.separator, "src", "main", "resources", "csv", "randomBank.csv");
                List<Bank> banks = ((BankServiceImpl) bankService).findBanks(file, stuff);
                return Collections.singletonList(banks);
            case ACCOUNT:
                file = String.join(File.separator, "src", "main", "resources", "csv", "randomAccounts.csv");
                List<Account> accounts = ((AccountServiceImpl) accountService).findAccounts(file, stuff);
                return Collections.singletonList(accounts);
            case PAYMENT:
                file = String.join(File.separator, "src", "main", "resources", "csv", "randomPayments.csv");
                List<Payment> payments = ((PaymentServiceImpl) paymentService).findPayments(file, stuff);
                return Collections.singletonList(payments);
        }
        return new ArrayList<>();
    }
}
