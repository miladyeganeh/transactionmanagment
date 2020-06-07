package com.my.accountmanager.model.dto.request;

import java.util.Date;

public class TransactionRequestDTO {

    private String trxID;
    private Date time;
    private Double amount;
    private Integer terminalId;
    private Integer transactionType;
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

    public Integer getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Integer terminalId) {
        this.terminalId = terminalId;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
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
