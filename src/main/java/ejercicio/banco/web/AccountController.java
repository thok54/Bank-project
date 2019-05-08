package ejercicio.banco.web;

import ejercicio.banco.dto.Account;
import ejercicio.banco.repository.MySqlAccountRepository;
import ejercicio.banco.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;


    @GetMapping
    public List<Account> getAll() {
        return accountService.process();
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable Integer id) {
        return accountService.find(id);
    }

    @GetMapping("/{name}")
    public List<Account> getByName(@PathVariable String name) {
        return accountService.findByName(name);
    }

    @PostMapping("/store/{id}/{name}/{money}/{iban}")
    public void store(@PathVariable Integer id, @PathVariable String name, @PathVariable Float money, @PathVariable String iban) {
        Account acc = new Account(id,name,money,iban);
        accountService.store(acc);
    }

    @PostMapping("/update/{position}/{id}/{name}/{money}/{iban}")
    public void update(@PathVariable Integer position, @PathVariable Integer id, @PathVariable String name, @PathVariable Float money, @PathVariable String iban) {
        Account acc = new Account(id,name,money,iban);
        accountService.update(position, acc);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        accountService.delete(id);
    }
}

