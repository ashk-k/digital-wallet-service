package com.ashkaan.digital_wallet_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping("/wallets/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositRequest request) {
        depositService.deposit(request.getWalletId(), request.getAmount());
        return ResponseEntity.ok("Deposit successful");
    }
}
