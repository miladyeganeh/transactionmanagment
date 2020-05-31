package com.my.accountmanager.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ACCOUNT")
public class AccountEntity extends Auditable<String> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "BALANCE")
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<DepositEntity> deposit;

    @OneToMany(mappedBy = "account")
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
