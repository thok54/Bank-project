package ejercicio.banco.web;

import ejercicio.banco.dto.Account;
import ejercicio.banco.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public void store(@RequestBody Account acc) {
        accountService.store(acc);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody Account acc){
        accountService.update(id, acc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        accountService.delete(id);
    }
}

