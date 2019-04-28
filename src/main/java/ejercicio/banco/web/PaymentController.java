package ejercicio.banco.web;

import ejercicio.banco.dto.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/payment")
public class PaymentController {
    private Payment expectedPayment1 = new Payment(1, 3, 5, (float)1.87);
    private Payment expectedPayment2 = new Payment(2, 4, 6, (float)3.41);


    @GetMapping
    public Payment getAccount() {
        return expectedPayment1;
    }

    @GetMapping("/all")
    public List<Payment> getAllPayments() {
        List<Payment> expectedPayments = new ArrayList();
        expectedPayments.add(expectedPayment1);
        expectedPayments.add(expectedPayment2);
        return expectedPayments;
    }
}
