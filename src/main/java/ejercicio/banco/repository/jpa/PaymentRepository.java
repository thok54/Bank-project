package ejercicio.banco.repository.jpa;

import ejercicio.banco.dto.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
