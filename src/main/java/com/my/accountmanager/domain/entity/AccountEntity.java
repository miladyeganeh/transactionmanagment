package com.my.accountmanager.domain.entity;

import com.my.accountmanager.domain.enums.AccountStatus;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author M.Yeganeh on 31/05/2020.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ACCOUNT")
public class AccountEntity extends Auditable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "BALANCE")
    private Double balance;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "OPENING_DATE")
    private Date openingDate;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CURRENCY_ID")
    private CurrencyEntity currency;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<DepositEntity> deposit;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CardEntity> cards;

    public AccountEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public CurrencyEntity getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEntity currency) {
        this.currency = currency;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public Set<DepositEntity> getDeposit() {
        return deposit;
    }

    public void setDeposit(Set<DepositEntity> deposit) {
        this.deposit = deposit;
    }

    public Set<CardEntity> getCards() {
        return cards;
    }

    public void setCards(Set<CardEntity> cards) {
        this.cards = cards;
    }
}
