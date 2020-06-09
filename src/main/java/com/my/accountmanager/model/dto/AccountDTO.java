package com.my.accountmanager.model.dto;

import com.my.accountmanager.domain.entity.AccountEntity;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.Set;

public class AccountDTO {

    private Long id;
    private Double balance;
    private Date openingDate;
    private String accountNumber;
    private boolean isActive;
    private CustomerDTO customer;
    private Set<DepositDTO> deposit;
    private Set<CardDTO> cards;

    public static AccountEntity from(AccountDTO accountRequestDTO) {
        AccountEntity accountEntity = new ModelMapper().map(accountRequestDTO, AccountEntity.class);
        return accountEntity;
    }

    public static AccountDTO to(AccountEntity accountEntity) {
        AccountDTO accountDTO = new ModelMapper().map(accountEntity, AccountDTO.class);
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

    public Set<DepositDTO> getDeposit() {
        return deposit;
    }

    public void setDeposit(Set<DepositDTO> deposit) {
        this.deposit = deposit;
    }

    public Set<CardDTO> getCards() {
        return cards;
    }

    public void setCards(Set<CardDTO> cards) {
        this.cards = cards;
    }
}
