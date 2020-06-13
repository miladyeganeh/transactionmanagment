package com.my.accountmanager.model.dto;

import com.my.accountmanager.domain.entity.TransactionEntity;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.Date;

public class TransactionDTO implements Serializable {

    private Long id;
    private String transactionID;
    private Date trxDate;
    private Double amount;
    private Integer currencyCode;
    private Integer terminalID;
    private Integer transactionType;
    private String cardPAN;
    private String hashedPassword;
    private String cvv;
    private String expireDateMonth;
    private String expireDateYear;
    private AccountDTO sourceAccount;
    private AccountDTO destinationAccount;

    public static TransactionEntity from(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = new ModelMapper().map(transactionDTO, TransactionEntity.class);
        return transactionEntity;
    }

    public static TransactionDTO to(TransactionEntity transactionEntity) {
        ModelMapper modelMapper = new ModelMapper();
        TransactionDTO transactionDTO = modelMapper.map(transactionEntity, TransactionDTO.class);
        if (transactionDTO.getSourceAccount() != null) {
            transactionDTO.setSourceAccount(AccountDTO.form(transactionEntity.getSourceAccount()));
        }
        if (transactionDTO.getDestinationAccount() != null) {
            transactionDTO.setDestinationAccount(AccountDTO.form(transactionEntity.getDestinationAccount()));
        }

        return transactionDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
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

    public Integer getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Integer currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getTerminalID() {
        return terminalID;
    }

    public void setTerminalID(Integer terminalID) {
        this.terminalID = terminalID;
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

    public AccountDTO getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(AccountDTO sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public AccountDTO getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(AccountDTO destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}
