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

    @GetMapping("/{name}")
    public List<Bank> getByName(@PathVariable String name) {
        return bankService.findByName(name);
    }

    @PostMapping("/store/{id}/{name}/{address}")
    public void store(@PathVariable Integer id, @PathVariable String name, @PathVariable String address) {
        Bank bank = new Bank(id,name,address);
        bankService.store(bank);
    }

    @PostMapping("/update/{position}/{id}/{name}/{address}")
    public void update(@PathVariable Integer position, @PathVariable Integer id, @PathVariable String name, @PathVariable String address) {
        Bank bank = new Bank(id,name,address);
        bankService.update(position, bank);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        bankService.delete(id);
    }
}
