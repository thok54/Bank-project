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

    @GetMapping("/byId/{id}")
    public Payment getById(@PathVariable Integer id) {
        return paymentService.find(id);
    }

    @GetMapping("/byName/{name}")
    public List<Payment> getByName(@PathVariable String name) {
        return paymentService.findByName(name);
    }

    @PostMapping("/store/{payment}")
    public void store(@PathVariable Payment payment) {
        paymentService.store(payment);
    }

    @PostMapping("/update/{position}/{payment}")
    public void update(@PathVariable Integer position, @PathVariable Payment payment) {
        paymentService.update(position, payment);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        paymentService.delete(id);
    }
}
