package com.my.accountmanager.model.dto.response.withrel;

import com.my.accountmanager.controller.AccountController;
import com.my.accountmanager.controller.CardController;
import com.my.accountmanager.controller.CurrencyController;
import com.my.accountmanager.controller.CustomerController;
import com.my.accountmanager.domain.entity.AccountEntity;
import com.my.accountmanager.domain.entity.CardEntity;
import com.my.accountmanager.domain.entity.DepositEntity;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


public class AccountResponseDTO extends RepresentationModel<AccountResponseDTO> implements Serializable {

    private Long id;
    private Double balance;
    private Date openingDate;
    private String accountNumber;
    private boolean isActive;
    private Long customerID;
    private Set<String> deposits;
    private Set<Long> cards;
    private Long currencyID;

    public static AccountEntity to(AccountResponseDTO accountRequestDTO) {
        AccountEntity accountEntity = new ModelMapper().map(accountRequestDTO, AccountEntity.class);
        return accountEntity;
    }

    public static AccountResponseDTO form(AccountEntity accountEntity) {
        AccountResponseDTO accountDTO = new AccountResponseDTO();
        accountDTO.setId(accountEntity.getId());
        accountDTO.setBalance(accountEntity.getBalance());
        accountDTO.setOpeningDate(accountEntity.getOpeningDate());
        accountDTO.setAccountNumber(accountEntity.getAccountNumber());
        accountDTO.setCustomerID(accountEntity.getCustomer().getId());
        accountDTO.setCurrencyID(accountEntity.getCurrency().getId());
        if (accountEntity.getDeposit() != null) {
            accountDTO.setDeposits(accountEntity.getDeposit().stream().map(DepositEntity::getDepositNumber).collect(Collectors.toSet()));
        }
        if (accountEntity.getCards() != null) {
            accountDTO.setCards(accountEntity.getCards().stream().map(CardEntity::getId).collect(Collectors.toSet()));
        }

        return setLinks(accountDTO);
    }


    //TODO add  deposit link
    private static AccountResponseDTO setLinks(AccountResponseDTO accountDTO) {
        accountDTO.add(linkTo(methodOn(AccountController.class).getAccount(accountDTO.getAccountNumber())).withSelfRel());
        accountDTO.add(linkTo(methodOn(CustomerController.class).getCustomer(accountDTO.getCustomerID())).withRel("Customer"));
        accountDTO.add(linkTo(methodOn(CurrencyController.class).getCurrency(accountDTO.getCurrencyID())).withRel("Customer"));
        accountDTO.getCards().forEach(card -> accountDTO.add(linkTo(methodOn(CardController.class).getCard(card)).withRel("Card")));
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

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Set<String> getDeposits() {
        return deposits;
    }

    public void setDeposits(Set<String> deposits) {
        this.deposits = deposits;
    }

    public Set<Long> getCards() {
        return cards;
    }

    public void setCards(Set<Long> cards) {
        this.cards = cards;
    }

    public Long getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(Long currencyID) {
        this.currencyID = currencyID;
    }
}
