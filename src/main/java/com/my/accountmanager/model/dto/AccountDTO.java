package com.my.accountmanager.model.dto;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.domain.entity.CardEntity;
import com.my.accountmanager.domain.entity.DepositEntity;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;


public class AccountDTO implements Serializable {

    private Long id;
    private Double balance;
    private Date openingDate;
    private String accountNumber;
    private boolean isActive;
    private CustomerDTO customer;
    private Set<String> deposits;
    private Set<String> cards;
    private String currencyCode;

    public static AccountEntity to(AccountDTO accountRequestDTO) {
        AccountEntity accountEntity = new ModelMapper().map(accountRequestDTO, AccountEntity.class);
        return accountEntity;
    }

    public static AccountDTO form(AccountEntity accountEntity) {
        AccountDTO accountDTO = new ModelMapper().map(accountEntity, AccountDTO.class);
        if (accountEntity.getDeposit() != null) {
            accountDTO.setDeposits(accountEntity.getDeposit().stream().map(DepositEntity::getDepositNumber).collect(Collectors.toSet()));
        }
        if (accountEntity.getCards() != null) {
            accountDTO.setCards(accountEntity.getCards().stream().map(CardEntity::getCardPAN).collect(Collectors.toSet()));
        }
        return accountDTO;
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

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public Set<String> getDeposits() {
        return deposits;
    }

    public void setDeposits(Set<String> deposits) {
        this.deposits = deposits;
    }

    public Set<String> getCards() {
        return cards;
    }

    public void setCards(Set<String> cards) {
        this.cards = cards;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
