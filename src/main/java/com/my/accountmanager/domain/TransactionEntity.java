package com.my.accountmanager.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TRANSACTION")
public class TransactionEntity extends Auditable<String> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "TRANSACTIONID")
    private String transactionID;

    @Column(name = "TERMINALID")
    private String terminalID;

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

    public String getTerminalID() {
        return terminalID;
    }

    public void setTerminalID(String terminalID) {
        this.terminalID = terminalID;
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
