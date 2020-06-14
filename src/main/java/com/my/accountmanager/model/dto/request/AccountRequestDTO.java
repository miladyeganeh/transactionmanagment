package com.my.accountmanager.model.dto.request;

import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.domain.enums.AccountStatus;
import com.my.accountmanager.model.dto.CardDTO;
import com.my.accountmanager.model.dto.CustomerDTO;
import com.my.accountmanager.model.dto.DepositDTO;
import com.my.accountmanager.model.dto.response.withrel.CurrencyDTO;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountRequestDTO {
    private Long id;
    private Double balance;
    private String accountNumber;
    private Date openingDate;
    private boolean isActive;
    private Integer status;
    private CurrencyDTO currency;
    private CustomerDTO customer;
    private Set<DepositDTO> deposit;
    private Set<CardDTO> cards;

    public static AccountEntity to(AccountRequestDTO accountRequestDTO) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setBalance(accountRequestDTO.getBalance());
        accountEntity.setAccountNumber(accountRequestDTO.getAccountNumber());
        accountEntity.setOpeningDate(accountRequestDTO.getOpeningDate());
        accountEntity.setIsActive(accountRequestDTO.getIsActive());
        accountEntity.setStatus(AccountStatus.getByValue(accountRequestDTO.getStatus()));
        if (accountRequestDTO.getCurrency() != null) {
            accountEntity.setCurrency(CurrencyDTO.to(accountRequestDTO.getCurrency()));
        }
        if (accountRequestDTO.getCustomer() != null) {
            accountEntity.setCustomer(CustomerDTO.to(accountRequestDTO.getCustomer()));
        }
        if (!accountRequestDTO.getDeposit().isEmpty()) {
            accountEntity.setDeposit(accountRequestDTO.getDeposit().stream().map(DepositDTO::to).collect(Collectors.toSet()));
        }
        if (!accountRequestDTO.getDeposit().isEmpty()) {
            accountEntity.setCards(accountRequestDTO.getCards().stream().map(CardDTO::to).collect(Collectors.toSet()));
        }
        return accountEntity;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public CurrencyDTO getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyDTO currency) {
        this.currency = currency;
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
