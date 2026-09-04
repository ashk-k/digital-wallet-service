package com.ashkaan.digital_wallet_service;

import java.math.BigDecimal;

public class TransferRequest {
    
    private Long senderWalletId;
    private Long receiverWalletId;
    private BigDecimal amount;

    public Long getSenderWalletId() {
        return senderWalletId;
    }

    public void setSenderWalletId(Long senderWalletId) {
        this.senderWalletId = senderWalletId;
    }

    public Long getReceiverWalletId(){
        return receiverWalletId;
    }

    public void setReceiverWalletId(Long  receiverWalletId) {
        this.receiverWalletId =  receiverWalletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
