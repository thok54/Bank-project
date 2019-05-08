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

    @PostMapping("/store/{id}/{bankId}/{userId}/{amount}")
    public void store(@PathVariable Integer id, @PathVariable Integer bankId, @PathVariable Integer userId, @PathVariable Float amount) {
        Payment payment = new Payment(id, bankId, userId, amount);
        paymentService.store(payment);
    }

    @PostMapping("/update/{position}/{id}/{bankId}/{userId}/{amount}")
    public void update(@PathVariable Integer position, @PathVariable Integer id, @PathVariable Integer bankId, @PathVariable Integer userId, @PathVariable Float amount) {
        Payment payment = new Payment(id, bankId, userId, amount);
        paymentService.update(position, payment);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        paymentService.delete(id);
    }
}
