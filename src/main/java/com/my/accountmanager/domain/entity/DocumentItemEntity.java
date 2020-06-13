package com.my.accountmanager.domain.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "DOCUMENT_ITEM")
public class DocumentItemEntity extends Auditable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "COMMENT", nullable = false)
    private String comment;

    @Column(name = "ISSUANCE_NAME", nullable = false)
    private Date issuanceDate;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "IS_DEBTOR")
    private Boolean isDebtor;

    @ManyToOne
    @JoinColumn(name = "DOCUMENT_ID")
    private DocumentEntity document;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CURRENCY_ID")
    private CurrencyEntity currency;

    @Column(name = "ExCHANGE_RATE")
    private Double exchangeRate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCOUNT_ID")
    private AccountEntity account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getIssuanceDate() {
        return issuanceDate;
    }

    public void setIssuanceDate(Date issuanceDate) {
        this.issuanceDate = issuanceDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getDebtor() {
        return isDebtor;
    }

    public void setDebtor(Boolean debtor) {
        isDebtor = debtor;
    }

    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

    public CurrencyEntity getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEntity currency) {
        this.currency = currency;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}
