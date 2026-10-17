package com.ashkaan.digital_wallet_service.controllers;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashkaan.digital_wallet_service.TransactionHistoryResponse;
import com.ashkaan.digital_wallet_service.TransactionsHistoryService;

@RestController
@RequestMapping("/wallets")
public class TransactionHistoryController {

    private final TransactionsHistoryService transactionsHistoryService;

    public TransactionHistoryController(TransactionsHistoryService transactionsHistoryService) {
        this.transactionsHistoryService = transactionsHistoryService;
    }

    @GetMapping("/{walletId}/transactions")
    public List<TransactionHistoryResponse> getTransactionHistory(
            @PathVariable Long walletId,
            Authentication authentication) {

        return transactionsHistoryService.getHistory(walletId, authentication.getName());
    }
}