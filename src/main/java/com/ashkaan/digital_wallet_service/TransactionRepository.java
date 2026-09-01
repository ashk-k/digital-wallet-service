package com.ashkaan.digital_wallet_service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    //extend JpaRepository<Transaction, Long> means Entity type = Wallet and Primary Key = Long
}
