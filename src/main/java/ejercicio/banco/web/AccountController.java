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

    @GetMapping("/byId/{id}")
    public Account getById(@PathVariable Integer id) {
        return accountService.find(id);
    }

    @GetMapping("/byName/{name}")
    public List<Account> getByName(@PathVariable String name) {
        return accountService.findByName(name);
    }

    @PostMapping("/store/{acc}")
    public void store(@PathVariable Account acc) {
        accountService.store(acc);
    }

    @PostMapping("/update/{position}/{acc}")
    public void update(@PathVariable Integer position, @PathVariable Account acc) {
        accountService.update(position, acc);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        accountService.delete(id);
    }
}

