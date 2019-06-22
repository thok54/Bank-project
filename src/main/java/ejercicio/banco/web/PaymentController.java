package ejercicio.banco.web;

import ejercicio.banco.dto.Payment;
import ejercicio.banco.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;


    @GetMapping
    public List<Payment> getAll() {
        return paymentService.process();
    }

    @GetMapping("/{id}")
    public Payment getById(@PathVariable Integer id) {
        return paymentService.find(id);
    }

    @GetMapping("/byName/{name}")
    public List<Payment> getByName(@PathVariable String name) {
        return paymentService.findByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void store(@RequestBody Payment payment) {
        paymentService.store(payment);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Payment payment) {
        paymentService.update(id, payment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        paymentService.delete(id);
    }
}
