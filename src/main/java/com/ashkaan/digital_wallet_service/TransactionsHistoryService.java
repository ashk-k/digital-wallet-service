package com.ashkaan.digital_wallet_service;

import org.springframework.stereotype.Service;

import com.ashkaan.digital_wallet_service.repositories.LedgerEntryRepository;
import com.ashkaan.digital_wallet_service.repositories.WalletRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TransactionsHistoryService {

        private final LedgerEntryRepository ledgerEntryRepository;
        private final WalletRepository walletRepository;

        public TransactionsHistoryService(
        LedgerEntryRepository ledgerEntryRepository,
        WalletRepository walletRepository) {

    this.ledgerEntryRepository = ledgerEntryRepository;
    this.walletRepository = walletRepository;
}

    public List<TransactionHistoryResponse> getHistory(Long walletId, String email) {

    Wallet wallet = walletRepository.findById(walletId)
            .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Wallet not found"
            ));

    if (!wallet.getUser().getEmail().equals(email)) {
        throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                "Not your wallet"
        );
    }

    List<LedgerEntry> entries =
            ledgerEntryRepository.findByWalletIdOrderByCreatedAtDesc(walletId);

    return entries.stream()
            .map(entry -> new TransactionHistoryResponse(
                    entry.getTransaction().getId(),
                    entry.getTransaction().getType(),
                    entry.getEntryType(),
                    entry.getAmount(),
                    entry.getCreatedAt()
            ))
            .toList();
        }
}
