package com.my.accountmanager.domain.entity;

import com.my.accountmanager.domain.enums.TransactionType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TRANSACTION")
public class TransactionEntity extends Auditable<String> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "TRANSACTIONID", unique = true)
    private String transactionID;

    @Column(name = "TERMINALID")
    private Integer terminalID;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "TYPE")
    private TransactionType type;

    private Date trxDate;

    @OneToOne
    private AccountEntity sourceAccount;

    @OneToOne
    private AccountEntity destinationAccount;

    @OneToOne
    private TransactionEntity reversTransaction;

    @OneToOne
    private TransactionEntity mainTransaction;

    @ManyToOne
    private DepositEntity deposit;

    public TransactionEntity() {
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

    public Integer getTerminalID() {
        return terminalID;
    }

    public void setTerminalID(Integer terminalID) {
        this.terminalID = terminalID;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Date getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(Date trxDate) {
        this.trxDate = trxDate;
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

    public TransactionEntity getReversTransaction() {
        return reversTransaction;
    }

    public void setReversTransaction(TransactionEntity reversTransaction) {
        this.reversTransaction = reversTransaction;
    }

    public TransactionEntity getMainTransaction() {
        return mainTransaction;
    }

    public void setMainTransaction(TransactionEntity mainTransaction) {
        this.mainTransaction = mainTransaction;
    }

    public DepositEntity getDeposit() {
        return deposit;
    }

    public void setDeposit(DepositEntity deposit) {
        this.deposit = deposit;
    }
}
