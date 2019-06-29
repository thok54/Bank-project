package ejercicio.banco.repository.jpa;

import ejercicio.banco.dto.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
