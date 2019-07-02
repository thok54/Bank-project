package exercise.web;

import exercise.dto.Bank;
import exercise.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return bankService.findAllByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void store(@RequestBody Bank bank) {
        bankService.store(bank);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Bank bank) {
        bankService.update(id, bank);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        bankService.delete(id);
    }
}
