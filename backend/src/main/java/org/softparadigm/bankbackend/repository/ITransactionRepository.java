package org.softparadigm.bankbackend.repository;

import org.softparadigm.bankbackend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, String> {
}
