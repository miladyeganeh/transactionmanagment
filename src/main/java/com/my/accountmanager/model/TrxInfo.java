package com.my.accountmanager.model;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.domain.enums.TransactionType;
import com.my.accountmanager.model.dto.CurrencyInfo;
import com.my.accountmanager.model.dto.request.TransactionRequestDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrxInfo {

    private String TrxID;
    private Date trxDate;
    private Double amount;
    private CurrencyInfo currencyInfo;
    private Integer terminalId;
    private TransactionType trxType;
    private String cardPAN;
    private String hashedPassword;
    private String cvv;
    private String expireDateMonth;
    private String expireDateYear;
    private List<String> validationErrors;
    private AccountEntity sourceAccount;
    private AccountEntity destinationAccount;

    public static TrxInfo from(TransactionRequestDTO transactionRequestDTO) {
        TrxInfo trxInfo = new TrxInfo();
        trxInfo.setTrxID(transactionRequestDTO.getTrxID());
        trxInfo.setTrxDate(transactionRequestDTO.getTrxDate());
        trxInfo.setAmount(transactionRequestDTO.getAmount());
        trxInfo.setCardPAN(transactionRequestDTO.getCardPAN());
        trxInfo.setExpireDateMonth(transactionRequestDTO.getExpireDateMonth());
        trxInfo.setExpireDateYear(transactionRequestDTO.getExpireDateYear());
        trxInfo.setHashedPassword(transactionRequestDTO.getHashedPassword());
        trxInfo.setTerminalId(transactionRequestDTO.getTerminalId());
        trxInfo.setCvv(transactionRequestDTO.getCvv());
        return trxInfo;
    }

    public TrxInfo() {
        this.validationErrors = new ArrayList<>();
    }

    public void addValidationError(String error) {
        if (validationErrors == null) {
            this.validationErrors = new ArrayList<>();
        }
        this.validationErrors.add(error);
    }

    public String getTrxID() {
        return TrxID;
    }

    public void setTrxID(String trxID) {
        TrxID = trxID;
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

    public CurrencyInfo getCurrencyInfo() {
        return currencyInfo;
    }

    public void setCurrencyInfo(CurrencyInfo currencyInfo) {
        this.currencyInfo = currencyInfo;
    }

    public Integer getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Integer terminalId) {
        this.terminalId = terminalId;
    }

    public TransactionType getTrxType() {
        return trxType;
    }

    public void setTrxType(TransactionType trxType) {
        this.trxType = trxType;
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

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public AccountEntity getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(AccountEntity sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public AccountEntity getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(AccountEntity destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}
