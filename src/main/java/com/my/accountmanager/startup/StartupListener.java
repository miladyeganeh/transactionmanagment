package com.my.accountmanager.startup;

import com.my.accountmanager.domain.entity.*;
import com.my.accountmanager.domain.enums.AccountStatus;
import com.my.accountmanager.domain.enums.CardType;
import com.my.accountmanager.service.AccountService;
import com.my.accountmanager.service.CurrencyService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class StartupListener {

    private final AccountService accountService;
    private final CurrencyService currencyService;
    private CurrencyEntity currencyEntity;

    public StartupListener(AccountService accountService, CurrencyService currencyService) {
        this.accountService = accountService;
        this.currencyService = currencyService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initializeValue () {
        this.currencyEntity = this.currencyService.save(createCurrency());
        this.accountService.save(createAccount());
        this.accountService.save(createAccount2());
        this.accountService.getAll();
    }

    private AccountEntity createAccount() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setBalance(1000D);
        accountEntity.setAccountNumber("400");
        accountEntity.setStatus(AccountStatus.ACTIVE);
        accountEntity.setIsActive(true);
        accountEntity.setOpeningDate(new Date());
        CustomerEntity customer = createCustomer();
        accountEntity.setCustomer(customer);
        accountEntity.setCurrency(this.currencyEntity);
        Set<CardEntity> cards = createCards();
        cards.forEach(cardEntity -> {
            cardEntity.setCustomer(customer);
            cardEntity.setAccount(accountEntity);
        });
        accountEntity.setCards(cards);
        Set<DepositEntity> deposits = createDeposit();
        deposits.forEach(deposit -> deposit.setAccount(accountEntity));
        accountEntity.setDeposit(deposits);
        return accountEntity;
    }

    private CustomerEntity createCustomer() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setNationalId("200500");
        customerEntity.setFirstName("Milad");
        customerEntity.setLastName("Yeganeh");
        customerEntity.setEmail("milad@test.com");
        customerEntity.setCustomerNumber("miladAccount");
        customerEntity.setAddress("tehran");
        return customerEntity;
    }

    private CurrencyEntity createCurrency() {
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setName("EUR");
        currencyEntity.setCode("EUR");
        return currencyEntity;
    }

    private Set<CardEntity> createCards(){
        Set<CardEntity> cards = new HashSet<>();
        CardEntity card = new CardEntity();
        card.setCardPAN("1234.1234.1234.1234");
        card.setCardHolderName("Milad Yeganeh");
        card.setType(CardType.DEBIT_CARD);
        card.setHashPassword("@#$%HRGB#$@#$@#$@$FGHDSFSDF");
        card.setExpireDateYear(23);
        card.setExpireDateMonth(10);
        cards.add(card);
        return cards;
    }

    private Set<DepositEntity> createDeposit() {
        Set<DepositEntity> deposits = new HashSet<>();
        DepositEntity depositEntity = new DepositEntity();
        depositEntity.setDepositNumber("200.1.123123.10");
        depositEntity.setIsDefault(true);
        depositEntity.setIsActive(true);
        deposits.add(depositEntity);
        return deposits;
    }

    private AccountEntity createAccount2() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setBalance(1000D);
        accountEntity.setAccountNumber("500");
        accountEntity.setStatus(AccountStatus.ACTIVE);
        accountEntity.setIsActive(true);
        accountEntity.setOpeningDate(new Date());
        CustomerEntity customer = createCustomer2();
        accountEntity.setCustomer(customer);
        Optional<CurrencyEntity> currency = this.currencyService.findByCurrencyByCode("EUR");
        currency.ifPresent(accountEntity::setCurrency);
        Set<CardEntity> cards = createCards2();
        cards.forEach(cardEntity -> {
            cardEntity.setCustomer(customer);
            cardEntity.setAccount(accountEntity);
        });
        accountEntity.setCards(cards);
        Set<DepositEntity> deposits = createDeposit2();
        deposits.forEach(deposit -> deposit.setAccount(accountEntity));
        accountEntity.setDeposit(deposits);
        return accountEntity;
    }

    private CustomerEntity createCustomer2() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setNationalId("200600");
        customerEntity.setFirstName("Adam");
        customerEntity.setLastName("Neary");
        customerEntity.setEmail("adam@test.com");
        customerEntity.setCustomerNumber("adamAccount");
        customerEntity.setAddress("Berlin");
        return customerEntity;
    }


    private Set<CardEntity> createCards2(){
        Set<CardEntity> cards = new HashSet<>();
        CardEntity card = new CardEntity();
        card.setCardPAN("4321.4321.4321.4321");
        card.setCardHolderName("Adam Neary");
        card.setType(CardType.DEBIT_CARD);
        card.setHashPassword("@#$%32#$##$@#$@#$@#$34523");
        card.setExpireDateYear(23);
        card.setExpireDateMonth(10);
        cards.add(card);
        return cards;
    }

    private Set<DepositEntity> createDeposit2() {
        Set<DepositEntity> deposits = new HashSet<>();
        DepositEntity depositEntity = new DepositEntity();
        depositEntity.setDepositNumber("200.1.432431.11");
        depositEntity.setIsDefault(true);
        depositEntity.setIsActive(true);
        deposits.add(depositEntity);
        return deposits;
    }
}
