package com.ashkaan.digital_wallet_service.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashkaan.digital_wallet_service.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // JpaRepository<Transaction, Long> means Entity type = Transaction and Primary Key = Long
    Optional<Transaction> findByIdempotencyKey(String idempotencyKey);
}
