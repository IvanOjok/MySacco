package com.jambo.mysacco.repository;

import com.jambo.mysacco.models.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<List<Transaction>> findByUserId(Long userId);
}
