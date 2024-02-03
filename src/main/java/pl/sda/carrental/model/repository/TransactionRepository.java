package pl.sda.carrental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.carrental.model.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
