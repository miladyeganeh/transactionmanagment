package com.my.accountmanager.domain.dto.request;

import java.util.Date;

public class TransactionRequestDTO {

    private String trxID;
    private Date time;
    private Double amount;
    private String transactionType;
    private AccountRequestDTO sourceAccount;
    private AccountRequestDTO destinationAccount;

    public String getTrxID() {
        return trxID;
    }

    public void setTrxID(String trxID) {
        this.trxID = trxID;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public AccountRequestDTO getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(AccountRequestDTO sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public AccountRequestDTO getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(AccountRequestDTO destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}
