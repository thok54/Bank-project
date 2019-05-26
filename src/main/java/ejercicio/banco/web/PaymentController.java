package ejercicio.banco.web;

import ejercicio.banco.dto.Payment;
import ejercicio.banco.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{name}")
    public List<Payment> getByName(@PathVariable String name) {
        return paymentService.findByName(name);
    }

    @PostMapping
    public void store(@RequestBody Payment payment) {
        paymentService.store(payment);
    }

    @PostMapping("/{id}")
    public void update(@PathVariable Integer position, @RequestBody Payment payment) {
        paymentService.update(position, payment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        paymentService.delete(id);
    }
}
