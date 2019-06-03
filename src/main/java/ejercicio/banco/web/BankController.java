package ejercicio.banco.web;

import ejercicio.banco.dto.Bank;
import ejercicio.banco.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    BankService bankService;


    @GetMapping
    public List<Bank> getAll() {
        return bankService.process();
    }

    @GetMapping("/{id}")
    public Bank getById(@PathVariable Integer id) {
        return bankService.find(id);
    }

    @GetMapping("/byName/{name}")
    public List<Bank> getByName(@PathVariable String name) {
        return bankService.findByName(name);
    }

    @PostMapping
    public void store(@RequestBody Bank bank) {
        bankService.store(bank);
    }

    @PostMapping("/{id}")
    public void update(@PathVariable Integer position, @RequestBody Bank bank) {
        bankService.update(position, bank);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        bankService.delete(id);
    }
}
