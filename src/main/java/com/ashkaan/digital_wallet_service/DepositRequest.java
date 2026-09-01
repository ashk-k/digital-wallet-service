package com.ashkaan.digital_wallet_service;

import java.math.BigDecimal;

public class DepositRequest {
    
    private Long walletId;
    private BigDecimal amount;

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

//Postmaan se jo data ayega, usko java object mein pakadne ke liye DTO/request class use krre h.
