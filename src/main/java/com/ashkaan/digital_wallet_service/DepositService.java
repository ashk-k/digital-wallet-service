package com.ashkaan.digital_wallet_service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class DepositService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final LedgerEntryRepository ledgerEntryRepository;

    public DepositService (
        WalletRepository walletRepository,
        TransactionRepository transactionRepository,
        LedgerEntryRepository ledgerEntryRepository) {

      this.walletRepository = walletRepository;
      this.transactionRepository = transactionRepository;
      this.ledgerEntryRepository = ledgerEntryRepository;
    }

    @Transactional
    public void deposit(Long walletId, BigDecimal amount){
        
        
        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
          throw new IllegalArgumentException("Deposit should be more than 0");
        }

        Wallet wallet = walletRepository.findById(walletId)
              .orElseThrow( () -> new IllegalArgumentException("No Wallet Found"));
        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setAmount(amount);

        transactionRepository.save(transaction);

        LedgerEntry ledgerEntry = new LedgerEntry();
        ledgerEntry.setTransaction(transaction);
        ledgerEntry.setWallet(wallet);
        ledgerEntry.setEntryType(LedgerEntryType.CREDIT);
        ledgerEntry.setAmount(amount);

        ledgerEntryRepository.save(ledgerEntry);
        
        wallet.setBalance(wallet.getBalance().add(amount));
                        
        walletRepository.save(wallet); 
    }
}
