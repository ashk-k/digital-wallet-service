package com.ashkaan.digital_wallet_service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransferService {
    
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final LedgerEntryRepository ledgerEntryRepository;

    public TransferService(
            WalletRepository walletRepository,
            TransactionRepository transactionRepository,
            LedgerEntryRepository ledgerEntryRepository) {

        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
        this.ledgerEntryRepository = ledgerEntryRepository;
    }

    @Transactional
    public void transfer(Long senderWalletId, Long receiverWalletId, BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero");
        }

        if (senderWalletId.equals(receiverWalletId)) {
            throw new IllegalArgumentException("Sender and receiver wallets must be different");
        }

        Wallet senderWallet = walletRepository.findById(senderWalletId)
                .orElseThrow(() -> new IllegalArgumentException("Sender wallet not found"));

        Wallet receiverWallet = walletRepository.findById(receiverWalletId)
                .orElseThrow(() -> new IllegalArgumentException("Receiver wallet not found"));

        if (senderWallet.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        Transaction transaction = new Transaction();
        transaction.setType(TransactionType.TRANSFER);
        transaction.setAmount(amount);

        transactionRepository.save(transaction);

        LedgerEntry debitEntry = new LedgerEntry();
        debitEntry.setTransaction(transaction);
        debitEntry.setWallet(senderWallet);
        debitEntry.setEntryType(LedgerEntryType.DEBIT);
        debitEntry.setAmount(amount);

        ledgerEntryRepository.save(debitEntry);

        LedgerEntry creditEntry = new LedgerEntry();
        creditEntry.setTransaction(transaction);
        creditEntry.setWallet(receiverWallet);
        creditEntry.setEntryType(LedgerEntryType.CREDIT);
        creditEntry.setAmount(amount);

        ledgerEntryRepository.save(creditEntry);

        senderWallet.setBalance(senderWallet.getBalance().subtract(amount));

        receiverWallet.setBalance(receiverWallet.getBalance().add(amount));

        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);
    }
}
