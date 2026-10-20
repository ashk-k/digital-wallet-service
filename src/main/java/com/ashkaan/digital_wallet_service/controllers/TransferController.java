package com.ashkaan.digital_wallet_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashkaan.digital_wallet_service.TransferRequest;
import com.ashkaan.digital_wallet_service.TransferService;

@RestController
@RequestMapping("/wallets")
public class TransferController {
    
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(
            @RequestBody TransferRequest request,
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            Authentication authentication) {

        transferService.transfer(
                request.getSenderWalletId(),
                request.getReceiverWalletId(),
                request.getAmount(),
                authentication.getName(),
                idempotencyKey
        );

        return ResponseEntity.ok("Transfer Successful");
        
    }
}
