package com.my.accountmanager.domain.entity;

import com.my.accountmanager.domain.enums.TransactionType;
import com.my.accountmanager.utils.DateUtil;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TRANSACTION")
public class TransactionEntity extends Auditable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "TRANSACTIONID", unique = true)
    //todo shouldn't we receive this form input?
    private String transactionID = DateUtil.getYearMonthDay() + UUID.randomUUID();

    @Column(name = "TERMINALID")
    private Integer terminalID;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "TYPE")
    private TransactionType type;

    @Column(name = "trxDate")
    private Date trxDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CURRENCY_ID")
    private CurrencyEntity currency;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SRC_ACC_ID")
    private AccountEntity sourceAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DEST_ACC_ID")
    private AccountEntity destinationAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DEPOSIT_ID")
    private DepositEntity deposit;

    @OneToOne(cascade = CascadeType.ALL)
    private TransactionEntity reversTransaction;

    @OneToOne(cascade = CascadeType.ALL)
    private TransactionEntity mainTransaction;

    @OneToOne(cascade = CascadeType.ALL)
    private DocumentEntity documentEntity;

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

    public CurrencyEntity getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEntity currency) {
        this.currency = currency;
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

    public DepositEntity getDeposit() {
        return deposit;
    }

    public void setDeposit(DepositEntity deposit) {
        this.deposit = deposit;
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

    public DocumentEntity getDocumentEntity() {
        return documentEntity;
    }

    public void setDocumentEntity(DocumentEntity documentEntity) {
        this.documentEntity = documentEntity;
    }
}
