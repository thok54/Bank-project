package ejercicio.banco.web;

import ejercicio.banco.dto.Bank;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {
    private Bank expectedBank1 = new Bank(1, "bestBank", "right here");
    private Bank expectedBank2 = new Bank(2, "Worst ATM", "over there");


    @GetMapping
    public Bank getBank() {
        return expectedBank1;
    }

    @GetMapping("/all")
    public List<Bank> getAllBanks() {
        List<Bank> expectedBanks = new ArrayList();
        expectedBanks.add(expectedBank1);
        expectedBanks.add(expectedBank2);
        return expectedBanks;
    }

}
