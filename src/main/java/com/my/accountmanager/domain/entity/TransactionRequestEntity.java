package com.my.accountmanager.domain.entity;

import com.my.accountmanager.model.enums.ResponseCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION_REQUEST")
public class TransactionRequestEntity extends Auditable{

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String trxID;

    @Column
    private Date time;

    @Column
    private Double amount;

    @Column
    private String currencyCode;

    @Column
    private Integer terminalId;

    @Column
    private Integer transactionType;

    @Column
    private String cardPAN;

    @Column
    private String hashedPassword;

    @Column
    private String cvv;

    @Column
    private String expireDateMonth;

    @Column
    private String expireDateYear;

    @Column
    private String sourceAccountNumber;

    @Column
    private String destinationAccountNumber;

    @OneToOne
    private TransactionEntity transaction;

    @Column
    private String message;

    @Column(name = "RESPONSE_CODE")
    @Enumerated(EnumType.STRING)
    private ResponseCode responseCode;

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

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String response) {
        this.message = response;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
