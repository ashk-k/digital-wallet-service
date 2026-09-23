package com.ashkaan.digital_wallet_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashkaan.digital_wallet_service.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long>{
    
}
