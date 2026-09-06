package com.ashkaan.digital_wallet_service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionHistoryResponse {
    
    private Long transactionId;
    private TransactionType transactionType;
    private LedgerEntryType entryType;
    private BigDecimal amount;
    private LocalDateTime createdAt;

    public TransactionHistoryResponse(
        Long transactionId,
        TransactionType transactionType,
        LedgerEntryType entryType,
        BigDecimal amount,
        LocalDateTime createdAt) {

            this.transactionId = transactionId;
            this.transactionType = transactionType;
            this.entryType = entryType;
            this.amount = amount;
            this.createdAt = createdAt;
        }

        public Long getTransactionId() {
            return transactionId;
        }

        public TransactionType getTransactionType() {
            return transactionType;
        }

        public LedgerEntryType getEntryType() {
            return entryType;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }
}
