package com.my.accountmanager.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.my.accountmanager.domain.enums.CardType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "CARD")
public class CardEntity extends Auditable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "Card_Holder_Name")
    private String cardHolderName;

    @Column(name = "CARDPAN")
    private String cardPAN;

    @Column(name = "PIN")
    private String pin;

    @Column(name = "CVV")
    private String cvv;

    @Column(name = "EXPIRE_DATE_MONTH")
    private Integer expireDateMonth;

    @Column(name = "EXPIRE_DATE_YEAR")
    private Integer expireDateYear;

    @Column(name = "HASH_PASSWORD")
    private String hashPassword;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private CardType type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCOUNT_ID")
    @JsonIgnore
    private AccountEntity account;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerEntity customer;

    public CardEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardPAN() {
        return cardPAN;
    }

    public void setCardPAN(String cardPAN) {
        this.cardPAN = cardPAN;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Integer getExpireDateMonth() {
        return expireDateMonth;
    }

    public void setExpireDateMonth(Integer expireDateMonth) {
        this.expireDateMonth = expireDateMonth;
    }

    public Integer getExpireDateYear() {
        return expireDateYear;
    }

    public void setExpireDateYear(Integer expireDateYear) {
        this.expireDateYear = expireDateYear;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
