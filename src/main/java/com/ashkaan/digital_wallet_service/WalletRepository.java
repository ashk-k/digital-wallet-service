package com.ashkaan.digital_wallet_service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long>{
    
}
