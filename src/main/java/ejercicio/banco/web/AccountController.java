package ejercicio.banco.web;

import ejercicio.banco.dto.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/account")
public class AccountController {
    private Account expectedAccount1 = new Account(1, "Peter",(float)3.00, "PIPIRANA87");
    private Account expectedAccount2 = new Account(2, "Aurelio", (float)8.49, "SATURN15STINKS");


        @GetMapping
        public Account getAccount() {
            return expectedAccount1;
        }

        @GetMapping("/all")
        public List<Account> getAllAccounts() {
            List<Account> expectedAccounts = new ArrayList();
            expectedAccounts.add(expectedAccount1);
            expectedAccounts.add(expectedAccount2);
            return expectedAccounts;
        }
}
