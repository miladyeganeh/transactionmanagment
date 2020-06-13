package com.my.accountmanager.model.dto.request;

import com.my.accountmanager.domain.entity.TransactionRequestEntity;

import java.util.Date;

public class TransactionRequestDTO {

    private String trxID;
    private Date trxDate;
    private Double amount;
    private String currencyCode;
    private Integer terminalId;
    private Integer transactionType;
    private String cardPAN;
    private String hashedPassword;
    private String cvv;
    private String expireDateMonth;
    private String expireDateYear;
    private String sourceAccountNumber;
    private String destinationAccountNumber;

    public static TransactionRequestEntity to(TransactionRequestDTO requestDTO) {
        TransactionRequestEntity trxRequestEntity = new TransactionRequestEntity();
        trxRequestEntity.setAmount(requestDTO.getAmount());
        trxRequestEntity.setTrxID(requestDTO.getTrxID());
        trxRequestEntity.setCurrencyCode(requestDTO.getCurrencyCode());
        trxRequestEntity.setDestinationAccountNumber(requestDTO.getDestinationAccountNumber());
        trxRequestEntity.setCvv(requestDTO.getCvv());
        trxRequestEntity.setCardPAN(requestDTO.getCardPAN());
        trxRequestEntity.setExpireDateMonth(requestDTO.getExpireDateMonth());
        trxRequestEntity.setExpireDateYear(requestDTO.getExpireDateYear());
        return trxRequestEntity;
    }

    public String getTrxID() {
        return trxID;
    }

    public void setTrxID(String trxID) {
        this.trxID = trxID;
    }

    public Date getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(Date trxDate) {
        this.trxDate = trxDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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

    public String getCardPAN() {
        return cardPAN;
    }

    public void setCardPAN(String cardPAN) {
        this.cardPAN = cardPAN;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpireDateMonth() {
        return expireDateMonth;
    }

    public void setExpireDateMonth(String expireDateMonth) {
        this.expireDateMonth = expireDateMonth;
    }

    public String getExpireDateYear() {
        return expireDateYear;
    }

    public void setExpireDateYear(String expireDateYear) {
        this.expireDateYear = expireDateYear;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }
}
