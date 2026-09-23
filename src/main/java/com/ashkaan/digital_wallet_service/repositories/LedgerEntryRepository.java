package com.ashkaan.digital_wallet_service.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ashkaan.digital_wallet_service.LedgerEntry;

import java.util.List;

public interface LedgerEntryRepository extends JpaRepository<LedgerEntry, Long> {
  List<LedgerEntry> findByWalletIdOrderByCreatedAtDesc(Long walletId); //to get all the enteries from the ledger, and latest one should be at top
}
