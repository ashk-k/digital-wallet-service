package com.ashkaan.digital_wallet_service;

import org.springframework.stereotype.Service;

import com.ashkaan.digital_wallet_service.repositories.LedgerEntryRepository;

import java.util.List;

@Service 
public class TransactionsHistoryService {
    
    private final LedgerEntryRepository ledgerEntryRepository;

    public TransactionsHistoryService(LedgerEntryRepository ledgerEntryRepository) {
        this.ledgerEntryRepository = ledgerEntryRepository;
    }

    public List<TransactionHistoryResponse> getHistory(Long walletId) {
        List<LedgerEntry> entries = ledgerEntryRepository.findByWalletIdOrderByCreatedAtDesc(walletId) ;

        return entries.stream() //mtlb list k har item pr kaam krenge
               .map(entry -> new TransactionHistoryResponse( //.map ka mtlb har ledger entry ko transaction history response mein bdlenge
                entry.getTransaction().getId(),
                        entry.getTransaction().getType(),
                        entry.getEntryType(),
                        entry.getAmount(),
                        entry.getCreatedAt()
                ))
                .toList();
    }
}
